package steps;


import com.katabank.model.Account;
import com.katabank.model.Operation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        assertThat(account.getLastOperation(), is(convertToOperation(dataTable)));
    }

    @Then("a withdrawal operation is added to the history with these values")
    public void a_wihdrawal_operation_is_added_to_the_history(io.cucumber.datatable.DataTable dataTable) throws ParseException {
        assertThat(account.getLastOperation(), is(convertToOperation(dataTable)));
    }


    @Given("A client with a bank account with following operations")
    public void A_client_with_a_bank_account_with_following_operations(io.cucumber.datatable.DataTable dataTable)  {
        account = new Account();
        List<Operation> operations =  account.getOperations();
        dataTable.asLists().stream().skip(1).forEach(list ->  {
           Operation operation =  new Operation();
           operation.setType(list.get(0));
            try {
                operation.setDate(new SimpleDateFormat("dd-mm-yyyy").parse(list.get(1)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            operation.setAmount(new BigDecimal(list.get(2)));
            operation.setBalance(new BigDecimal(list.get(3)));

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

    private Operation convertToOperation(io.cucumber.datatable.DataTable dataTable) throws ParseException {
        Operation operation = new Operation();

        operation.setType(dataTable.cell(0, 1));
        operation.setDate(new SimpleDateFormat("dd-mm-yyyy").parse(dataTable.cell(1, 1)));
        operation.setAmount(new BigDecimal(dataTable.cell(2, 1)));
        operation.setBalance(new BigDecimal(dataTable.cell(3, 1)));
        return  operation;

    }

}
