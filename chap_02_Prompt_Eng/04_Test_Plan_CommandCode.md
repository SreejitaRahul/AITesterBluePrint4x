# Salesforce Login — Enterprise Selenium + Java + Maven + TestNG Framework

## Goal
Build an enterprise-level Selenium WebDriver framework under `chap_02_Prompt_Eng` that automates and verifies the Salesforce login page (`https://login.salesforce.com/?locale=in`) with valid and invalid test cases, using Page Object Model with `PageFactory`, XPath-only locators, TestNG annotations, robust exception handling, and zero `Thread.sleep()`.

## Deliverables (chosen scope: Enterprise-standard)
- Maven project (`pom.xml`) — Selenium 4.x, TestNG 7.x, WebDriverManager 5.x, Java 17
- `config.properties` — external URL + valid/invalid credentials (stub values; user can swap external staging values)
- Page Object: `LoginPage.java` (XPath-only `@FindBy`, constructor init, reusable action methods)
- Base classes: `BasePage.java` (driver init + `PageFactory`), `BaseTest.java` (`@BeforeTest`/`@AfterTest` setup/teardown)
- Utilities: `ConfigReader.java`, `WebDriverFactory.java`, `WaitUtils.java` (explicit `WebDriverWait`), `ExceptionHandler.java` (structured try–catch + custom exception `LoginException`)
- TestNG tests: `ValidLoginTest.java` (comprehensive: valid creds, remember-me, post-login landing assert) and `InvalidLoginTest.java` (invalid username, invalid password, blank fields, error-message assertions)
- `testng.xml` — test suite with both classes; Surefire wired to run it
- No CSS selectors, no IDs/names, no comments, no `Thread.sleep()`

## File Tree (under `chap_02_Prompt_Eng`)
```
chap_02_Prompt_Eng/
├── pom.xml
├── testng.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   ├── config/ConfigReader.java
    │   │   ├── pages/LoginPage.java
    │   │   ├── base/BasePage.java
    │   │   ├── drivers/WebDriverFactory.java
    │   │   ├── utils/WaitUtils.java
    │   │   ├── utils/ExceptionHandler.java
    │   │   └── exceptions/LoginException.java
    │   └── resources/config.properties
    └── test/
        └── java/
            ├── base/BaseTest.java
            └── tests/
                ├── ValidLoginTest.java
                └── InvalidLoginTest.java
```

## Key Files & Implementation Notes

### pom.xml
- `maven.compiler.release=17`, JUnit exclusion (TestNG only)
- Dependencies: selenium-java 4.21+, testng 7.10+, webdrivermanager 5.9+
- Surefire plugin: `suiteXmlFiles` → `testng.xml`

### config.properties
- `url=https://login.salesforce.com/?locale=in`
- `valid.username=`, `valid.password=`, `invalid.username=`, `invalid.password=`, `rememberme=true` — stubbed, user replaces with external staging values
- Timeouts: `implicit.timeout=10`, `explicit.timeout=20`, `page.load.timeout=30`

### LoginPage.java
- `@FindBy(xpath="//input[@id='username']")` etc. — XPath only, never CSS/ID/name attributes
- Constructor: `PageFactory.initElements(driver, this)`
- Actions: `enterUsername`, `enterPassword`, `clickLogin`, `doLogin` (valid), `doLoginWithRememberMe`, `isErrorDisplayed`, `getErrorMessage`, `isRememberMeSelected`
- Each method wraps in try–catch → delegates to `ExceptionHandler` → rethrows `LoginException` on failure

### BasePage.java
- Holds `WebDriver driver`, `WebDriverWait wait`; constructor takes driver, calls `PageFactory.initElements` for subclass
- `WaitUtils` helpers: `waitForElementVisible`, `waitForElementClickable`, `waitForText`

### BaseTest.java
- `@BeforeTest`: read config, instantiate driver via `WebDriverFactory` (WebDriverManager-chrome), maximize, set implicit wait, navigate to URL
- `@AfterTest`: close/quit driver
- Provides `driver` to test classes via protected field/getter

### TestNG tests
- Extend `BaseTest`, use `LoginPage` methods, assert with TestNG `Assert`
- ValidLoginTest: 2–3 methods — successful login, remember-me toggle, landing/URL assertion after login
- InvalidLoginTest: 2–3 methods — wrong password, wrong username, blank submit — assert `isErrorDisplayed` + error text
- Login URL can be flaky for a real Salesforce org (SSO/2FA); tests are written against the public login page; post-login assertion is tolerant (checks URL contains `salesforce` or `lightning`/`my.salesforce` OR presence of known post-login element; if user supplies real org creds, adjust assertion)

## Reuse
- WebDriverManager for driver binaries (no manual chromedriver)
- TestNG built-in assertions; Selenium `ExpectedConditions` in WaitUtils
- No external test data file needed beyond config.properties

## Verification
1. `cd chap_02_Prompt_Eng && mvn clean test` (requires Chrome + internet; external creds must be filled in config.properties for valid-login to pass)
2. Confirm Surefire runs `testng.xml`, both classes execute, invalid tests pass (assert error messages), valid test passes with real creds
3. Sanity: no `Thread.sleep` anywhere, no CSS selectors, no IDs/names in locators

## Out of Scope (can add later)
- DataProvider-driven CSV/Excel data
- Cross-browser grid (Selenium Grid / BrowserStack)
- Reporting (ExtentReports), logging (Log4j2)
- CI integration
