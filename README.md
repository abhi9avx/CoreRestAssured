# ReqRes API Automation Framework

This project is an automated test framework for the ReqRes API (https://reqres.in) using Rest Assured, TestNG, and Java.

## Project Structure

```
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── resreq/
│   │               └── pojo/           # POJO classes for request/response
│   │                   ├── UserResponse.java
│   │                   ├── UserData.java
│   │                   ├── Support.java
│   │                   ├── ResourceListResponse.java
│   │                   └── DataItem.java
│   └── test/
│       └── java/
│           └── com/
│               └── reqres/
│                   ├── base/           # Common setup and utilities
│                   │   └── BaseTest.java
│                   └── test/           # Test classes
│                       ├── GetUserTest.java
│                       ├── GetUserNotFoundTest.java
│                       └── GetResourceListTest.java
└── config.properties                   # Configuration file for API key
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

1. **Using Maven**
   ```bash
   mvn clean test
   ```

2. **Using TestNG XML**
   - Right-click on `testng.xml`
   - Select "Run As" > "TestNG Suite"

## Project Structure Details

### Base Test Class
- Handles common setup for all tests
- Manages base URL and API key configuration
- Provides request specification for API calls

### POJO Classes
- **UserResponse**: Main response structure for user endpoints
- **UserData**: User information model
- **Support**: Support information model
- **ResourceListResponse**: Response structure for resource list
- **DataItem**: Individual resource item model

### Test Classes
- **GetUserTest**: Tests user retrieval functionality
- **GetUserNotFoundTest**: Tests error handling for non-existent users
- **GetResourceListTest**: Tests resource list retrieval

## Best Practices Implemented

1. **Code Organization**
   - Clear package structure
   - Separation of concerns
   - Reusable components

2. **Test Design**
   - Independent test cases
   - Clear assertions
   - Proper error handling

3. **Security**
   - API key management
   - Configuration file handling
   - Secure data handling

4. **Maintainability**
   - Common setup in base class
   - Reusable POJO classes
   - Clear naming conventions

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details. 