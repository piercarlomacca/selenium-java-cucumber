# Selenium-Cucumber automated tests with Java #

This code aims to implement some test cases for the register page of etherscan (https://etherscan.io/register).  
I wanted to try Cucumber with Selenium on Java, and I used Maven to take care of the project dependencies. I decided to use Java and Maven because they are easy to manage and to integrate on CI builds. 

## Scenarios ##
On the register.feature file there is a scenario to test the different contraints on the fields of the register form.

One important test missing is the test of the happy path of the register with allowed credentials. This scenario would be the first to test but, due to the presence of a CAPTCHA, automation would need to have that disabled for testing purposes. CAPTCHA is designed to avoid people inserting data automatically, which is what we do with selenium, so trying to trick the mechanism (with sleeps?) would render the test less reliable and slower.

I tested that warnings are raised when trying to:

- insert an username having less than 5 characters.
- insert an e-mail malformed.
- insert a password having less than 8 characters.
- confirm a wrong password.
- proceed without accepting the terms and conditions. 

All those checks have been inserted on a single scenario outline.  

#### Additional tests ####
I would also like to automate (but not using Selenium):
* API tests. Like trying to send wrong/malformed credentials to the APIs to see if the backend checksare also in place.

it would be nice also to:
* Test from other supported devices (if any).
* Test on other browsers.
* Test the page layout with different resolutions.
* Make available some utlilities to check element visibility or presence or clickability for use on Steps files.
* Documentation on code.

#### Issues ####
For some reason on my machine the --headless flag of chromedriver was not working properly, while without that option all tests were good. Could be something related to my machine, let me know if headless works for you.

## Installation ##

### Requirements ###

* [Java 8 JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
* [maven](https://maven.apache.org/download.cgi).
* [Google Chrome](https://www.google.com/chrome) - Tested on Version 96.0.4664.45

### Steps ###
* Download [ChromeDriver](http://chromedriver.chromium.org) - Tested on Version 96.0.4664.45
* The location must be added to system PATH.
* `webdriver.chrome.driver=path/to/the/driver` must be added to local variables or passed as an option when running tests.


#### Install dependencies and run tests ####
From project folder

```console
$ mvn clean install
```

## Run tests ##
From project folder

```console
$ mvn test
```
  
Here you can also pass the option `-Dwebdriver.chrome.driver=path/to/the/driver` to the command if you didn't set it as a local variable.


