# 🚀 StudentQR: Smart Verification Platform

**StudentQR** is a modern web platform designed to automate and streamline student identity verification using QR codes. It simplifies student management for educational institutions by securely authenticating students and retrieving essential details in real-time.

**View:** [Verification-System](https://verification-system-wn3u.onrender.com/)
## 🛠 Features

- **QR Code Scanning:** Automatically verify students through QR codes.
- **Fast & Secure:** Ensures secure and rapid verification.
- **Automated Student Data Fetching:** Retrieve relevant student information upon successful QR code scan.
- **User-Friendly Interface:** modern and simple UI.

## 📂 Project Structure

```bash
StudentQR/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/studentqr/
│   │   │       ├── controller/         # Handles HTTP requests and responses
│   │   │       ├── model/              # Entity and data models
│   │   │       ├── service/            # Business logic for QR processing
│   │   │       └── StudentQRApp.java   # Main application class
│   │   └── resources/
│   │       ├── static/                 # Frontend assets (CSS, JS, Images)
│   │       ├── templates/              # HTML views (Thymeleaf)
│   │       └── application.properties  # Configurations
│   └── test/
│       └── java/                       # Unit and integration tests
├── Dockerfile                          # Docker configuration for containerization
├── pom.xml                             # Maven dependencies and build configuration
├── .gitignore                          # Files to ignore in version control
└── README.md                           # This file
```
## 🚀 Getting Started
### Prerequisites
Before running the project, ensure you have the following installed:

- **Java 11+**
- **Maven (for building the project)**
- **Docker (optional, if running with Docker)**
- **Installation**
- **Clone the Repository**

```bash
git clone https://github.com/yourusername/studentqr.git
cd studentqr
Configure the Database
```
-**Build and start the application with Maven:**

```bash
mvn clean install
mvn spring-boot:run
Access the App
```
-**Open your browser and navigate to http://localhost:8081.**

## 🐳 Running with Docker
-**Alternatively, you can run StudentQR using Docker:**

-**Build the Docker image:**

```bash
docker build -t studentqr-app .
Run the container:
```
```bash
docker run -p 8081:8081 studentqr-app
 ```
## 💻 Usage
-**Upload a Student's card:**

-**From the homepage**, click on "Choose FIle" to upload a screenshot that contains the student's card.
Verify Student Information:

-**Upon successful QR code processing**, the platform will display the student's ID and other information.
## 🧪 Testing
-**Run unit and integration tests using Maven:**

```bash
mvn test
Test cases are located in the src/test/java/ directory.
```
## 🔧 Technologies Used
- **Backend**: Java Spring Boot.
- **Frontend:** single react and tailwind page.
- **DataBase:** mysql.
- **DataBAse insertion:** python.
- **Version Control:** Git.
- **Containerization:** Docker.

- **Fork the repository**
- **Create a new branch (git checkout -b feature-branch)**
- **Make your changes and commit (git commit -m 'Add some feature')**
- **Push to the branch (git push origin feature-branch)**
- **Create a Pull Request**
## 📄 License
This project is licensed under the MIT License. See the LICENSE file for more details.

## 💬 Contact
For any questions or feedback, feel free to reach out:

Email: bourabiachemseddine@gmail.com
GitHub: chemsodev
📢 Shoutout
Big thanks to all users of StudentQR for making the project better every day with sending feedbacks!
