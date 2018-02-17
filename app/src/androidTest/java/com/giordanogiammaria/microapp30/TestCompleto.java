package com.giordanogiammaria.microapp30;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestCompleto {

    @Rule
    public ActivityTestRule<SplashScreen> mActivityTestRule = new ActivityTestRule<>(SplashScreen.class);

    @Test
    public void testCompleto() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.sfoglia), withText("run from XML"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout1),
                                        childAtPosition(
                                                withId(R.id.linearLayoutRoot),
                                                5)),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        DataInteraction appCompatTextView = onData(anything())
                .inAdapterView(allOf(withId(R.id.list),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                1)))
                .atPosition(0);
        appCompatTextView.perform(click());

        ViewInteraction fButton = onView(
                allOf(withId(R.id.nextButton), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(R.id.relativeMicro_app),
                                                0)),
                                1),
                        isDisplayed()));
        fButton.perform(click());

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.listView),
                        childAtPosition(
                                withClassName(is("android.support.constraint.ConstraintLayout")),
                                0)))
                .atPosition(57);
        linearLayout.perform(click());

        ViewInteraction fButton2 = onView(
                allOf(withId(R.id.nextButton), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(R.id.relativeMicro_app),
                                                0)),
                                1),
                        isDisplayed()));
        fButton2.perform(click());

        ViewInteraction fButton3 = onView(
                allOf(withId(R.id.nextButton), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(R.id.relativeMicro_app),
                                                0)),
                                1),
                        isDisplayed()));
        fButton3.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.tx_container),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout2),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                3)),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("test"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatImageButton = onView(
                allOf(withId(R.id.sendSms),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frameLayout),
                                        0),
                                4),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction fButton4 = onView(
                allOf(withId(R.id.nextButton), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(R.id.relativeMicro_app),
                                                0)),
                                1),
                        isDisplayed()));
        fButton4.perform(click());

        ViewInteraction fButton5 = onView(
                allOf(withId(R.id.nextButton), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(R.id.relativeMicro_app),
                                                0)),
                                1),
                        isDisplayed()));
        fButton5.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.subjectEditText),
                        childAtPosition(
                                allOf(withId(R.id.firstFloat),
                                        childAtPosition(
                                                withClassName(is("android.widget.RelativeLayout")),
                                                3)),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("test"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.bodyEditText),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.RelativeLayout")),
                                        4),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("body"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.sendButton), withText("Send"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frameLayout),
                                        0),
                                5),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction fButton6 = onView(
                allOf(withId(R.id.nextButton), withText("Next"),
                        childAtPosition(
                                allOf(withId(R.id.relativeLayout),
                                        childAtPosition(
                                                withId(R.id.relativeMicro_app),
                                                0)),
                                1),
                        isDisplayed()));
        fButton6.perform(click());

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
