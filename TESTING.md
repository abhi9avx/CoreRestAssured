# Testing Documentation

## 📊 Test Reporting Strategy

### **🌐 Live Reports (Production)**
Our testing framework provides **real-time, interactive reports** that are automatically generated and deployed:

**Live Report URL**: `https://abhi9avx.github.io/CoreRestAssured/reports/latest/`

#### Features:
- 🔄 **Auto-updated** with every main branch push
- 📱 **Mobile-responsive** design  
- 📊 **Interactive dashboards** with trends and analytics
- 🎯 **Test case drill-down** with detailed execution steps
- 📈 **Historical trends** and success rates
- 🔍 **Detailed failure analysis** with screenshots and logs

### **📦 Artifact Reports (Backup)**
Every CI run also generates downloadable artifacts:

| Artifact | Description | Use Case |
|----------|-------------|----------|
| `allure-report` | Complete HTML report | Offline viewing, archival |
| `test-results` | Raw TestNG/Surefire data | Integration with tools |
| `build-logs` | Execution logs | Debugging CI issues |

### **🛠️ Local Development**
Generate reports during development:

```bash
# Quick test run with report
mvn clean test allure:serve

# Generate static report
mvn test allure:report
open target/site/allure-maven-plugin/index.html

# Run specific test suite
mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml
```

## 🎯 Testing Best Practices

### **Test Organization**
```
src/test/java/
├── com/reqres/          # API-specific tests
│   ├── base/           # Base test classes
│   └── test/           # Test implementations
└── com/rest/           # RestAssured examples
    ├── AutomateGet.java
    ├── AutomatePost.java
    └── ...
```

### **Report Integration Points**

#### **1. GitHub Actions Integration**
- Reports deploy automatically to GitHub Pages
- Artifacts uploaded for 30-day retention
- Failed builds include detailed logs

#### **2. Local Development**
- Live reload with `mvn allure:serve`
- Static reports for sharing
- CI-identical test execution

#### **3. Team Collaboration**
- Shared report URL for stakeholders
- Historical trend analysis
- Test coverage metrics

## 📈 Report Contents

### **Dashboard Overview**
- ✅ **Test Execution Summary**: Pass/fail counts, duration
- 📊 **Trend Analysis**: Success rate over time  
- 🎯 **Test Categories**: Organized by functionality
- ⚡ **Performance Metrics**: Response times, slowest tests

### **Detailed Test Results**
- 🔍 **Step-by-step execution**: Request/response details
- 📝 **Assertions and validations**: What was verified
- 🚫 **Failure analysis**: Screenshots, stack traces
- 📊 **Environment information**: Test configuration

### **Historical Data**
- 📈 **Trend graphs**: Test stability over time
- 🏆 **Flaky test detection**: Inconsistent test identification
- 📊 **Coverage metrics**: API endpoint coverage

## 🔧 Configuration

### **Environment Variables**
```bash
BASE_URL=https://reqres.in          # API endpoint
API_KEY=your-api-key               # Authentication
CI=true                            # CI environment detection
```

### **Report Customization**
Modify `allure.properties` for custom branding:
```properties
allure.results.directory=target/allure-results
allure.report.title=CoreRestAssured API Tests
allure.report.environment=Production
```

## 🚀 Deployment

### **Automatic Deployment**
Reports are automatically deployed via GitHub Actions:

1. **Trigger**: Push to `main` branch
2. **Build**: Run all tests + generate reports  
3. **Deploy**: Upload to GitHub Pages
4. **Notify**: Available at live URL within 2-3 minutes

### **Manual Deployment**
For testing the deployment process:

```bash
# Generate report locally
mvn clean test allure:report

# Test the report
open target/site/allure-maven-plugin/index.html

# Commit and push to trigger deployment
git add . && git commit -m "Update tests" && git push
```

---

## 💡 Why This Approach?

### **❌ Old Approach (Static Screenshots)**
- Outdated information
- No interactivity  
- Manual updates required
- Limited detail
- Not mobile-friendly

### **✅ New Approach (Live Reports)**
- Always current
- Interactive exploration
- Automatic updates
- Rich detail and context
- Professional presentation
- Mobile-responsive
- Historical trending

**Result**: Professional, maintainable, and valuable test reporting that actually helps the team make decisions. 