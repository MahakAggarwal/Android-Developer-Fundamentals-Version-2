package me.mahakagg.ScoreKeeperEspresso2;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import me.mahakagg.ScoreKeeperEspresso2.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DayNightModeTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void dayNightModeTest() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        
        ViewInteraction appCompatTextView = onView(
allOf(withId(R.id.title), withText("Night Mode"),
childAtPosition(
childAtPosition(
withId(R.id.content),
0),
0),
isDisplayed()));
        appCompatTextView.perform(click());
        
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        
        ViewInteraction textView = onView(
allOf(withId(R.id.title), withText("Day Mode"),
childAtPosition(
childAtPosition(
withId(R.id.content),
0),
0),
isDisplayed()));
        textView.check(matches(withText("Day Mode")));
        
        ViewInteraction appCompatTextView2 = onView(
allOf(withId(R.id.title), withText("Day Mode"),
childAtPosition(
childAtPosition(
withId(R.id.content),
0),
0),
isDisplayed()));
        appCompatTextView2.perform(click());
        
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        
        ViewInteraction textView2 = onView(
allOf(withId(R.id.title), withText("Night Mode"),
childAtPosition(
childAtPosition(
withId(R.id.content),
0),
0),
isDisplayed()));
        textView2.check(matches(withText("Night Mode")));
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
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
