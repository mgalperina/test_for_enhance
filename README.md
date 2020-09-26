# test_for_enhance

This is a tech task from Enhance.


Prerequisites for UI tests:

- install a geckodriver to run tests in a Firefox browser
- in the file TradeMeMotorsStepsUI.java in the first @Given step put the path to your installed geckodriver
- install all necessary Cucumber plugins and jars
- install Selenium UI Automation Testing plugin
- 


Note: This task contains checks in Used Cars category and Motorbikes category as at the moment of writing tests there was no search results for cars.

Note: I couldn't use some of the required attributes from the task for test #3 to check as it wasn't available in the listings so I added different UI elements to check. 

Note: In the tests I could've used Page object model too. But since I wrapped part of locators into arguments to pass in tests from .feature files, that became sort of redundant. 



Prerequisites for API tests:

- get the token from trade me sandbox environment
- 

Note: when I used Rest Assured for tests, it felt like doing it with Cucumber is not necessary as the steps in this framework are already in Cucumber format, so not sure how Enhance prefers this part.

Note: 

