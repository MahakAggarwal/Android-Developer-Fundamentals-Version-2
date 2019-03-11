package me.mahakagg.phonenumberspinnerespresso;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class SpinnerSelectionTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("me.mahakagg.phonenumberspinnerespresso", appContext.getPackageName());
    }

    @Test
    public void iterateSpinnerItems(){
        String[] myArray = mActivityRule.getActivity().getResources().getStringArray(R.array.labels_array);
        for (int i = 0; i< myArray.length; i++){
            // find spinner and click on it
            onView(withId(R.id.label_spinner)).perform(click());
            //find spinner item matching the array text and click on it
            onData(is(myArray[i])).perform(click());
            // check if spinner selection matches with (part of) text view
            onView(withId(R.id.text_phone_label)).check(matches(withText(containsString(myArray[i]))));
        }
    }
}
