package com.example.newsapid3.details;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import com.bluelinelabs.conductor.Controller;
import com.example.newsapid3.R;
import com.example.newsapid3.test.ControllerTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * @author Mugiwara_Munyi
 * @date 14/06/2019
 */
@RunWith(AndroidJUnit4.class)
public class NewsDetailsControllerTest extends ControllerTest {

    static {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Before
    public void setUp(){
        testRule.clearState();
    }



    @Test
    public void loadArticles(){
        categoryService.setSendError(false);
        launch();
        onView(withId(R.id.loading_indicator_article)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.tv_article_error)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.article_list)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(allOf(withId(R.id.tv_article_title), withText("Tyson Launches Its First Plant-Based Protein Brand to Compete With Beyond Meat - Gizmodo"))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void testError(){
        categoryService.setSendError(true);
        launch();
        onView(withId(R.id.loading_indicator_article)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.article_list)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.tv_article_error)).check(matches(allOf(withText(R.string.api_error_loading_articles),withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE))));

    }

    protected Controller controllerToLaunch(){ return NewsDetailsController.newInstance("business");}
}

