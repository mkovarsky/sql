package ru.netology.ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLUtils;
import ru.netology.page.LoginPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    SQLUtils mySql = new SQLUtils();

    @Test
    void shouldLogin() throws SQLException {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getValidAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = SQLUtils.getVerificationCode(authInfo.getLogin());
        val verify = verificationPage.verify(verificationCode);
        verify.pageVisibilityCheck();
    }

    @Test
    void shouldBeBlockedIfPasswordIsWrongThreeTimes() throws SQLException {
        val loginPage = open("http://localhost:9999", LoginPage.class);
        val authInfo = DataHelper.getAuthInfoWithInvalidPassword();
        loginPage.validLogin(authInfo);
        loginPage.cleanLoginFields();
        loginPage.validLogin(authInfo);
        loginPage.cleanLoginFields();
        loginPage.validLogin(authInfo);
        val statusSQL = mySql.getStatusFromDb(authInfo.getLogin());
        assertEquals("blocked", statusSQL);
    }

    @AfterAll
    static void clean() throws SQLException {
        SQLUtils.cleanDb();
    }
}