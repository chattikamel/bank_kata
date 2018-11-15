package com.katabank.steps;


import com.katabank.domain.model.Account;
import com.katabank.domain.model.Operation;
import com.katabank.domain.model.OperationType;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;

public class OperationTestSteps {

    private Account account;

    @Given("A client with a bank account and a balance of {}")
    public void a_client_with_a_bank_account_and_a_balance(BigDecimal blance) {

        account = new Account();
        account.setBalance(blance);
    }

    @When("He makes a deposit of {} on {iso-date}")
    public void he_makes_a_deposit_of_on(BigDecimal amount, Date operationDate) {
        account.makeDeposit(amount, operationDate);
    }

    @When("He makes a withdrawal of {} on {iso-date}")
    public void He_makes_a_withdrawal_of (BigDecimal amount, Date operationDate) {
        account.makeWithdrawal(amount, operationDate);
    }

    @Then("the account balance should be updated with {}")
    public void the_account_s_balance_should_be_updated_with(BigDecimal newBalanceAmount) {
        assertThat( account.getBalance(), closeTo(newBalanceAmount, new BigDecimal(0)));

    }
    @Then("a deposit operation is added to the history with these values")
    public void a_deposit_operation_is_added_to_the_history(io.cucumber.datatable.DataTable dataTable) throws ParseException {
        assertThat(account.getLastOperation(), is(convertToOperation(dataTable.cell(0,1), dataTable.cell(1,1), dataTable.cell(2,1), dataTable.cell(3,1))));
    }

    @Then("a withdrawal operation is added to the history with these values")
    public void a_wihdrawal_operation_is_added_to_the_history(io.cucumber.datatable.DataTable dataTable) throws ParseException {
        assertThat(account.getLastOperation(), is(convertToOperation(dataTable.cell(0,1), dataTable.cell(1,1), dataTable.cell(2,1), dataTable.cell(3,1))));
    }


    @Given("A client with a bank account with following operations")
    public void A_client_with_a_bank_account_with_following_operations(io.cucumber.datatable.DataTable dataTable)  {
        account = new Account();
        List<Operation> operations =  account.getOperations();
        dataTable.asLists().stream().skip(1).forEach(list ->  {
            Operation operation = null;
            try {
                operation = convertToOperation(list.get(0), list.get(1), list.get(2), list.get(3));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            operations.add(operation);

        });



    }

    @When("He wants to check operations")
    public void He_wants_to_check_operations () {


    }

    @Then("the history of all operations should be printed like this")
    public void the_history_of_all_operations_should_be_printed_like_this (io.cucumber.datatable.DataTable dataTable){
        assertThat(account.getOperations().size(), is(3));
        assertThat(account.printHistory(), is(expectedValue(dataTable)));
    }

    private String expectedValue(DataTable dataTable) {
        return dataTable.asList().stream().collect(Collectors.joining("\n"));
    }

    private Operation convertToOperation(String operationType, String date, String amount, String balance) throws ParseException {
        Operation operation = new Operation();

        operation.setType(OperationType.valueOf(operationType));
        operation.setDate(new SimpleDateFormat("dd-mm-yyyy").parse(date));
        operation.setAmount(new BigDecimal(amount));
        operation.setBalance(new BigDecimal(balance));
        return  operation;

    }

}
