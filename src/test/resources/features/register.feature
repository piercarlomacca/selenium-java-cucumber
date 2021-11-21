@register
Feature: As a registered user I want to be able to register a new user in etherscan app

  Scenario Outline: An user can not register with invalid credentials
    Given I visit etherscan app registration page
    When I am on registration page
    And I try to register using username "<username>", email "<email>", password "<password>", password confirm "<pwconfirm>", and term and conditions <terms>
    Then the registration is "<registrationstatus>"
    And an error is displayed on the field "<invalidfield>"

    Examples:
      | username      | email           | password        | pwconfirm       | terms | registrationstatus | invalidfield |
      | aaaa          | valid@email.com | k3riUzaqAxVVgz! | k3riUzaqAxVVgz! | true  | unsuccessful       | username     |
      | rightusername | notamail        | k3riUzaqAxVVgz! | k3riUzaqAxVVgz! | true  | unsuccessful       | email        |
      | rightusername | valid@email.com | pwdp         		| k3riUzaqAxVVgz! | true  | unsuccessful       | password     |
      | rightusername | valid@email.com | aaaaaaaaaaaaaa  | k3riUzaqAxVVgz! | true  | unsuccessful       | pwconfirm    |
      | rightusername | valid@email.com | k3riUzaqAxVVgz! | k3riUzaqAxVVgz! | false | unsuccessful       | terms        |

