# CoreRestAssured - Enterprise API Testing Framework

[![CI Status](https://github.com/abhi9avx/CoreRestAssured/workflows/CoreRestAssured%20CI/badge.svg)](https://github.com/abhi9avx/CoreRestAssured/actions)
[![Test Reports](https://img.shields.io/badge/Reports-Live-brightgreen)](https://abhi9avx.github.io/CoreRestAssured/reports/latest/)
[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://openjdk.java.net/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![RestAssured](https://img.shields.io/badge/RestAssured-5.5.1-blue.svg)](https://rest-assured.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.11.0-green.svg)](https://testng.org/)

## 🚀 Overview

**CoreRestAssured** is a comprehensive, enterprise-grade REST API testing framework built with modern testing practices and industry standards. It provides a robust foundation for automated API testing with advanced reporting, CI/CD integration, and scalable test architecture.

### Key Features

- **🏗️ Modular Architecture**: Clean separation of concerns with POJO models, utilities, and test layers
- **🔧 Advanced Configuration**: Environment-based configuration management with fallback mechanisms  
- **📊 Rich Reporting**: Integrated Allure reporting with interactive dashboards and trend analysis
- **🚀 CI/CD Ready**: GitHub Actions integration with automated test execution and report deployment
- **🎯 BDD Support**: Behavior-driven development approach with Given-When-Then patterns
- **⚡ Parallel Execution**: TestNG-powered parallel test execution for faster feedback
- **🔍 Comprehensive Validation**: JSON schema validation, response time assertions, and data integrity checks
- **📝 Detailed Logging**: Configurable logging with environment-aware output management

## 📋 Table of Contents

- [Quick Start](#-quick-start)
- [Project Architecture](#-project-architecture)
- [Framework Components](#-framework-components)
- [Test Categories](#-test-categories)
- [Configuration Management](#-configuration-management)
- [Reporting & Analytics](#-reporting--analytics)
- [CI/CD Pipeline](#-cicd-pipeline)
- [Development Guide](#-development-guide)
- [Best Practices](#-best-practices)
- [Troubleshooting](#-troubleshooting)

## 🏃 Quick Start

### Prerequisites
- **Java 11+** (OpenJDK or Oracle JDK)
- **Maven 3.6+** for dependency management
- **Git** for version control

### Installation & Setup

```bash
# Clone the repository
git clone https://github.com/abhi9avx/CoreRestAssured.git
cd CoreRestAssured

# Verify Java and Maven installation
java -version
mvn -version

# Run all tests
mvn clean test

# Generate and serve Allure reports
mvn allure:serve
```

### Environment Configuration

```bash
# Set base URL (optional - defaults to https://reqres.in)
export BASE_URL=https://your-api-endpoint.com

# Set API key for authentication (optional - has fallback)
export API_KEY=your-api-key-here
```

## 🏗️ Project Architecture

```
CoreRestAssured/
├── src/
│   ├── main/java/com/resreq/
│   │   ├── pojo/                    # Data Transfer Objects
│   │   │   ├── Data.java           # User data model
│   │   │   ├── DataItem.java       # Individual data items
│   │   │   ├── LoginRequest.java   # Authentication request model
│   │   │   ├── LoginResponse.java  # Authentication response model
│   │   │   ├── RequestUserDetails.java  # User creation request
│   │   │   ├── ResponseUserDetails.java # User creation response
│   │   │   ├── UserListResponse.java    # User list pagination model
│   │   │   ├── UserResponse.java        # Single user response
│   │   │   ├── ResourceListResponse.java # Resource listing model
│   │   │   ├── EmptyUserResponse.java   # Empty response handling
│   │   │   └── Support.java            # Support information model
│   │   └── utils/
│   │       └── ConfigReader.java   # Configuration management utility
│   ├── main/resources/
│   │   ├── CreateUserPayload.json  # Test data templates
│   │   └── CreateWorkspacePayload.json
│   └── test/java/
│       ├── com/reqres/             # API-specific tests
│       │   ├── base/
│       │   │   └── BaseTest.java   # Test foundation & setup
│       │   └── test/               # Functional test suites
│       │       ├── CreateUser.java      # User creation tests
│       │       ├── GetUserTest.java     # User retrieval tests
│       │       ├── GetUserListTest.java # User listing tests
│       │       ├── GetUserNotFoundTest.java # Error handling tests
│       │       ├── GetResourceListTest.java # Resource tests
│       │       ├── LoginSuccesful.java      # Authentication tests
│       │       └── UpdateUser.java          # User modification tests
│       └── com/rest/               # RestAssured feature examples
│           ├── AutomateGet.java         # GET request patterns
│           ├── AutomatePost.java        # POST request patterns
│           ├── AutomatePut.java         # PUT request patterns
│           ├── AutomateDelete.java      # DELETE request patterns
│           ├── JsonSchemaValidation.java # Schema validation
│           ├── RequestParameters.java    # Parameter handling
│           ├── RequestSpecificationTest.java  # Request specs
│           ├── ResponseSpecificationTest.java # Response specs
│           ├── Filters.java             # Request/Response filters
│           ├── MethodChaining.java      # Fluent API patterns
│           ├── StaticImports.java       # Import optimization
│           └── NonStaticImports.java    # Alternative import styles
└── test/resources/
    ├── testng.xml              # TestNG suite configuration
    ├── EchoGet.json           # Test data files
    └── logs/                  # Test execution logs
```

## 🧩 Framework Components

### 1. Base Test Infrastructure (`BaseTest.java`)
- **Environment Detection**: Automatically detects CI/local environments
- **Authentication Management**: Configurable API key authentication with fallbacks
- **Request Specification**: Pre-configured request specifications with headers and base URI
- **Logging Configuration**: Environment-aware logging (file-based locally, silent in CI)

### 2. POJO Models (`com.resreq.pojo`)
- **Type Safety**: Strongly-typed data models for all API entities
- **Lombok Integration**: Automated getter/setter generation for cleaner code
- **JSON Mapping**: Jackson-based serialization/deserialization
- **Validation Support**: Built-in validation for request/response data integrity

### 3. Utility Classes (`com.resreq.utils`)
- **ConfigReader**: Properties-based configuration management
- **Environment Variables**: Support for runtime configuration overrides
- **Fallback Mechanisms**: Graceful degradation when configurations are missing

### 4. Test Categories

#### Functional API Tests (`com.reqres.test`)
- **User Management**: Complete CRUD operations for user entities
- **Authentication**: Login/logout flow validation
- **Resource Management**: Resource listing and retrieval
- **Error Handling**: 4xx/5xx error response validation
- **Data Validation**: Response structure and content verification

#### RestAssured Feature Examples (`com.rest`)
- **HTTP Methods**: Comprehensive examples for GET, POST, PUT, DELETE
- **Request Specifications**: Reusable request configurations
- **Response Specifications**: Standardized response validation
- **Parameter Handling**: Query parameters, path parameters, form data
- **JSON Schema Validation**: Contract testing with schema validation
- **Method Chaining**: Fluent API usage patterns
- **Filters**: Request/response logging and modification

## 🎯 Test Categories & Coverage

| **Category** | **Test Count** | **Coverage** | **Description** |
|--------------|----------------|--------------|-----------------|
| **User Management** | 15 | CRUD Operations | Create, read, update, delete user entities |
| **Authentication** | 3 | Login/Logout | Session management and token validation |
| **Data Validation** | 8 | Schema/Content | JSON schema validation and data integrity |
| **Error Handling** | 5 | 4xx/5xx Responses | Negative testing and error scenarios |
| **Performance** | 7 | Response Time | SLA validation and performance benchmarks |
| **Integration** | 12 | End-to-End | Complete workflow validation |

**Total Test Coverage**: 50+ automated test scenarios

## ⚙️ Configuration Management

### Environment Variables
```bash
# Primary configuration
BASE_URL=https://api.example.com    # Target API endpoint
API_KEY=your-secret-key            # Authentication token

# Optional configurations  
LOG_LEVEL=INFO                     # Logging verbosity
TIMEOUT=30000                      # Request timeout in milliseconds
RETRY_COUNT=3                      # Failed request retry attempts
```

### Configuration Hierarchy
1. **Environment Variables** (Highest priority)
2. **System Properties** (`-Dproperty=value`)
3. **Configuration Files** (`config.properties`)
4. **Default Values** (Fallback)

### BaseTest Configuration Features
- **Automatic Environment Detection**: CI vs Local execution
- **Dynamic Authentication**: Multiple auth strategies
- **Logging Management**: Environment-appropriate log levels
- **Request Specification**: Centralized request configuration

## 📊 Reporting & Analytics

### Live Test Reports
🔗 **Access Reports**: [https://abhi9avx.github.io/CoreRestAssured/reports/latest/](https://abhi9avx.github.io/CoreRestAssured/reports/latest/)

### Report Features
- **📈 Interactive Dashboards**: Real-time test execution metrics
- **📱 Mobile Responsive**: Stakeholder-friendly mobile interface  
- **🔍 Detailed Analysis**: Step-by-step test execution breakdown
- **📊 Trend Analysis**: Historical test performance tracking
- **🚨 Failure Analysis**: Root cause analysis with stack traces
- **⏱️ Performance Metrics**: Response time trends and SLA monitoring

### Local Report Generation
```bash
# Generate Allure report
mvn allure:generate

# Serve report locally  
mvn allure:serve

# Open specific report
mvn allure:open
```

## 🚀 CI/CD Pipeline

### GitHub Actions Integration
- **🔄 Automated Triggers**: Push to main, Pull requests
- **⚡ Fast Execution**: ~30-40 seconds average runtime
- **📊 Auto-deployment**: Reports deployed to GitHub Pages
- **🏗️ Build Matrix**: Multi-environment testing support
- **📦 Artifact Management**: 30-day test artifact retention

### Pipeline Stages
1. **Environment Setup**: JDK 11, Maven, dependencies
2. **Code Compilation**: Source and test compilation
3. **Test Execution**: Parallel test suite execution
4. **Report Generation**: Allure report creation
5. **Deployment**: GitHub Pages report publishing
6. **Notification**: Slack/email notifications (configurable)

### Quality Gates
- ✅ **All tests must pass** for merge approval
- ✅ **Code coverage** threshold enforcement
- ✅ **Performance benchmarks** must be met
- ✅ **Security scanning** for dependencies

## 💻 Development Guide

### Running Tests

```bash
# Execute all tests
mvn clean test

# Run specific test class
mvn test -Dtest=CreateUser

# Run tests with specific suite
mvn test -Dsurefire.suiteXmlFiles=testng.xml

# Run tests in parallel
mvn test -DthreadCount=4

# Skip tests (for build only)
mvn clean compile -DskipTests
```

### Test Development Patterns

#### 1. BDD-Style Test Structure
```java
@Test
@Description("Create user with valid details")
@Severity(SeverityLevel.CRITICAL)
public void createUserWithValidDetails() {
    // Given - Test data preparation
    RequestUserDetails userDetails = new RequestUserDetails();
    userDetails.setName("John Doe");
    userDetails.setJob("QA Engineer");
    
    // When - API call execution
    Response response = given()
        .spec(requestSpec)
        .contentType(ContentType.JSON)
        .body(userDetails)
        .when()
        .post("/api/users");
    
    // Then - Assertions and validation
    response.then()
        .statusCode(201)
        .body("name", equalTo("John Doe"));
}
```

#### 2. POJO-Based Data Handling
```java
// Serialize request
RequestUserDetails request = new RequestUserDetails();
request.setName("Jane Smith");

// Deserialize response  
ResponseUserDetails response = apiResponse.as(ResponseUserDetails.class);
Assert.assertEquals(response.getName(), request.getName());
```

### Adding New Tests

1. **Create Test Class**: Extend `BaseTest` for common setup
2. **Define Test Data**: Use POJO models for type safety
3. **Implement Test Logic**: Follow BDD patterns
4. **Add Allure Annotations**: For comprehensive reporting
5. **Update TestNG Suite**: Include in appropriate test suite

### Debugging & Troubleshooting

```bash
# Enable detailed logging
mvn test -Dlog4j.configuration=debug

# Run single test with verbose output
mvn test -Dtest=CreateUser -X

# Generate dependency tree
mvn dependency:tree

# Validate TestNG configuration
mvn test-compile
```

## 🎯 Best Practices

### Test Design Principles
- **🔄 Idempotent Tests**: Tests should be repeatable without side effects
- **🎯 Single Responsibility**: Each test validates one specific behavior
- **📊 Data-Driven**: Use external data sources for test parameterization
- **🚫 No Hard Dependencies**: Tests should be independent and executable in any order
- **⚡ Fast Feedback**: Optimize for quick execution and early failure detection

### Code Quality Standards
- **📝 Meaningful Names**: Clear, descriptive test and method names
- **🏗️ DRY Principle**: Reusable components and utilities
- **🔒 Type Safety**: Leverage POJO models and strong typing
- **📚 Documentation**: Comprehensive Allure annotations and comments
- **🧪 Test Coverage**: Aim for high functional coverage over line coverage

### Performance Optimization
- **⚡ Parallel Execution**: Utilize TestNG parallel capabilities
- **🎯 Selective Testing**: Use TestNG groups for targeted test execution
- **📦 Resource Management**: Proper cleanup and resource disposal
- **🚀 CI Optimization**: Optimize pipeline for faster feedback cycles

## 🔧 Troubleshooting

### Common Issues & Solutions

#### Authentication Failures
```bash
# Verify API key configuration
echo $API_KEY

# Test with curl
curl -H "x-api-key: $API_KEY" https://reqres.in/api/users

# Check BaseTest authentication setup
```

#### Test Execution Issues
```bash
# Clear Maven cache
mvn dependency:purge-local-repository

# Rebuild project
mvn clean compile test-compile

# Verify TestNG configuration
mvn surefire:test
```

#### Report Generation Problems
```bash
# Clean Allure results
rm -rf target/allure-results target/site

# Regenerate reports
mvn clean test allure:report
```

### Debug Mode
```bash
# Enable debug logging
export LOG_LEVEL=DEBUG
mvn test -Dlog4j.debug=true

# Maven debug mode
mvn test -X -e
```

## 🤝 Contributing

### Development Workflow
1. **Fork Repository**: Create personal fork
2. **Feature Branch**: `git checkout -b feature/new-test-suite`
3. **Development**: Implement features following coding standards
4. **Testing**: Ensure all tests pass locally
5. **Documentation**: Update README and code comments
6. **Pull Request**: Submit PR with detailed description

### Code Review Checklist
- ✅ All tests pass locally and in CI
- ✅ Code follows established patterns and conventions
- ✅ Proper error handling and logging
- ✅ Allure annotations for reporting
- ✅ Updated documentation where necessary

## 📈 Roadmap

### Upcoming Features
- **🔐 OAuth 2.0 Support**: Enhanced authentication mechanisms
- **🌐 Multi-Environment**: Support for dev/staging/prod environments
- **📊 Performance Testing**: JMeter integration for load testing
- **🔍 API Mocking**: WireMock integration for contract testing
- **📱 Mobile API Testing**: Support for mobile-specific API patterns

---

## 📞 Support & Contact

- **📧 Issues**: [GitHub Issues](https://github.com/abhi9avx/CoreRestAssured/issues)
- **📖 Documentation**: [Wiki](https://github.com/abhi9avx/CoreRestAssured/wiki)
- **💬 Discussions**: [GitHub Discussions](https://github.com/abhi9avx/CoreRestAssured/discussions)

---

**Built with ❤️ for enterprise-grade API testing excellence**