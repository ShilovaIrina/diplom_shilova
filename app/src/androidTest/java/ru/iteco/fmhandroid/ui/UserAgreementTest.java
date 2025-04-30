package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.helper.LoadingPage.waitDisplayed;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;
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
public class UserAgreementTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    String buttonAbout = "О приложении";
    String userAgreement = "https://vhospice.org/#/terms-of-use";

    @Before
    public void mainPage() {
        Allure.step("Шаг 1: Запустить приложение");
        onView(isRoot()).perform(waitDisplayed(R.id.container_list_news_include_on_fragment_main, 7000));
    }

    @Test
    @DisplayName(value = "Тест-кейс 12. Переход по ссылке Пользовательское соглашение")
    @io.qameta.allure.kotlin.Description(value = "Тест проверяет интент")
    public void intentUserAgreementTest() {
        Allure.step("Шаг 2: Нажать на меню навигации");
        ViewInteraction navigationButton = onView(withId(R.id.main_menu_image_button));
        navigationButton.check(matches(isDisplayed()));
        navigationButton.perform(click());

        Allure.step("Шаг 3: Нажать на " + buttonAbout);
        ViewInteraction aboutApplication = onView(withText(buttonAbout)).check(matches(isDisplayed()));
        aboutApplication.perform(click());

        Allure.step("Шаг 4: Нажать на ссылку");
        ViewInteraction result = onView(withId(R.id.about_terms_of_use_value_text_view));
        result.check(matches(isDisplayed()));
        Intents.init();
        result.perform(click());

        intended(hasData(userAgreement));
        intended(hasAction(Intent.ACTION_VIEW));

        Intents.release();
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
