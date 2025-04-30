package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.helper.LoadingPage.waitDisplayed;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.helper.CustomViewMatcher;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MaxViewNewsPostTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void mainPage() {
        Allure.step("Шаг 1: Запустить приложение");
        onView(isRoot()).perform(waitDisplayed(R.id.container_list_news_include_on_fragment_main, 7000));
    }


    @Test
    @DisplayName(value = "Тест-кейс 87. Отображение 8 последних новостных постов в разделе Панель Управления")
    @io.qameta.allure.kotlin.Description(value = "Тест проверяет отображение max кол-во постов")
    public void maxPostInControlPanelTest() {
        Allure.step("Шаг 2: Нажать кнопку все новости");
        ViewInteraction allNewsButton = onView(withId(R.id.all_news_text_view));
        allNewsButton.check(matches(isDisplayed()));
        allNewsButton.perform(click());
        Allure.step("Шаг 3: Нажать на иконку с карандашиком");
        ViewInteraction editButton = onView(withId(R.id.edit_news_material_button));
        editButton.check(matches(isDisplayed()));
        editButton.perform(click());
        Allure.step("Шаг 4: Посмотреть все новостные посты в разделе");
        ViewInteraction newsBox = onView(withId(R.id.news_list_recycler_view));
        newsBox.check(matches(isDisplayed()));
        newsBox.check(matches(CustomViewMatcher.recyclerViewSizeMatcher(8)));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
