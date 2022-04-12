package com.csci310.uscdoordrink;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
public class LoginUI {

    @Rule
    public ActivityScenarioRule<Login> activityTestRule =
            new ActivityScenarioRule<>(Login.class);


    @Test
    public void checkShowRegisterView() {
        onView(withId(R.id.btn_register)).perform(click());
        onView(withId(R.id.passwordR)).check(matches(isDisplayed()));
    }

    @Test
    public void checkSuccessFulLogin() throws InterruptedException {
        onView(withId(R.id.email))
                .perform(typeText("Leo"), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText("1223"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        //wait for the view to be loaded
        Thread.sleep(1000);
        onView(withId(R.id.buttons)).check(matches(isDisplayed()));
    }

    @Test
    public void checkFailedLogin() throws InterruptedException {
        onView(withId(R.id.email))
                .perform(typeText("Leo"), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        //wait for the view to be loaded
        Thread.sleep(1000);

    }

}