# REST Assured API Testing Framework with Allure Reporting

This project demonstrates API test automation using **REST Assured**, **TestNG**, and **Allure Reporting**. It's designed to be a clear example of how to structure API tests, use Plain Old Java Objects (POJOs) for data handling, and generate detailed reports.

## Key Components

*   **REST Assured**: For making HTTP requests and validating responses.
*   **TestNG**: The test automation framework.
*   **Allure Reporting**: For generating rich, interactive test reports.
*   **POJO Classes (`src/main/java/com/resreq/pojo`)**: Java objects that mirror API request/response structures for easy data handling.
*   **Base Test Class (`src/test/java/com/reqres/base/BaseTest.java`)**: Centralizes common configurations like the base URL for all tests.
*   **Test Cases (`src/test/java/com/reqres/test/`)**: Examples of GET requests for user data, including:
    *   `GetUserListTest.java`: Fetches a list of users.
    *   `GetUserTest.java`: Fetches a single existing user.
    *   `GetUserNotFoundTest.java`: Verifies behavior for a non-existent user.

## Prerequisites

*   Java Development Kit (JDK) 8 or higher
*   Apache Maven
*   Allure Command Line Tool

    ```bash
    # Install Allure (example for macOS)
    brew install allure
    ```

## Project Structure

This project follows a standard Maven project structure, organized to separate source code from test code and common utilities.

```
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── resreq/        # Main application source code (e.g., POJOs)
│   │               └── pojo/      # Plain Old Java Objects for API request/response
│   │                   ├── UserListResponse.java
│   │                   └── UserResponse.java
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── reqres/        # Test source code
│       │           ├── base/      # Base test configuration (e.g., BaseTest.java)
│       │           │   └── BaseTest.java
│       │           └── test/      # API test classes
│       │               ├── GetUserListTest.java
│       │               ├── GetUserNotFoundTest.java
│       │               └── GetUserTest.java
│       └── resources/
│           └── config.properties  # Configuration file for API properties
├── docs/
│   └── reports/                   # Directory for storing sample report screenshots
│       ├── overview.png
│       ├── test-results.png
│       └── test-details.png
├── pom.xml                        # Maven Project Object Model file
├── testng.xml                     # TestNG Suite XML file for test execution
└── README.md
```

## Running Tests and Generating Allure Report

To run all tests and generate the Allure report, follow these steps:

1.  **Run Tests:** Execute all tests defined in `testng.xml`.

    ```bash
    mvn clean test -Dsurefire.suiteXmlFiles=testng.xml
    ```

2.  **Generate Report:** Create the interactive HTML report from the test results.

    ```bash
    allure generate target/allure-results -o target/allure-report --clean
    ```

3.  **View Report:** Open the generated Allure report in your web browser.

    ```bash
    allure serve target/allure-results
    ```

    *Note: The `target/allure-results/` and `target/allure-report/` directories are git-ignored as they contain generated files.*

### Sample Report Screenshot

Here's an example of what your Allure report might look like:

![Allure Report Overview](docs/reports/allure-report-overview.png)

## Allure Report Features

The Allure report provides a clear overview of test execution, including:

*   **Overview Dashboard**: Summary of tests, pass/fail rates.
*   **Detailed Test Steps**: Thanks to Allure annotations (`@Step`, `@Feature`, `@Epic`, `@Story`, `@Description`, `@Severity`), you can see granular actions and categorizations for each test.
*   **Interactive Navigation**: Filter and search through test results easily.

## Troubleshooting

*   If the report shows "0 test cases", ensure `mvn clean test -Dsurefire.suiteXmlFiles=testng.xml` ran successfully and `target/allure-results` exists. Also, verify `testng.xml` is correctly set up.

## Contributing

Feel free to submit issues and enhancement requests!