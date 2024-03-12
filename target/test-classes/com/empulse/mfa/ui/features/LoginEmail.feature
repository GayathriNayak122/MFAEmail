Feature: Login Feature
 @Login
  Scenario: Successful Login with OTP
    Given User is on Home Page
    When User navigates to Login Page
    And User enters Credentials to LogIn
    Then User receives OTP
    And User enters OTP
    Then Message displayed Login Successfully
   