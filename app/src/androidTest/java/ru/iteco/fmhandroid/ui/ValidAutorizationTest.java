package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
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
public class ValidAutorizationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    String exitButton = "Выйти";

    String login = "Логин";
    String validLogin = "login2";
    String password = "Пароль";
    String validPassword = "password2";

    @Before
    public void mainPage() {
        Allure.step("Шаг 1: Запустить приложение");
        onView(isRoot()).perform(waitDisplayed(R.id.container_list_news_include_on_fragment_main, 7000));
    }
    
    @Test
    @DisplayName("Кейс 153. Авторизация с валидными значениями полей логин и пароль")
    @io.qameta.allure.kotlin.Description("Тест проверяет авторизацию с валидными данными")
    public void validAutorizationTest() {
        Allure.step("Шаг 2: Нажать на иконку человечка");
        ViewInteraction autoButton = onView(withId(R.id.authorization_image_button));
        autoButton.check(matches(isDisplayed()));
        autoButton.perform(click());

        Allure.step("Шаг 3: Нажать на кнопку " + exitButton);
        ViewInteraction exit = onView(withText(exitButton));
        exit.check(matches(isDisplayed()));
        exit.perform(click());

        Allure.step("Шаг 4: Ввести в поле " + login + "значение " + validLogin);
        ViewInteraction loginField = onView(withId(R.id.login_text_input_layout));
        loginField.check(matches(isDisplayed()));
        loginField.perform(click());
        onView(withHint(login)).perform(typeText(validLogin), closeSoftKeyboard());

        Allure.step("Шаг 5: Ввести в поле " + password + "значение " + validLogin);
        ViewInteraction passwordField = onView(withId(R.id.password_text_input_layout));
        passwordField.check(matches(isDisplayed()));
        passwordField.perform(click());
        onView(withHint(password)).perform(typeText(validPassword), closeSoftKeyboard());

        Allure.step("Шаг 6: Нажать на Войти" );
        ViewInteraction loginButton = onView(withId(R.id.enter_button));
        loginButton.check(matches(isDisplayed()));
        loginButton.perform(click());

        Allure.step("Пользователь авторизован. Открылась главная страница приложения");
        ViewInteraction mainPage = onView(isRoot()).perform(waitDisplayed(R.id.all_news_text_view, 7000));
        mainPage.check(matches(isDisplayed()));
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
