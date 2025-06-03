package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasFocus;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;
import org.junit.Before;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class NewsPage extends BaseClass {

    private final int editButton = R.id.edit_news_material_button;
    private final int createButton = R.id.add_news_image_view;
    private final int categoryField = R.id.news_item_category_text_auto_complete_text_view;
    private final int titleField = R.id.news_item_title_text_input_edit_text;
    private final int publishDate = R.id.news_item_publish_date_text_input_edit_text;
    private final int publishTime = R.id.news_item_publish_time_text_input_edit_text;
    private final int descriptionField = R.id.news_item_description_text_input_edit_text;
    private final int saveButton = R.id.save_button;
    private final String warningMessage = "Заполните пустые поля";

    private View decorView;

    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    public final int getEditButtonID() {
        return editButton;
    }

    private final int getCreateButtonID() {
        return createButton;
    }

    private final int getCategoryFieldID() {
        return categoryField;
    }

    private final int getTitleFieldID() {
        return titleField;
    }

    private final int getPublishDateID() {
        return publishDate;
    }

    private final int getPublishTimeID() {
        return publishTime;
    }

    private final int getDescriptionFieldID() {
        return descriptionField;
    }

    private final int getSaveButtonID() {
        return saveButton;
    }

    private final String getWarningMessage() {
        return warningMessage;
    }

    public void clickEditButton() {
        Allure.step("Нажать на иконку с карандашиком");
        onView(withId(getEditButtonID()))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickCreateButton() {
        Allure.step("Шаг 1: Нажать на плюсик");
        onView(withId(getCreateButtonID()))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void inputCategory(String category) {
        Allure.step("Шаг 2: Вести в поле Катерория 'Объявление'");
        onView(withId(getCategoryFieldID()))
                .check(matches(isDisplayed()))
                .perform(replaceText(category), closeSoftKeyboard());
    }

    public void inputTitle(String title) {
        Allure.step("Шаг 3: Вести в поле Заголовок 'Всем привет'");
        onView(withId(getTitleFieldID()))
                .check(matches(isDisplayed()))
                .perform(replaceText(title), closeSoftKeyboard());
    }

    public void inputPublishDate(String date) {
        Allure.step("Шаг 4: Вести в поле Дата Публикации текущую дату");
        onView(withId(getPublishDateID()))
                .check(matches(isDisplayed()))
                .perform(replaceText(date), closeSoftKeyboard());
    }

    public void inputPublishTime(String time) {
        Allure.step("Шаг 5: Вести в поле Время текущее время");
        onView(withId(getPublishTimeID()))
                .check(matches(isDisplayed()))
                .perform(replaceText(time), closeSoftKeyboard());
    }

    public void inputDescription(String description) {
        Allure.step("Шаг 6: Вести в поле Описание значение 'хочу обратить ваше внимание'");
        onView(withId(getDescriptionFieldID()))
                .check(matches(isDisplayed()))
                .perform(replaceText(description), closeSoftKeyboard());
    }

    public void clickSaveButton() {
        Allure.step("Шаг 7: Нажать на кнопку Сохранить");
        onView(withId(getSaveButtonID()))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void viewCreatedNews(String title) {
        Allure.step("Новость создана");
        onView(withId(R.id.news_list_recycler_view)).perform(actionOnItemAtPosition(8, click()));
        onView(allOf(withId(R.id.news_item_title_text_view), withText(title))).check(matches(isDisplayed()));
    }

    public void viewCategory() {
        Allure.step("Шаг 2: Поле Категория не заполнено");
        onView(withId(getCategoryFieldID()))
                .check(matches(isDisplayed()));
    }

    public void viewTitle() {
        Allure.step("Шаг 3: Поле Заголовок не заполнено");
        onView(withId(getTitleFieldID()))
                .check(matches(isDisplayed()));
    }

    public void viewPublishDate() {
        Allure.step("Шаг 4: Поле Дата Публикации не заполнено");
        onView(withId(getPublishDateID()))
                .check(matches(isDisplayed()));
    }

    public void viewPublishTime() {
        Allure.step("Шаг 5: Поле Время не заполнено");
        onView(withId(getPublishTimeID()))
                .check(matches(isDisplayed()));
    }

    public void viewDescription() {
        Allure.step("Шаг 6: Поле Описание не заполнено");
        onView(withId(getDescriptionFieldID()))
                .check(matches(isDisplayed()));
    }

    public void viewWarningMessage() {
        Allure.step("Отображение ошибки на странице");
        onView(withText(getWarningMessage()))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }
}
