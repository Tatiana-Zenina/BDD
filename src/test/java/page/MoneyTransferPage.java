package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class MoneyTransferPage {
    private SelenideElement heading= $("[data-test-id='dashboard']");

    public MoneyTransferPage() {
        heading.shouldBe(Condition.visible);
    }
    public DashboardPage transfer(String cardNumber, String amount) {
        $("[data-test-id='amount'] input").setValue(amount);
        $("[data-test-id='from'] input").setValue(cardNumber);
        $("[data-test-id='action-transfer']").click();
        return new DashboardPage();
    }
}