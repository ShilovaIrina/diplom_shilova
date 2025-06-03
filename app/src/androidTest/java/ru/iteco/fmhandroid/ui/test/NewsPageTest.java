package ru.iteco.fmhandroid.ui.test;


import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.data.TestDataInfo;
import ru.iteco.fmhandroid.ui.page.MainPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsPageTest extends NewsPage {
    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();

    @Before
    public void clickPlusButton() {
        mainPage.clickAllNews();
        newsPage.clickEditButton();
        newsPage.clickCreateButton();
    }

    @Test
    @DisplayName(value = "Тест-кейс 68. Создание активной новости")
    @io.qameta.allure.kotlin.Description(value = "Тест проверяет возможность создания активного новостного поста")
    public void createdActivNewsTest() {
        newsPage.inputCategory(TestDataInfo.getCategory());
        newsPage.inputTitle(TestDataInfo.getTitle());
        newsPage.inputPublishDate(TestDataInfo.getDate());
        newsPage.inputPublishTime(TestDataInfo.getTime());
        newsPage.inputDescription(TestDataInfo.getDescription());
        newsPage.clickSaveButton();
        newsPage.viewCreatedNews(TestDataInfo.getTitle());
    }

    @Test
    @DisplayName(value = "Тест-кейс 66. Создание активной новости с пустыми полями")
    @io.qameta.allure.kotlin.Description(value =
            "Тест проверяет возможность создания активного пустого новостного поста")
    public void createdEmptyNewsTest() {
        newsPage.viewCategory();
        newsPage.viewTitle();
        newsPage.viewPublishDate();
        newsPage.viewPublishTime();
        newsPage.viewDescription();
        newsPage.clickSaveButton();
        newsPage.viewWarningMessage();
    }

    @Test
    @DisplayName(value = "Тест-кейс 71. Ввод ссылки в поле Заголовок")
    @io.qameta.allure.kotlin.Description(value =
            "Тест проверяет возможность создания активного новостного " +
                    "поста с невалидным значение в поле Заголовок")
    public void inputLinkInTitle() {
        newsPage.inputCategory(TestDataInfo.getCategory());
        newsPage.inputTitle(TestDataInfo.getLink());
        newsPage.inputPublishDate(TestDataInfo.getDate());
        newsPage.inputPublishTime(TestDataInfo.getTime());
        newsPage.inputDescription(TestDataInfo.getNewDescription());
        newsPage.clickSaveButton();
        newsPage.viewCreatedNews(TestDataInfo.getLink());
    }
}
