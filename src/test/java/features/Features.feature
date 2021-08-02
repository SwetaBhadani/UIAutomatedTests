Feature: Test the home page functionality and UI for "The Collective"

  Background: Open the website with the given URL
    Given As a user, I open the website

  Scenario: Verify "Our Location" link function
    When I click on "Our Location" link
    Then I get the locations displayed
    And I verify the locations with the below list
      | Old Oak       |
      | Canary Wharf  |
      | Harrow        |
      | Paper Factory |

  Scenario: Verify booking stay for months
    When I click on "Move in for months" button
    And I provide the below details
      | Full Name   | Contact Number | Email Address  | Location | Move In      |
      | Krish Kumar | 07865346781    | KrishN@yes.com | Old Oak  | 9th Aug 2021 |
    And I click on "Request Information" button
    Then I verify if I see below options
      | Schedule a call with our team |
      | Take our 360 tour             |

  Scenario: Verify booking stay for months when entering invalid details
    When I click on "Move in for months" button
    And I provide the below details
      | Full Name | Contact Number | Email Address | Location | Move In      |
      | James B   | 12AB@3489374   | Hello@xyz     | Old Oak  | 9th Aug 2021 |
    Then I see error message saying "Please enter a valid phone number." for invalid "Contact Number"
    And I see error message saying "Please enter a valid email address." for invalid "Email Address"
    And "Request Information" button remains disabled


