package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data.DataHelper;
import page.DashboardPage;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static data.DataHelper.*;


public class PageObjectTest {
    int initBalCardOne;
    int initBalCardTwo;
    int finBalCardOne;
    int finBalCardTwo;
    DashboardPage dashboardPage;


    @BeforeEach
    void SetUp() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var loginData = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(loginData);
        var verificationCode = getVerificationCodeFor(loginData);
        dashboardPage = verificationPage.validVerify(verificationCode);
        initBalCardOne = dashboardPage.getFirstCardBalance();
        initBalCardTwo = dashboardPage.getSecondCardBalance();
    }

    @Test
    void shouldTransferFromFirstToSecondCard() {
        int amount = 3100;
        var firstCardInfo = DataHelper.getFirstCardInfo();
        var MoneyTransferPage = dashboardPage.transferPage1();
        dashboardPage = MoneyTransferPage.transfer(firstCardInfo.getCardNumber(), Integer.toString(amount));
        finBalCardOne = dashboardPage.getFirstCardBalance();
        finBalCardTwo = dashboardPage.getSecondCardBalance();
        assertEquals(initBalCardOne - amount, finBalCardOne);
        assertEquals(initBalCardTwo + amount, finBalCardTwo);
    }

    @Test
    void shouldTransferFromSecondToFirstCard() {
        int amount = 4500;
        var secondCardInfo = DataHelper.getSecondCardInfo();
        var MoneyTransferPage = dashboardPage.transferPage2();
        dashboardPage = MoneyTransferPage.transfer(secondCardInfo.getCardNumber(), Integer.toString(amount));
        finBalCardOne = dashboardPage.getFirstCardBalance();
        finBalCardTwo = dashboardPage.getSecondCardBalance();
        assertEquals(initBalCardOne + amount, finBalCardOne);
        assertEquals(initBalCardTwo - amount, finBalCardTwo);
    }

    @Test
    void shouldTransferAllMoneyFromFirstCard() {
        int amount = 10_000;
        var secondCardInfo = DataHelper.getSecondCardInfo();
        var MoneyTransferPage = dashboardPage.transferPage2();
        dashboardPage = MoneyTransferPage.transfer(secondCardInfo.getCardNumber(), Integer.toString(amount));
        finBalCardOne = dashboardPage.getFirstCardBalance();
        finBalCardTwo = dashboardPage.getSecondCardBalance();
        assertEquals(initBalCardOne + amount, finBalCardOne);
        assertEquals(initBalCardTwo - amount, finBalCardTwo);
    }

    @Test
    void shouldTransferAllMoneyFromSecondCard() {
        int amount = 10_000;
        var secondCardInfo = DataHelper.getSecondCardInfo();
        var MoneyTransferPage = dashboardPage.transferPage2();
        dashboardPage = MoneyTransferPage.transfer(secondCardInfo.getCardNumber(), Integer.toString(amount));
        finBalCardOne = dashboardPage.getFirstCardBalance();
        finBalCardTwo = dashboardPage.getSecondCardBalance();
        assertEquals(initBalCardOne + amount, finBalCardOne);
        assertEquals(initBalCardTwo - amount, finBalCardTwo);
    }

    @Test
    void shouldTransferZeroFromFirstToSecondCard() {
        int amount = 0;
        var firstCardInfo = DataHelper.getFirstCardInfo();
        var MoneyTransferPage = dashboardPage.transferPage1();
        dashboardPage = MoneyTransferPage.transfer(firstCardInfo.getCardNumber(), Integer.toString(amount));
        finBalCardOne = dashboardPage.getFirstCardBalance();
        finBalCardTwo = dashboardPage.getSecondCardBalance();
        assertEquals(initBalCardOne - amount, finBalCardOne);
        assertEquals(initBalCardTwo + amount, finBalCardTwo);
    }

    @Test
    void shouldTransferZeroFromSecondToFirstCard() {
        int amount = 0;
        var secondCardInfo = DataHelper.getSecondCardInfo();
        var MoneyTransferPage = dashboardPage.transferPage2();
        dashboardPage = MoneyTransferPage.transfer(secondCardInfo.getCardNumber(), Integer.toString(amount));
        finBalCardOne = dashboardPage.getFirstCardBalance();
        finBalCardTwo = dashboardPage.getSecondCardBalance();
        assertEquals(initBalCardOne + amount, finBalCardOne);
        assertEquals(initBalCardTwo - amount, finBalCardTwo);
    }

    @Test
    void shouldTransfer20_000FromFirstToSecond() {
        int amount = 20_000;
        var firstCardInfo = DataHelper.getFirstCardInfo();
        var MoneyTransferPage = dashboardPage.transferPage1();
        dashboardPage = MoneyTransferPage.transfer(firstCardInfo.getCardNumber(), Integer.toString(amount));
        finBalCardOne = dashboardPage.getFirstCardBalance();
        finBalCardTwo = dashboardPage.getSecondCardBalance();
        assertEquals(initBalCardOne - amount, finBalCardOne);
        assertEquals(initBalCardTwo + amount, finBalCardTwo);
    }

}