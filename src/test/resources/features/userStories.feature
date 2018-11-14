Feature: Operations
  As a client
  I want to save my money in a bank account by making a deposit


  Scenario Outline: Make a deposit
    Given A client with a bank account and a balance of <amount>
    When He makes a deposit of <money> on <date>
    Then the account balance should be updated with <value>
    Then a deposit operation is added to the history like this
    |operation | deposit |
    |operationDate | <date> |
    |operationAmount | <amount> |
    |operationBalance | <value> |

    Examples:
      | amount | money  |  value | date |
      | 0      | 100    |  100   | 01/01/2017 |
      | 1000   | 500    |  1500  | 02/01/2017 |
      | 1500   | 100    |  1600  | 03/01/2018 |


  Scenario Outline: Make a withdrawal
    Given A client with a bank account and a balance of <amount>
    When He makes a withrawal of <money>
    Then the account balance should be updated with <value>

    Examples:
      | amount | money  |  value |
      | 0      | 50     |  -50   |
      | 1000   | 500    |   500  |
      | 1500   | 100    |  1400  |


  Scenario Outline: Check operations
    Given A client with a bank account with following operations
     | operation | date       | amount | balance |
     | deposit   | 10/01/2017 | 100    | 200     |
     | withdrawal| 01/01/2018 | 50     | 150     |
    When He wants to check operations
    Then the history of all operations should be printed like this

    Examples:
      | amount | money  |  value |
      | 0      | 50     |  -50   |
      | 1000   | 500    |   500  |
      | 1500   | 100    |  1400  |


