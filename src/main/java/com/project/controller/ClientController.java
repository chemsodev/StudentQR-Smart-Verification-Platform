package com.project.controller;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.project.model.ResponseData;
import com.project.model.Student;
import com.project.service.StudentService;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin(origins = "https://verification-system-wn3u.onrender.com")
@Controller
@RequestMapping("/api")
public class ClientController {
    private static final long MAX_IMAGE_SIZE = 5 * 1024 * 1024; // 5MB

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseData> addImagePost(@RequestParam("image") MultipartFile file) throws IOException {
        ResponseData responseData = processImage(file);
        return new ResponseEntity<>(responseData,
                responseData.getStatus() == HttpStatus.OK.value() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    private ResponseData processImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return new ResponseData("No file was uploaded.", HttpStatus.BAD_REQUEST.value(), null, null, null, null, null, null, null, null, null);
        }
        if (file.getSize() > MAX_IMAGE_SIZE) {
            return new ResponseData("Image size exceeds the limit of 5MB.", HttpStatus.BAD_REQUEST.value(), null, null, null, null, null, null, null, null, null);
        }

        try (InputStream inputStream = file.getInputStream()) {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            if (bufferedImage == null) {
                return new ResponseData("Invalid image format.", HttpStatus.BAD_REQUEST.value(), null, null, null, null, null, null, null, null, null);
            }

            // QR code processing
            String qrCodeData = processQRCode(bufferedImage);

            // Check for specific QR code data that indicates failure
            if (!qrCodeData.equals("https://progres.mesrs.dz/api/infos/checkInscription/undefined")) {
                return new ResponseData("Invalid QR code data.", HttpStatus.BAD_REQUEST.value(), null, null, null, null, null, null, null, null, null);
            }

            // OCR processing
            ITesseract tesseract = new Tesseract();
            tesseract.setDatapath("/app/tessdata");
            tesseract.setLanguage("eng");

            String matricule = extractMatriculeFromOcr(bufferedImage, tesseract);
            if (matricule != null) {
                return verifyStudent(matricule.trim());
            } else {
                return new ResponseData("Matricule not found in OCR results.", HttpStatus.NOT_FOUND.value(), null, null, null, null, null, null, null, null, null);
            }
        } catch (Exception e) {
            return handleProcessingError(e);
        }
    }

    private String processQRCode(BufferedImage image) throws Exception {
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        QRCodeReader reader = new QRCodeReader();
        Result result = reader.decode(bitmap);
        return result.getText();
    }

    private String extractMatriculeFromOcr(BufferedImage image, ITesseract tesseract) throws TesseractException {
        for (int i = 1; i <= 4; i++) {
            BufferedImage rotatedImage = rotateImage(image, 90 * i);
            String ocrResult = tesseract.doOCR(rotatedImage);
            System.out.println("OCR Result after rotation " + (90 * i) + " degrees: " + ocrResult); // Debugging line

            String matricule = extractMatricule(ocrResult);
            if (matricule != null) {
                return matricule;
            }
        }
        return null;
    }

    private ResponseData verifyStudent(String matricule) {
        try {
            Student student = studentService.getStudentByMatricule(matricule);
            if (student == null) {
                // Student is valid but not found in the database
                return new ResponseData(
                        "Valid QR code and matricule, but the student is not registered in the database.",
                        HttpStatus.NOT_FOUND.value(),
                        matricule,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                );
            }

            return buildResponseData(student);
        } catch (RuntimeException e) {
            return new ResponseData("Error verifying student: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), matricule, null, null, null, null, null, null, null, null);
        }
    }

    private ResponseData buildResponseData(Student student) {
        return new ResponseData(
                "Student confirmed: " + student.getNom() + " " + student.getPrenom(),
                HttpStatus.OK.value(),
                student.getPrenom(),
                student.getMatricule(),
                student.getNom(),
                student.getEmail_1(),
                student.getEmail_2(),
                student.getSpeciality() != null ? student.getSpeciality().getSpecialite() : "N/A",
                student.getSpeciality() != null ? student.getSpeciality().getPalier() : "N/A",
                student.getSection() != null ? student.getSection().getSection_name() : "N/A",
                student.getEtat()
        );
    }

    private BufferedImage rotateImage(BufferedImage image, int angle) {
        double radians = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(radians));
        double cos = Math.abs(Math.cos(radians));
        int w = image.getWidth();
        int h = image.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, image.getType());
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);
        int x = w / 2;
        int y = h / 2;
        at.rotate(radians, x, y);

        AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image, rotated);
        return rotated;
    }

    private String extractMatricule(String ocrResult) {
    Pattern pattern = Pattern.compile("UN1604\\d{4}(\\d{12})");
    Matcher matcher = pattern.matcher(ocrResult);
    if (matcher.find()) {
        System.out.println("Matched Matricule: " + matcher.group(1)); // Debugging line
        return matcher.group(1); // Return only the 12-digit captured group
    }
    return null;
}
    private ResponseData handleProcessingError(Exception e) {
        if (e instanceof NotFoundException) {
            return new ResponseData("QR code not found in the uploaded image.", HttpStatus.NOT_FOUND.value(), null, null, null, null, null, null, null, null, null);
        } else if (e instanceof ChecksumException || e instanceof FormatException) {
            return new ResponseData("Invalid QR code format.", HttpStatus.BAD_REQUEST.value(), null, null, null, null, null, null, null, null, null);
        } else if (e instanceof TesseractException) {
            return new ResponseData("OCR error occurred.", HttpStatus.INTERNAL_SERVER_ERROR.value(), null, null, null, null, null, null, null, null, null);
        } else {
            return new ResponseData("Unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null, null, null, null, null, null, null, null, null);
        }
    }
}
