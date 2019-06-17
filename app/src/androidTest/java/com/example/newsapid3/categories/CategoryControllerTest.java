package com.example.newsapid3.categories;

import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import com.bluelinelabs.conductor.Controller;
import com.example.newsapid3.R;
import com.example.newsapid3.base.TestApplication;
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
public class CategoryControllerTest extends ControllerTest {
    static {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Before
    public void setUp(){
        TestApplication.getComponent().inject(this);
    }

    @Test
    public void loadCategories(){
        categoryService.setSendError(false);
        launch();
        onView(withId(R.id.loading_indicator)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.tv_error)).check(ViewAssertions.matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.category_list)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        onView(allOf(withId(R.id.tv_category_name), withText("technology"))).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    protected Controller controllerToLaunch(){ return new CategoriesController();}
}
