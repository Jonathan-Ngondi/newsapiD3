package com.example.newsapid3.test;

import android.content.Intent;

import com.bluelinelabs.conductor.Controller;
import com.example.newsapid3.data.CategoryRepository;
import com.example.newsapid3.data.TestCategoryService;
import com.example.newsapid3.home.MainActivity;

import org.junit.Rule;

import ui.TestScreenNavigator;

/**
 * @author Mugiwara_Munyi
 * @date 15/06/2019
 */
public abstract class ControllerTest {
    @Rule
    public ControllerTestRule<MainActivity> testRule = new ControllerTestRule<>(MainActivity.class);

    protected TestCategoryService categoryService = testRule.categoryService;
    protected TestScreenNavigator screenNavigator = testRule.screenNavigator;
    protected CategoryRepository categoryRepository = testRule.categoryRepository;

    public ControllerTest(){
          screenNavigator.overrideInitialController(controllerToLaunch());
    }

    protected abstract Controller controllerToLaunch();

    protected void launch(){
        launch(null);
    }

    protected void launch(Intent intent){
        testRule.launchActivity(intent);
    }
}
