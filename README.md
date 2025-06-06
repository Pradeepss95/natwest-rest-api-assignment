# natwest-rest-api-assignment

# 🧪 NatWest REST API Test Automation Framework

This project is a modular and scalable API Test Automation Framework built using:

- ✅ [Cucumber](https://cucumber.io/)
- ✅ [Spring Boot](https://spring.io/projects/spring-boot)
- ✅ [TestNG](https://testng.org/)
- ✅ [Rest Assured](https://rest-assured.io/)
- ✅ [Maven](https://maven.apache.org/)
- ✅ [Maven Sure Fire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)

It is designed to test RESTful APIs in a BDD-style with readable and maintainable test scenarios.

---

## 🚀 Getting Started

### 📦 Prerequisites

Ensure you have the following installed:

- Java 8 or higher
- Maven 3.6+
- Git

---

## 🔧 Installation


### Step 1: Clone the repository
git clone https://github.com/Pradeepss95/natwest-rest-api-assignment.git

### Step 2: Navigate into the project directory
cd natwest-rest-api-assignment

### Step 3: Download project dependencies
```
mvn clean install -DskipTests
```
---
## 🧪 How to Run Tests


✅ Using Maven from Command Line

You can run the tests using the TestNG runner via Maven:
```
mvn test -DsuiteXmlFile=testng.xml
```

You can run the tests in different envirnment using Spring profile
```
mvn test -Dspring.profiles.active=dev
```

✅ Directly Using TestNG Runner Class
If you want to run the TestNGTestRunner class directly (for example, in an IDE like IntelliJ or Eclipse):

---
## 🧩 Features

- Modular architecture with Cucumber + TestNG integration
- Centralized configuration using Spring Boot
- Clean separation of concerns (features, steps, config, runners)
- Readable Gherkin syntax for API scenarios
- JSON response assertions with Rest Assured
- Base URLs and other configs can be injected via Spring Boot’s application.properties.
- You can also override these at runtime using command line arguments or environment variables.

---

## 📃 Reporting

TestNG default reports are generated under target/surefire-reports/
Cucumber JSON reports (if configured) can be found under target/cucumber-reports/