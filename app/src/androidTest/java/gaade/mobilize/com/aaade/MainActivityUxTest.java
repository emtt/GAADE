package gaade.mobilize.com.aaade;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Morfo on 07/05/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityUxTest {

    private static final String NOMBREESPERADO = "Efra";

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void btnEvent(){
        onView(withId(R.id.editText)).perform(typeText(NOMBREESPERADO), closeSoftKeyboard());

        onView(withText("Hola")).perform(click());

        String expectedText = "Hola , " + NOMBREESPERADO + "!";
        onView(withId(R.id.textView)).check(matches(withText(expectedText)));
    }

}
