# CoreRestAssured

Professional REST API testing framework using RestAssured, TestNG, and Allure reporting.

[![CI Status](https://github.com/abhi9avx/CoreRestAssured/workflows/CoreRestAssured%20CI/badge.svg)](https://github.com/abhi9avx/CoreRestAssured/actions)
[![Test Reports](https://img.shields.io/badge/Reports-Live-brightgreen)](https://abhi9avx.github.io/CoreRestAssured/reports/latest/)
[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://openjdk.java.net/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)

## Quick Start

```bash
git clone https://github.com/abhi9avx/CoreRestAssured.git
cd CoreRestAssured
mvn clean test
```

## 📊 Test Reports

**Live Reports**: [https://abhi9avx.github.io/CoreRestAssured/reports/latest/](https://abhi9avx.github.io/CoreRestAssured/reports/latest/)

Reports are automatically generated and deployed with every CI run. Features include:
- Interactive dashboards with real-time updates
- Detailed test execution history and trends  
- Mobile-responsive design for stakeholder reviews
- Comprehensive failure analysis and debugging info

## 🏗️ Project Structure

```
src/
├── main/java/com/resreq/
│   ├── pojo/              # Data models
│   └── utils/             # Utilities
└── test/java/
    ├── com/reqres/        # API Tests  
    └── com/rest/          # RestAssured Examples
```

## 🚀 Usage

### Run Tests
```bash
# All tests
mvn test

# Specific test
mvn test -Dtest=CreateUser

# Generate report
mvn test allure:serve
```

### Environment Configuration
```bash
export BASE_URL=https://reqres.in    # API endpoint
export API_KEY=your-api-key          # Authentication
```

## 🎯 Test Coverage

| Category | Tests | Coverage |
|----------|-------|----------|
| User Management | 15 | CRUD operations |
| Authentication | 3 | Login/logout |
| Validation | 8 | Schema/data validation |
| Error Handling | 5 | 4xx/5xx responses |
| Performance | 7 | Response time validation |

**Total**: 38 automated test cases

## 🔧 CI/CD Pipeline

- **Trigger**: Push to main, Pull requests
- **Runtime**: ~30-40 seconds
- **Reports**: Auto-deployed to GitHub Pages
- **Artifacts**: 30-day retention
- **Quality Gates**: All tests must pass

## 📈 Quality Metrics

- **Success Rate**: 100% (when API healthy)
- **Average Response Time**: <2s  
- **Parallel Execution**: Supported
- **Flaky Test Rate**: <1%

## 🛠️ Development

### Prerequisites
- Java 11+
- Maven 3.6+

### Contributing
1. Fork repository
2. Create feature branch
3. Write/update tests
4. Ensure CI passes
5. Submit pull request

---

**Enterprise-grade API testing with professional reporting and CI/CD integration.**