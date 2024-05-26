# magento-test-demo

Automated test for the e-commerce site at https://magento.softwaretestingboard.com.

## What does it do?
- It enters "watch" in the Home Page search bar & tests that the first page of the search results has the correct watches.
- It enters "bottle" in the Home Page searc bar & tests that the first page of the search results has the correct bottle-related products (bottles & products that contain "bottle" in the descriptions).

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
```
### Page Object Models
```
src/test/java/com/example/magento_test_demo/pages
   HomePage.java
   SearchResultPage.java
```
