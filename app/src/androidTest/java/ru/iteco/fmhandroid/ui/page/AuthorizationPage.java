package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.utils.LoadingPage.waitDisplayed;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.PerformException;

import org.hamcrest.Matchers;
import org.junit.Before;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class AuthorizationPage extends BaseClass {

    private final int mainPageID = R.id.container_list_news_include_on_fragment_main;
    private final int authButton = R.id.authorization_image_button;
    private final int fieldLogin = R.id.login_text_input_layout;
    private final int fieldPassword = R.id.password_text_input_layout;
    private final int enterButton = R.id.enter_button;

    private final String exitButton = "Выйти";
    private final String login = "Логин";
    private final String password = "Пароль";
    private final String errorMessageEmpty = "Логин и пароль не могут быть пустыми";
    private final String errorMessageInvalid = "Что-то пошло не так. Попробуйте позднее.";


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

    @Override
    @Before
    public void authorizationUserOrNot() {
        Allure.step("Шаг 1: Запустить приложение. Открывается страница авторизации");
        try {
            onView(isRoot()).perform(waitDisplayed(fieldLogin, 7000));
        } catch (PerformException e) {
            Allure.step("Шаг 1: Запустить приложение. Открывается главная страница");
            onView(isRoot()).perform(waitDisplayed(mainPageID, 7000));

            Allure.step("Шаг 2: Нажать на " + authButton);
            onView(withId(authButton)).check(matches(isDisplayed()));
            onView(withId(authButton)).perform(click());

            Allure.step("Шаг 3: Нажать на кнопку " + exitButton);
            onView(withText(exitButton)).check(matches(isDisplayed()));
            onView(withText(exitButton)).perform(click());
        }
    }

    public final int getFieldLoginID(){
        return fieldLogin;
    }

    public final int getFieldPasswordID() {
        return fieldPassword;
    }

    public final int getEnterButton() {
        return enterButton;
    }

    public final String getErrorMessageEmpty() {
        return errorMessageEmpty;
    }

    public final String getErrorMessageInvalid() {
        return  errorMessageInvalid;
    }

    public void inputValidLoginInFieldLogin(String validLogin) {
        Allure.step("Шаг 4: Ввести в поле " + login + "значение " + validLogin);
        onView(withId(getFieldLoginID()))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withHint(login)).perform(typeText(validLogin), closeSoftKeyboard());
    }

    public void inputValidPasswordInFieldPassword(String validPassword) {
        Allure.step("Шаг 5: Ввести в поле " + password + "значение " + validPassword);
        onView(withId(getFieldPasswordID()))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withHint(password)).perform(typeText(validPassword), closeSoftKeyboard());
    }

    public void clickInEnterButton() {
        Allure.step("Шаг 6: Нажать на " + getEnterButton() );
        onView(withId(getEnterButton()))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void inputInvalidLoginInFieldLogin(String invalidLogin) {
        Allure.step("Шаг 4: Ввести в поле " + login + "значение " + invalidLogin);
        onView(withId(getFieldLoginID()))
                .check(matches(isDisplayed()))
                .perform(click());
        onView(withHint(login)).perform(typeText(invalidLogin), closeSoftKeyboard());
    }

    public void viewLoginField() {
        Allure.step("Шаг 4: Оставить поле " + login + "пустым");
        onView(withId(getFieldPasswordID()))
                .check(matches(isDisplayed()));
    }

    public void viewPasswordField() {
        Allure.step("Шаг 5: Оставить поле " + password + "пустым");
        onView(withId(getFieldPasswordID()))
                .check(matches(isDisplayed()));
    }

    public void viewErrorMessageEmpty() {
        Allure.step("Отображение ошибки на странице");
        onView(withText(getErrorMessageEmpty()))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    public void viewErrorMessageInvalid() {
        Allure.step("Отображение ошибки на странице");
        onView(withText(getErrorMessageInvalid()))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }
}
