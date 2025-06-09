# REST Assured API Testing Framework with Allure Reporting

This project is a REST API testing framework built using REST Assured, TestNG, and Allure for beautiful test reporting.

## Quick Start

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd CoreRestAssured
   ```

2. **Install Allure** (if not already installed)
   ```bash
   # For macOS
   brew install allure
   
   # For Windows
   scoop install allure
   
   # For Linux
   sudo apt-add-repository ppa:qameta/allure
   sudo apt-get update
   sudo apt-get install allure
   ```

3. **Run Tests and Generate Report**
   ```bash
   # Run tests
   mvn clean test
   
   # Generate and view report
   allure serve target/allure-results
   ```

## Documentation

### Sample Reports
Check out the sample reports in the `docs/reports` directory to see what the Allure reports look like:
- [Sample Report Overview](docs/reports/overview.png)
- [Test Results](docs/reports/test-results.png)
- [Test Details](docs/reports/test-details.png)

### Report Generation

1. **Generate Results**
   ```bash
   mvn clean test
   ```
   This will:
   - Run all tests
   - Generate test results in `target/allure-results/`
   - (Note: This directory is git-ignored as it contains generated files)

2. **View Report**
   ```bash
   allure serve target/allure-results
   ```
   This will:
   - Start a local web server
   - Open the report in your default browser
   - Show an interactive, beautiful report

3. **Generate Static Report** (for sharing)
   ```bash
   allure generate target/allure-results -o target/allure-report
   ```
   This will:
   - Generate a static HTML report
   - Save it in `target/allure-report/`
   - (Note: This directory is git-ignored as it contains generated files)

### Important Notes

1. **Generated Directories**
   The following directories are automatically generated and should not be committed to Git:
   - `target/allure-results/` - Raw test results
   - `target/allure-report/` - Generated HTML report
   - `.allure/` - Allure framework files

2. **Viewing Reports**
   - Always use `allure serve` for the best experience
   - The report is interactive and provides detailed test information
   - You can filter, search, and analyze test results

3. **Sharing Reports**
   - Use `allure generate` to create shareable reports
   - Share the generated HTML files
   - Or use Allure's reporting plugins in CI/CD

## Project Structure

```
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── reqres/
│   │               ├── base/
│   │               ├── pojo/
│   │               └── utils/
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── reqres/
│       │           └── test/
│       └── resources/
│           └── config.properties
├── docs/
│   └── reports/
│       ├── overview.png
│       ├── test-results.png
│       └── test-details.png
├── pom.xml
└── README.md
```

## Report Features

1. **Overview Dashboard**
   - Test execution summary
   - Pass/Fail statistics
   - Duration trends
   - Environment information

2. **Test Details**
   - Step-by-step test execution
   - Request/Response data
   - Error messages (if any)
   - Test duration

3. **Interactive Features**
   - Filterable results
   - Search functionality
   - Expandable test steps
   - Detailed test information

## Best Practices

1. **Running Tests**
   - Always use `mvn clean test` to ensure clean results
   - Check the console output for immediate feedback
   - Use `allure serve` to view detailed results

2. **Viewing Reports**
   - Use the interactive features to analyze results
   - Check the Overview dashboard first
   - Drill down into specific tests as needed

3. **Sharing Results**
   - Generate static reports for sharing
   - Include relevant screenshots in documentation
   - Use CI/CD integration for automated reporting

## Troubleshooting

1. **Report Not Opening**
   - Ensure Allure is installed correctly
   - Check if tests ran successfully
   - Verify the results directory exists

2. **No Test Results**
   - Run `mvn clean test` first
   - Check if tests are configured correctly
   - Verify Allure listener is properly set up

## Additional Resources

- [Allure Framework Documentation](https://docs.qameta.io/allure/)
- [REST Assured Documentation](https://rest-assured.io/)
- [TestNG Documentation](https://testng.org/doc/)

## Contributing

Feel free to submit issues and enhancement requests!

## Prerequisites

- Java JDK 8 or higher
- Maven
- Allure Command Line Tool

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

## Understanding Report Generation and Storage

### Report Generation Process

1. **Test Execution and Results Collection**
   - When you run `mvn clean test`, the following happens:
     - Tests are executed
     - Allure listener collects test results
     - Results are stored in `target/allure-results/` directory
     - Each test generates a JSON file with test details

2. **Report Generation**
   - When you run `allure serve` or `allure generate`:
     - Allure reads the JSON files from `target/allure-results/`
     - Processes the data
     - Generates HTML report

### File Locations

1. **Test Results (Not in Git)**
   - Location: `target/allure-results/`
   - Contains: Raw test execution data in JSON format
   - Generated: During test execution
   - Git Status: Ignored (in .gitignore)

2. **Generated Report (Not in Git)**
   - Location: `target/allure-report/` (when using `allure generate`)
   - Contains: HTML report files
   - Generated: When running `allure generate`
   - Git Status: Ignored (in .gitignore)

3. **Temporary Report (Not in Git)**
   - Location: Temporary directory (when using `allure serve`)
   - Contains: HTML report files
   - Generated: When running `allure serve`
   - Git Status: Not tracked (temporary files)

### Why Reports Are Not in Git

The report files are not stored in Git because:
1. They are generated files that can be recreated
2. They can be large in size
3. They are environment-specific
4. They change with every test run

### How to Share Reports

1. **For Team Members**
   - Each team member can generate reports locally using:
     ```bash
     mvn clean test
     allure serve target/allure-results
     ```

2. **For CI/CD Pipeline**
   - Add these steps to your pipeline:
     ```yaml
     - mvn clean test
     - allure generate target/allure-results
     - allure report:deploy
     ```

3. **For External Sharing**
   - Generate a static report:
     ```bash
     allure generate target/allure-results -o target/allure-report
     ```
   - Share the contents of `target/allure-report/` directory
   - The report can be opened by anyone with a web browser

### Report Generation Commands Explained

1. **Generate Results**
   ```bash
   mvn clean test -Dallure.results.directory=target/allure-results
   ```
   - Cleans previous build
   - Runs tests
   - Stores results in `target/allure-results/`

2. **Serve Report (Interactive)**
   ```bash
   allure serve target/allure-results
   ```
   - Generates report in temp directory
   - Starts local web server
   - Opens in browser
   - Report is temporary

3. **Generate Static Report**
   ```bash
   allure generate target/allure-results -o target/allure-report
   ```
   - Generates report in specified directory
   - Creates static HTML files
   - Can be shared or archived 

## Report Appearance and Navigation

### What You'll See in the Report

The Allure report provides a beautiful, interactive interface with the following sections:

1. **Overview Dashboard**
   - Test execution summary
   - Pass/Fail statistics
   - Duration trends
   - Environment information

2. **Categories View**
   - Test categorization
   - Failure analysis
   - Custom categories

3. **Suites View**
   - Test suite organization
   - Detailed test information
   - Execution history

4. **Graphs**
   - Test execution trends
   - Duration distribution
   - Status breakdown

5. **Timeline**
   - Chronological test execution
   - Parallel execution visualization

### Important Note About Directories

You might notice these directories in your project:
- `.allure/` - Contains Allure framework files (can be safely ignored)
- `target/allure-results/` - Contains raw test results (JSON files)
- `target/allure-report/` - Contains the generated HTML report

**Do not commit these directories to Git!** They are:
- Generated automatically
- Can be recreated
- May contain environment-specific data

### How to View the Report

1. **Interactive View (Recommended)**
   ```bash
   allure serve target/allure-results
   ```
   This opens a beautiful, interactive report in your browser.

2. **Static Report**
   ```bash
   allure generate target/allure-results -o target/allure-report
   ```
   This generates a static HTML report that can be shared.

### Report Features

1. **Interactive Elements**
   - Expandable test steps
   - Filterable results
   - Search functionality
   - Detailed test information

2. **Test Details**
   - Test steps
   - Request/Response data
   - Environment information
   - Test duration
   - Error messages (if any)

3. **Visual Elements**
   - Status indicators
   - Progress bars
   - Charts and graphs
   - Timeline visualization

### Best Practices for Report Sharing

1. **For Team Members**
   - Use `allure serve` for local viewing
   - Share screenshots of important findings
   - Use the report's export features

2. **For CI/CD**
   - Configure your pipeline to generate and host reports
   - Use Allure's reporting plugins
   - Set up report archiving

3. **For Documentation**
   - Take screenshots of key report sections
   - Document important metrics
   - Share report URLs when available 