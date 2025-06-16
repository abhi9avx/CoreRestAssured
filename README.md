# 🚀 REST Assured Automation Framework

<div align="center">

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![REST Assured](https://img.shields.io/badge/REST_Assured-00ADD8?style=for-the-badge&logo=rest-assured&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-00ADD8?style=for-the-badge&logo=testng&logoColor=white)
![Allure](https://img.shields.io/badge/Allure-00ADD8?style=for-the-badge&logo=allure&logoColor=white)
![Jenkins](https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

</div>

## 📋 Overview

A robust and scalable API testing framework built with REST Assured, TestNG, and Allure Reports. This framework provides comprehensive test coverage for the ReqRes.in API, featuring modern testing practices and continuous integration capabilities.

## ✨ Key Features

- **Comprehensive API Testing**
  - REST Assured for robust API testing
  - TestNG for test execution and parallel testing
  - Allure Reports for detailed test reporting
  - Maven for dependency management
  - JSON Schema validation for response structure verification
  - POJO-based request/response handling

- **CI/CD Integration**
  - Jenkins pipeline configuration
  - Automated test execution
  - Detailed reporting
  - Build status notifications

- **Framework Features**
  - Environment-specific configurations
  - Comprehensive test coverage
  - Modular test structure
  - Reusable components
  - JSON Schema validation
  - Request/Response logging
  - Custom assertions

## 🛠️ Prerequisites

- Java JDK 11 or higher
- Maven 3.6.x or higher
- Jenkins (for CI/CD)
- Allure Commandline Tool

## 🚀 Getting Started

### 1. Clone the Repository
```bash
git clone https://github.com/abhi9avx/CoreRestAssured.git
cd CoreRestAssured
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Run Tests
```bash
# Run all tests with default configuration
mvn clean test -P run-all-tests

# Run with custom configuration
mvn clean test -DBASE_URL=https://reqres.in -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml

# Run specific test class
mvn test -Dtest=JsonSchemaValidation

# Generate and view Allure report
mvn allure:serve
```

## 🔧 Jenkins CI/CD Configuration

### 1. Required Plugins
- Allure Test Report Plugin
- Git Plugin
- Maven Integration Plugin

### 2. Global Tool Configuration
1. Navigate to `Manage Jenkins > Global Tool Configuration`
2. Configure Maven (Version: 3.9.10)
3. Configure Allure Commandline (Version: 2.24.0)

### 3. Job Configuration

#### General Settings
```yaml
Project Name: RestAssuredAutomation
Description: REST Assured API Testing Framework with Allure Reporting
Discard old builds: 
  - Days to keep builds: 7
  - Max # of builds to keep: 10
```

#### Source Code Management
```yaml
Type: Git
Repository URL: https://github.com/abhi9avx/CoreRestAssured.git
Branch: */main
```

#### Build Triggers
```yaml
Poll SCM: H/15 * * * *  # Every 15 minutes
GitHub hook trigger: Enabled
```

#### Build Environment
```yaml
Delete workspace before build: Enabled
Add timestamps to Console Output: Enabled
```

#### Build Steps
```yaml
Maven Version: Maven_3.9.10
Goals: -DBASE_URL=https://reqres.in -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml clean test
```

#### Post-build Actions
```yaml
Allure Report:
  Results path: target/allure-results
  Properties:
    allure.results.directory: target/allure-results
    allure.report.directory: target/allure-report
```

## 📊 Test Reports

### Allure Reports
The framework generates detailed Allure reports that provide:

- Test execution summary
- Pass/fail statistics
- Detailed test results
- Test duration
- Environment details
- Test steps and attachments
- Historical trends
- JSON Schema validation results
- Request/Response details

#### Sample Report
![Allure Report Overview](docs/reports/allure-report-overview.png)

## 🏗️ Project Structure
```
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── reqres
│   │               ├── pojo
│   │               └── utils
│   └── test
│       ├── java
│       │   └── com
│       │       ├── reqres
│       │       │   └── test
│       │       └── rest
│       └── resources
│           ├── testng.xml
│           └── EchoGet.json
├── pom.xml
└── README.md
```

## 📝 Test Coverage

The framework includes comprehensive test cases for:
- User management
  - Create user
  - Update user
  - Get user details
- Resource management
  - Get resource list
  - Get single resource
- Authentication
  - Login
  - Token validation
- File uploads
- Request parameters
- Response validation
  - JSON Schema validation
  - Response body validation
  - Status code validation
  - Header validation

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 👨‍💻 Author

**Abhinav Kumar**
- LinkedIn: [Connect with me](https://www.linkedin.com/in/abhi9avx/)
- GitHub: [@abhi9avx](https://github.com/abhi9avx)

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

<div align="center">
Made with ❤️ by Abhinav Kumar
</div>