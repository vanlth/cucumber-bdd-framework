@register_check_infor
Feature: register_check_infor

@Register
  Scenario Outline: Register testcase
  	Given Open Register page
  	When Click to "Register" button
  	Then Verify Error Empty FirtName message is displayed
  	
    When Reload Register page
    And Input infor invalid to Register Form
      | FirstName   | LastName   | Email             | Password   | ConfirmPassword | 
      | <FirstName> | <LastName> | 123456 | <Password> | <Password>      |     | 
    Then Verify Error Invalid Email message is displayed
    
    When Reload Register page
    And Click to "Male" radio button
    And Input to "FirstName" textbox with value "<FirstName>"
    And Input to "LastName" textbox with value "<LastName>"
    And Select to "DateOfBirthDay" dropdown with value "<DateOfBirthDay>"
    And Select to "DateOfBirthMonth" dropdown with value "<DateOfBorthMonth>"
    And Select to "DateOfBirthYear" dropdown with value "<DateOfBirthYear>"
    And Input to "Email" textbox with value "<Email>"
    And Input to "Company" textbox with value "<Company>"
    And Check to "Newsletter:" checkbox
    And Input to "Password" textbox with value "<Password>"
    And Input to "ConfirmPassword" textbox with value "<Password>"
    And Click to "Register" button
    Then Verify Success Register Message is displayed
    When Click to logout link
    Given Open Login Page
 		When Input to "Email" textbox with value "<Email>"
    And Input to "Password" textbox with value "<Password>"
    And Click to "Log in" button
    Given Open Custommer Infor page
    Then The valid text is displayed at "FirstName" with value "<FirstName>"
    Then The valid text is displayed at "LastName" with value "<LastName>"
    Then The valid text is displayed at "Email" with value "<Email>"
    Then The valid text is displayed at "Company" with value "<Company>"
    
   Examples:
      | FirstName | LastName | DateOfBirthDay | DateOfBorthMonth | DateOfBirthYear | Email       | Company | Password | 
      | Auto      | Test     | 3              | January          | 1997            | autotesting | Omi     | 123456   | 
