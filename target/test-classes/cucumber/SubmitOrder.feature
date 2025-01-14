@tag
Feature: Purchase the order from eCommerce website

	Background:
	Given I landed on eCommerce page
	
  @tag2
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <username> and password <password>
    When I add the product <productName> to cart
    And checkout the <productName> by selecting <countryName> and submit the order
    Then "THANKYOU FOR THE ORDER." message should be displayed in confirmationPage

    Examples: 
      | username                | password    | productName | countryName |
      | svishal.bhat7@gmail.com | Nimmibadri7 | ZARA COAT 3 | India       |
