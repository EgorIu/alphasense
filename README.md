# AlphaSense testing assignment

## To run tests, type the following in terminal:

`mvn clean test -Dselenide.browser=chrome -Dselenide.baseUrl=https://rc.alpha-sense.com -Dalphasense.baseUrl=https://rc.alpha-sense.com`

where,

- **selenide.baseUrl** is the url for browser based (UI) tests
- **alphasense.baseUrl** is the url for API test (optional, as it is already specified in the property file but can be
  overridden through command line )

## To open the test report, run:

`mvn allure:serve`



