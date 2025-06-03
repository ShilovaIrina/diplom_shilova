package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Intent;

import androidx.test.espresso.intent.Intents;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class AboutApplicationPage extends BaseClass {

    private final String privacyPolicy = "https://vhospice.org/#/privacy-policy";

    private final String buttonAbout = "О приложении";
    private final int navigationButton = R.id.main_menu_image_button;
    private final int userAgreementID = R.id.about_terms_of_use_value_text_view;
    private final String userAgreement = "https://vhospice.org/#/terms-of-use";
    private final int versionApplicationID = R.id.about_version_value_text_view;
    private final String version = "1.0.0";
    private final int privacyPolicyID = R.id.about_privacy_policy_value_text_view;


    public final int getNavButtonID() {
        return navigationButton;
    }

    public final String getButtonAboutText() {
        return buttonAbout;
    }

    public final int getUserAgreementID(){
        return userAgreementID;
    }

    public final String getUserAgreementLink() {
        return userAgreement;
    }

    public final int getVersionApplicationID() {
        return versionApplicationID;
    }

    public final String getVersion() {
        return version;
    }

    public final int getPrivacyPolicyID() {
        return  privacyPolicyID;
    }

    public final String getPrivacyPolicyLink() {
        return privacyPolicy;
    }

    public void clickNavButton() {
    Allure.step("Шаг 2: Нажать на меню навигации");
        onView(withId(getNavButtonID()))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickButtonAbout() {
        Allure.step("Шаг 3: Нажать на " + buttonAbout);
        onView(withText(getButtonAboutText()))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void clickLinkUserAgreement() {
        Allure.step("Шаг 4: Нажать на ссылку");
        onView(withId(getUserAgreementID()))
                .check(matches(isDisplayed()));
        Intents.init();
        onView(withId(getUserAgreementID())).perform(click());

        intended(hasData(getUserAgreementLink()));
        intended(hasAction(Intent.ACTION_VIEW));

        Intents.release();
    }

    public void viewActualVersion() {
        Allure.step("Шаг 4: Посмотреть отображаемую версию в верхней части страницы");
        onView(withId(getVersionApplicationID()))
                .check(matches(withText(getVersion())))
                .check(matches(isDisplayed()));
    }

    public void clickLinkPrivacyPolicy() {
        Allure.step("Шаг 4: Нажать на ссылку");
        onView(withId(getPrivacyPolicyID())).check(matches(isDisplayed()));
        Intents.init();
        onView(withId(getPrivacyPolicyID())).perform(click());

        intended(hasData(getPrivacyPolicyLink()));
        intended(hasAction(Intent.ACTION_VIEW));

        Intents.release();
    }
}
