package steps;


import com.katabank.model.Account;
import com.katabank.model.Operation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.ast.DataTable;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;
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

    @When("He makes a withrawal of {}")
    public void He_makes_a_withrawal_of (BigDecimal amount) {
        account.makeWithdrawal(amount);
    }

    @Then("the account balance should be updated with {}")
    public void the_account_s_balance_should_be_updated_with(BigDecimal newBalanceAmount) {
        assertThat( account.getBalance(), closeTo(newBalanceAmount, new BigDecimal(0)));

    }
    @Then("a deposit operation is added to the history with these values")
    public void a_deposit_operation_is_added_to_the_history(io.cucumber.datatable.DataTable dataTable) throws ParseException {
        Operation operation = new Operation();

        operation.setType(dataTable.cell(0, 1));
        operation.setDate(new SimpleDateFormat("dd-mm-yyyy").parse(dataTable.cell(1, 1)));
        operation.setAmount(new BigDecimal(dataTable.cell(2, 1)));
        operation.setBalance(new BigDecimal(dataTable.cell(3, 1)));
        assertThat(account.getLastOperation(), is(operation));
    }


    @Given("A client with a bank account with following operations")
    public void A_client_with_a_bank_account_with_following_operations(DataTable dataTable) {

    }

    @When("He wants to check operations")
    public void He_wants_to_check_operations () {

    }

    @Then("the history of all operations should be printed like this")
    public void the_history_of_all_operations_should_be_printed_like_this (String outputStream){
       account.printOperations(outputStream );
    }

}
