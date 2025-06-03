package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.utils.LoadingPage.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MainPage extends BaseClass {

    private final int newsBox = R.id.container_list_news_include_on_fragment_main;
    private final int allNewsButton = R.id.all_news_text_view;

    public final int getMainNewsBoxID(){
        return newsBox;
    }

    public final int getAllNewsButton() {
        return allNewsButton;
    }

    public void viewMainPage() {
        onView(isRoot()).perform(waitDisplayed(getMainNewsBoxID(), 7000));
        onView(withId(getMainNewsBoxID())).check(matches(isDisplayed()));
    }

    public void clickAllNews() {
        Allure.step("Шаг 2: Нажать кнопку все новости");
        onView(withId(getAllNewsButton()))
                .check(matches(isDisplayed()))
                .perform(click());
    }
}
