# REST Assured API Testing Framework with Allure Reporting

This project is a REST API testing framework built using REST Assured, TestNG, and Allure for beautiful test reporting.

## Prerequisites

- Java JDK 8 or higher
- Maven
- Allure Command Line Tool

## Installation

### 1. Install Allure Command Line Tool

#### For macOS:
```bash
brew install allure
```

#### For Windows (using scoop):
```bash
scoop install allure
```

#### For Linux:
```bash
sudo apt-add-repository ppa:qameta/allure
sudo apt-get update
sudo apt-get install allure
```

## Project Structure

```
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── reqres
│   │               ├── base
│   │               ├── pojo
│   │               └── utils
│   │               └── pojo/           # POJO classes for request/response
│   │                   ├── UserResponse.java
│   │                   ├── UserData.java
│   │                   ├── Support.java
│   │                   ├── ResourceListResponse.java
│   │                   └── DataItem.java
│   └── test
│       ├── java
│       │   └── com
│       │       └── reqres
│       │           └── test
│       └── resources
│           └── config.properties
├── pom.xml
└── README.md
```

## Features

- **Base Test Setup**: Common configuration for all tests including base URL and API key
- **POJO Classes**: Structured data models for request/response handling
- **Test Cases**: Comprehensive test coverage for various API endpoints
- **Configuration Management**: Secure handling of API keys using properties file

## Test Coverage

### 1. User Endpoints
- **Get User Details**
  - Endpoint: `/api/users/{id}`
  - Validates user data, support information
  - Checks response structure and content

- **Get User Not Found**
  - Endpoint: `/api/users/{id}`
  - Validates 404 response for non-existent users
  - Verifies empty response body

### 2. Resource Endpoints
- **Get Resource List**
  - Endpoint: `/api/unknown`
  - Validates pagination data
  - Checks resource details and support information
  - Verifies response structure

## Setup and Configuration

1. **Prerequisites**
   - Java JDK 8 or higher
   - Maven
   - IDE (Eclipse/IntelliJ)

2. **Dependencies**
   ```xml
   <dependencies>
       <dependency>
           <groupId>io.rest-assured</groupId>
           <artifactId>rest-assured</artifactId>
           <version>5.3.0</version>
       </dependency>
       <dependency>
           <groupId>org.testng</groupId>
           <artifactId>testng</artifactId>
           <version>7.7.1</version>
       </dependency>
       <dependency>
           <groupId>com.fasterxml.jackson.core</groupId>
           <artifactId>jackson-databind</artifactId>
           <version>2.15.2</version>
       </dependency>
   </dependencies>
   ```

3. **Configuration**
   - Create `config.properties` file in project root
   - Add API key: `reqres.api.key=your-api-key`

## Running Tests

### 1. Run Tests with Allure Results Generation

```bash
mvn clean test -Dallure.results.directory=target/allure-results
```

### 2. Generate and View Allure Report

There are two ways to view the Allure report:

#### Option 1: Using Allure Serve (Recommended)
```bash
allure serve target/allure-results
```
This will:
- Generate the report
- Start a local web server
- Open the report in your default browser
- The report will be available at `http://localhost:XXXX` (port number will be shown in the console)

#### Option 2: Generate Static Report
```bash
allure generate target/allure-results -o target/allure-report
```
This will generate a static HTML report in the `target/allure-report` directory.

## Understanding Allure Reports

The Allure report provides several key sections:

1. **Overview**
   - Test execution summary
   - Duration and status statistics
   - Environment information

2. **Categories**
   - Test categorization based on failures
   - Custom categories if defined

3. **Suites**
   - Test suite organization
   - Test class and method details

4. **Graphs**
   - Test execution trends
   - Duration distribution
   - Status breakdown

5. **Timeline**
   - Chronological view of test execution
   - Parallel execution visualization

6. **Behaviors**
   - BDD-style test organization
   - Feature and story breakdown

## Adding Allure Annotations to Tests

Here's how to enhance your test reports with Allure annotations:

```java
import io.qameta.allure.*;

@Feature("Resource List API")
public class GetResourceListTest extends BaseTest {
    
    @Test
    @Description("Test to verify the resource list API functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Get Resource List")
    public void getResourceList() {
        // Test implementation
    }
}
```

### Available Allure Annotations

1. **@Feature** - Groups tests by feature
2. **@Story** - Groups tests by user story
3. **@Description** - Adds detailed test description
4. **@Severity** - Marks test severity level
5. **@Step** - Marks individual test steps
6. **@Attachment** - Attaches additional information
7. **@Link** - Adds links to related documentation
8. **@Issue** - Links tests to specific issues

## Best Practices

1. **Use Meaningful Descriptions**
   ```java
   @Description("Verify that user can successfully create a new workspace")
   ```

2. **Add Steps for Complex Operations**
   ```java
   @Step("Creating new workspace with name: {name}")
   public void createWorkspace(String name) {
       // Implementation
   }
   ```

3. **Attach Important Information**
   ```java
   @Attachment(value = "Response", type = "text/plain")
   public String attachResponse(String response) {
       return response;
   }
   ```

4. **Use Severity Levels Appropriately**
   - BLOCKER
   - CRITICAL
   - NORMAL
   - MINOR
   - TRIVIAL

## Troubleshooting

1. **Report Not Opening**
   - Ensure Allure command line tool is installed
   - Check if the results directory exists
   - Verify no other process is using the default port

2. **No Test Results in Report**
   - Verify tests are running successfully
   - Check if Allure listener is properly configured
   - Ensure correct results directory is specified

3. **Missing Annotations**
   - Verify Allure dependencies in pom.xml
   - Check import statements
   - Ensure correct annotation usage

## Additional Resources

- [Allure Framework Documentation](https://docs.qameta.io/allure/)
- [REST Assured Documentation](https://rest-assured.io/)
- [TestNG Documentation](https://testng.org/doc/)

## Contributing

Feel free to submit issues and enhancement requests! 