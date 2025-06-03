package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.utils.LoadingPage.waitDisplayed;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.PerformException;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import junit.framework.AssertionFailedError;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class BaseClass {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

 private final int mainPageID = R.id.container_list_news_include_on_fragment_main;
 private final int fieldLoginID = R.id.login_text_input_layout;
 private final int fieldPasswordID = R.id.password_text_input_layout;
 private final int loginButton = R.id.enter_button;
 private final int newsBox = R.id.all_news_text_view;
 private final String login = "Логин";
 private final String validLogin = "login2";
 private final String password = "Пароль";
 private final String validPassword = "password2";

    @Before
    public void authorizationUserOrNot() {
        Allure.step("Шаг 1: Запустить приложение. Открывается Главная");
        try {
            onView(isRoot()).perform(waitDisplayed(mainPageID, 7000));
            onView(withId(mainPageID)).check(matches(isDisplayed()));

        } catch (PerformException e) {
            Allure.step("Шаг 1: Запустить приложение. Открывается страница авторизации");
            onView(isRoot()).perform(waitDisplayed(fieldLoginID, 7000));

            onView(withId(fieldLoginID)).perform(click());
            onView(withHint(login)).perform(typeText(validLogin), closeSoftKeyboard());

            onView(withId(fieldPasswordID)).check(matches(isDisplayed()));
            onView(withId(fieldPasswordID)).perform(click());
            onView(withHint(password)).perform(typeText(validPassword), closeSoftKeyboard());

            onView(withId(loginButton)).check(matches(isDisplayed()));
            onView(withId(loginButton)).perform(click());

            onView(isRoot()).perform(waitDisplayed(newsBox, 7000));
        }
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
