package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
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

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class QuoteDescriptionTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    String quoteItem = "Хоспис в своем истинном понимании - это творчество";
    String descriptionItem = "Нет шаблона и стандарта, есть только дух, который живет в разных домах по-разному. " +
            "Но всегда он добрый, любящий и помогающий.";

    @Before
    public void mainPage() {
        Allure.step("Шаг 1: Запустить приложение");
        onView(isRoot()).perform(waitDisplayed(R.id.container_list_news_include_on_fragment_main, 7000));
    }

    @Test
    @DisplayName(value = "Тест-кейс 63. Просмотр описания цитаты")
    @io.qameta.allure.kotlin.Description(value = "Тест проверяет отображение описания цитаты")
    public void viewQuoteDescriptionTest() {
        Allure.step("Шаг 2: Нажать на изображение бабочки");
        ViewInteraction butterflyButton = onView(withId(R.id.our_mission_image_button));
        butterflyButton.check(matches(isDisplayed()));
        butterflyButton.perform(click());

        Allure.step("Шаг 3: Выбрать любую из отображаемых цитат");
        ViewInteraction quote = onView(withText(quoteItem));
        quote.check(matches(isDisplayed()));

        Allure.step("Шаг 4: Нажать на цитату");
        quote.perform(click());

        ViewInteraction description = onView(withText(descriptionItem));
        description.check(matches(isDisplayed()));
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
