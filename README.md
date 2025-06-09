# REST Assured Automation Framework

A robust API testing framework built with REST Assured, TestNG, and Allure Reports for comprehensive API testing.

## 🚀 Features

- REST Assured for API testing
- TestNG for test execution and parallel testing
- Allure Reports for detailed test reporting
- Maven for dependency management
- Jenkins CI/CD integration
- Environment-specific configurations
- Comprehensive test coverage for ReqRes.in API

## 📋 Prerequisites

- Java JDK 11 or higher
- Maven 3.6.x or higher
- Jenkins (for CI/CD)
- Allure Commandline Tool

## 🛠️ Setup Instructions

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd CoreRestAssured
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Run tests locally**
   ```bash
   # Run all tests with default configuration
   mvn clean test -P run-all-tests
   
   # Or run with custom configuration
   mvn clean test -DBASE_URL=https://reqres.in -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml
   ```

## 🔧 Jenkins CI/CD Setup

### 1. Jenkins Plugin Requirements
- Allure Test Report Plugin
- Git Plugin
- Maven Integration Plugin

### 2. Global Tool Configuration
1. Go to Jenkins Dashboard > Manage Jenkins > Global Tool Configuration
2. Configure Maven
3. Configure Allure Commandline (Version: 2.24.0)

### 3. Job Configuration
1. Create a new Freestyle project
2. Configure Git repository
3. Build Steps:
   - Maven clean test with parameters:
     ```
     mvn -DBASE_URL=https://reqres.in -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml clean test
     ```
4. Post-build Actions:
   - Allure Report
     - Results path: `target/allure-results`

## 📊 Test Reports

### Allure Reports
After test execution, view the Allure report in Jenkins:
1. Navigate to the build
2. Click on "Allure Report" in the left menu
3. View detailed test results, trends, and analytics

#### Sample Allure Report
![Allure Report Overview](docs/reports/allure-report-overview.png)

The Allure report provides:
- Test execution summary
- Pass/fail statistics
- Detailed test results
- Test duration
- Environment details
- Test steps and attachments
- Historical trends

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
│       │       └── rest
│       └── resources
│           └── testng.xml
├── pom.xml
└── README.md
```

## 🔄 CI/CD Pipeline

The project is configured with Jenkins for continuous integration:
- Automatic build on code changes
- Test execution
- Allure report generation
- Build status notifications

## 📝 Test Cases

The framework includes comprehensive test cases for:
- User management
- Resource management
- Authentication
- File uploads
- Request parameters
- Response validation

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.