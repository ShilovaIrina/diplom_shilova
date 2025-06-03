package ru.iteco.fmhandroid.ui.test;


import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.page.AboutApplicationPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutApplicationTest extends AboutApplicationPage {

    @Before
    public void openAboutApplication() {
        aboutApplicationPage.clickNavButton();
        aboutApplicationPage.clickButtonAbout();
    }

    AboutApplicationPage aboutApplicationPage = new AboutApplicationPage();

    @Test
    @DisplayName(value = "Тест-кейс 12. Переход по ссылке Пользовательское соглашение")
    @io.qameta.allure.kotlin.Description(value = "Тест проверяет интент")
    public void intentUserAgreementTest() {
        aboutApplicationPage.clickLinkUserAgreement();
    }

    @Test
    @DisplayName(value = "Тест-кейс 13. Отображение текущей версии приложения на странице")
    @io.qameta.allure.kotlin.Description(value = "Тест проверяет отобращение актуальной версии приложения")
    public void actualVersionTest() {
        aboutApplicationPage.viewActualVersion();
    }

    @Test
    @DisplayName(value = "Тест-кейс 11. Переход по ссылке Политика Конфиденциальности")
    @io.qameta.allure.kotlin.Description(value = "Тест проверяет интент")
    public void intentPrivacyPolicyTest() {
        aboutApplicationPage.clickLinkUserAgreement();
    }
}
