package ru.iteco.fmhandroid.ui.test;


import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.data.TestDataInfo;
import ru.iteco.fmhandroid.ui.page.ThematicQuotesPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ThematicQuoteTest extends ThematicQuotesPage {

    @Before
    public void clickButton() {
        thematicQuotesPage.clickButterflyButton();
    }

    ThematicQuotesPage thematicQuotesPage = new ThematicQuotesPage();

    @Test
    @DisplayName(value = "Тест-кейс 62. Отображение max кол-во цитат в разделе")
    @io.qameta.allure.kotlin.Description(value = "Тест проверяет отображение max кол-во цитат в разделе")
    public void maxQuoteViewTest() {
        thematicQuotesPage.viewMaxQuotes(TestDataInfo.getMaxQuotes());
    }

    @Test
    @DisplayName(value = "Тест-кейс 63. Просмотр описания цитаты")
    @io.qameta.allure.kotlin.Description(value = "Тест проверяет отображение описания цитаты")
    public void viewQuoteDescriptionTest() {
        thematicQuotesPage.viewQuote();
        thematicQuotesPage.clickQuote();
        thematicQuotesPage.viewDescription();
    }
}
