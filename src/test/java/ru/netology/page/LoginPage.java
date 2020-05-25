package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("input[name=\"login\"]");
    private SelenideElement passwordField = $("input[name=\"password\"]");
    private SelenideElement loginButton = $("[data-test-id=action-login]");

    public VerificationPage validLogin(DataHelper.AuthInfo loginInfo) {
        loginField.setValue(loginInfo.getLogin());
        passwordField.setValue(loginInfo.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void cleanLoginFields() {
        loginField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
    }
}