package posidenpalace.com.wallmart_coding_chalange.ui_tests.item_list;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import posidenpalace.com.wallmart_coding_chalange.R;
import posidenpalace.com.wallmart_coding_chalange.view.activities.item_list.ItemList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertEquals;

public class itemListUITest {

    private String searched;

    @Rule
    public ActivityTestRule<ItemList> activityRule = new ActivityTestRule<>(
            ItemList.class);

    @Before
    public void setup() {
         searched = "apple";
    }

    @Test // tests that the search function works
    public void Test_Search_Function()throws Exception {

        onView(withId(R.id.etSearchBox))
                .perform(typeText(searched), closeSoftKeyboard());
        onView(withId(R.id.btnSearcButton)).perform(click());

       assertEquals(searched,activityRule.getActivity().currentItem);
    }

}


