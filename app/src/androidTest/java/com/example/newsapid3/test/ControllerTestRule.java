package com.example.newsapid3.test;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import com.example.newsapid3.base.TestApplication;
import com.example.newsapid3.data.CategoryRepository;
import com.example.newsapid3.data.TestCategoryService;

import ui.TestScreenNavigator;

/**
 * @author Mugiwara_Munyi
 * @date 15/06/2019
 */
public class ControllerTestRule<T extends Activity> extends ActivityTestRule<T> {

    public final TestScreenNavigator screenNavigator;
    public final TestCategoryService categoryService;
    public final CategoryRepository categoryRepository;

    public ControllerTestRule(Class<T> activityClass){
        super(activityClass, true, false);
        screenNavigator = TestApplication.getComponent().screenNavigator();
        categoryService = TestApplication.getComponent().categoryService();
        categoryRepository = TestApplication.getComponent().categoryRepository();

    }

    public void clearState(){
        categoryRepository.clearCache();
    }
}
