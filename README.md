# CoreRestAssured

A robust REST API testing framework built with REST Assured, TestNG, and Java.

## Overview

This project is a comprehensive REST API testing framework that provides a structured approach to API testing using industry-standard tools and best practices. It's designed to help you write, maintain, and execute API tests efficiently.

## Prerequisites

- Java JDK 8 or higher
- Maven 3.6.0 or higher
- IDE (IntelliJ IDEA recommended)

## Project Structure

```
CoreRestAssured/
├── src/
│   ├── main/
│   │   └── java/        # Main source code
│   └── test/
│       └── java/        # Test source code
├── config.properties    # Configuration properties
├── pom.xml             # Maven dependencies
└── restassured.log     # Test execution logs
```

## Dependencies

The project uses the following key dependencies:

- **REST Assured (5.5.1)**: For REST API testing
- **TestNG (7.11.0)**: For test execution and assertions
- **Hamcrest (3.0)**: For fluent assertions
- **Logback (1.4.11)**: For logging
- **Jackson (2.19.0)**: For JSON processing
- **JSON Schema Validator (5.5.5)**: For JSON schema validation

## Getting Started

1. Clone the repository:
   ```bash
   git clone [repository-url]
   ```

2. Navigate to the project directory:
   ```bash
   cd CoreRestAssured
   ```

3. Install dependencies:
   ```bash
   mvn clean install
   ```

4. Run tests:
   ```bash
   mvn test
   ```

## Configuration

The project uses `config.properties` for configuration settings. Make sure to update the properties according to your environment:

```properties
# Add your configuration properties here
```

## Features

- REST API testing with REST Assured
- TestNG for test execution and reporting
- JSON schema validation
- Comprehensive logging
- JSON processing with Jackson
- Fluent assertions with Hamcrest

## Best Practices

1. **Test Organization**
   - Keep tests organized by feature or API endpoint
   - Use meaningful test names
   - Follow the Arrange-Act-Assert pattern

2. **Configuration Management**
   - Use environment-specific configuration files
   - Keep sensitive data in secure locations
   - Use environment variables for sensitive information

3. **Logging**
   - Use appropriate log levels
   - Include relevant information in logs
   - Configure log rotation

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

[Add your license information here]

## Contact

[Add your contact information here] 