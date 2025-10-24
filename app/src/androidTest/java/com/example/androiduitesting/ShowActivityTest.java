package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenario =
            new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Test Case 1: Check whether the activity correctly switched
     * This verifies that clicking on a city opens ShowActivity
     */
    @Test
    public void testActivitySwitch() {
        // First, add a city to test with
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(click());
        onView(withId(R.id.editText_name)).perform(androidx.test.espresso.action.ViewActions.typeText("Edmonton"));
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the first item in the city list
        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Verify that ShowActivity is displayed by checking if city_name_text is visible
        onView(withId(R.id.city_name_text))
                .check(matches(isDisplayed()));
    }

    /**
     * Test Case 2: Test whether the city name is consistent
     * This verifies that the city name displayed matches what was clicked
     */
    @Test
    public void testCityNameConsistency() {
        String expectedCity = "Vancouver";

        // Add the city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(androidx.test.espresso.action.ViewActions.typeText(expectedCity));
        androidx.test.espresso.Espresso.closeSoftKeyboard();
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on the first item
        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Verify the city name is displayed correctly in ShowActivity
        onView(withId(R.id.city_name_text))
                .check(matches(withText(expectedCity)));
    }

    /**
     * Test Case 3: Test the "back" button
     * This verifies that the back button returns to MainActivity
     */
    @Test
    public void testBackButton() {
        // Add a city
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(androidx.test.espresso.action.ViewActions.typeText("Toronto"));
        androidx.test.espresso.Espresso.closeSoftKeyboard();
        onView(withId(R.id.button_confirm)).perform(click());

        // Click on a city to go to ShowActivity
        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // Click the back button
        onView(withId(R.id.back_button))
                .perform(click());

        // Verify we're back at MainActivity by checking if city_list is visible
        onView(withId(R.id.city_list))
                .check(matches(isDisplayed()));
    }
}