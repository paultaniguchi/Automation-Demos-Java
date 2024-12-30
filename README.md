# magento-test-demo

Automated tests for the e-commerce site at https://magento.softwaretestingboard.com.

## What does it do?
- It enters "watch" in the Home Page search bar & tests that the first page of the search results has the correct watches.
- It enters "bottle" in the Home Page search bar & tests that the first page of the search results has the correct bottle-related products (bottles & products that contain "bottle" in the descriptions).
- It signs into an account & tests that the correct account name appears in the header.  Prior to running the test, the account must be created in magento.  Environment variables MagentoUsername & MagentoPassword must be created on the test machine & set to the account credentials. 

## Dependencies
- JDK 17
- Maven
- TestNG
- Selenium

## Files
### Tests
```
src/test/java/com/example/magento_test_demo 
   HomePageTests.java
   LoginPageTests.java
```
### Page Object Models
```
src/test/java/com/example/magento_test_demo/pages
   HomePage.java
   LoginPage.java
   SearchResultPage.java
```
