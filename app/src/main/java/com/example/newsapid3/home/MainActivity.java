package com.example.newsapid3.home;

import com.bluelinelabs.conductor.Controller;
import com.example.newsapid3.R;
import com.example.newsapid3.base.BaseActivity;
import com.example.newsapid3.categories.CategoriesController;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public Controller initialScreen() {
        return new CategoriesController();
    }
}
