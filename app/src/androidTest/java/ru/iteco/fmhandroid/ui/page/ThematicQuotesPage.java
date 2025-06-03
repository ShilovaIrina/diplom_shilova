package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.utils.CustomViewMatcher;

public class ThematicQuotesPage extends BaseClass {

    private final int butterflyButton = R.id.our_mission_image_button;
    private final int quoteBox = R.id.our_mission_item_list_recycler_view;

    private final String quoteItem = "Хоспис в своем истинном понимании - это творчество";
    private final String descriptionItem = "Нет шаблона и стандарта, есть только дух, " +
            "который живет в разных домах по-разному. " +
            "Но всегда он добрый, любящий и помогающий.";

    public final int getButterflyButton() {
        return butterflyButton;
    }

    public final int getQuoteBox() {
        return  quoteBox;
    }

    public final String getQuoteItem() {
        return quoteItem;
    }

    public final String getDescriptionItem() {
        return descriptionItem;
    }

    public void clickButterflyButton() {
        Allure.step("Шаг 2: Нажать на изображение бабочки");
        onView(withId(getButterflyButton()))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void viewMaxQuotes(int item) {
        Allure.step("Шаг 3: Посмотреть сколько цитат отображается на странице");
        onView(withId(getQuoteBox()))
                .check(matches(isDisplayed()))
                .check(matches(CustomViewMatcher.recyclerViewSizeMatcher(item)));
    }

    public void viewQuote() {
        Allure.step("Шаг 3: Выбрать любую из отображаемых цитат");
        onView(withText(getQuoteItem()))
                .check(matches(isDisplayed()));
    }

    public void clickQuote() {
        Allure.step("Шаг 4: Нажать на цитату");
        onView(withText(getQuoteItem())).perform(click());
    }

    public void viewDescription() {
        Allure.step("Появилось описание цитаты");
        onView(withText(getDescriptionItem()))
                .check(matches(isDisplayed()));
    }
}
