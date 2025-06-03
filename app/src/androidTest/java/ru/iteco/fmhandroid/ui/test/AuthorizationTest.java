package ru.iteco.fmhandroid.ui.test;


import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.data.TestDataInfo;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.MainPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest extends AuthorizationPage {

    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    
    @Test
    @DisplayName("Кейс 153. Авторизация с валидными значениями полей логин и пароль")
    @io.qameta.allure.kotlin.Description("Тест проверяет авторизацию с валидными данными")
    public void validAuthorizationTest() {
        authorizationPage.inputValidLoginInFieldLogin(TestDataInfo.getValidLogin());
        authorizationPage.inputValidPasswordInFieldPassword(TestDataInfo.getValidPassword());
        authorizationPage.clickInEnterButton();
        mainPage.viewMainPage();
    }

    @Test
    @DisplayName("Кейс 152. Авторизация с пустыми полями")
    @io.qameta.allure.kotlin.Description("Тест проверяет попытку авторизации с незаполнеными полями")
    public void emptyAuthorizationTest() {
        authorizationPage.viewLoginField();
        authorizationPage.viewPasswordField();
        authorizationPage.clickInEnterButton();
        authorizationPage.viewErrorMessageEmpty();
    }

    @Test
    @DisplayName("Кейс 155. Авторизация с валидным паролем и логином содержащим ошибку")
    @io.qameta.allure.kotlin.Description("Тест проверяет попытку авторизации с невалидным логином")
    public void invalidLoginAuthorizationTest() {
        authorizationPage.inputInvalidLoginInFieldLogin(TestDataInfo.getInvalidLogin());
        authorizationPage.inputValidPasswordInFieldPassword(TestDataInfo.getValidPassword());
        authorizationPage.clickInEnterButton();
        authorizationPage.viewErrorMessageInvalid();
    }
}
