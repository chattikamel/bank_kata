Feature: Operations
  As a client
  I want to save my money in a bank account by making a deposit


  Scenario Outline: Make a deposit
    Given A client with a bank account and a balance of <amount>
    When He makes a deposit of <money>
    Then the account balance should be updated with <value>

    Examples:
      | amount | money  |  value |
      | 0      | 100    |  100   |
      | 1000   | 500    |  1500  |
      | 1500   | 100    |  1600  |


  Scenario Outline: Make a withdrawal
    Given A client with a bank account and a balance of <amount>
    When He makes a withrawal of <money>
    Then the account balance should be updated with <value>

    Examples:
      | amount | money  |  value |
      | 0      | 50     |  -50   |
      | 1000   | 500    |   500  |
      | 1500   | 100    |  1400  |
