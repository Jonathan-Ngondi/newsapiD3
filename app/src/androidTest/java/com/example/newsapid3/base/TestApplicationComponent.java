package com.example.newsapid3.base;

import com.example.newsapid3.categories.CategoryControllerTest;
import com.example.newsapid3.data.CategoryRepository;
import com.example.newsapid3.data.TestCategoryService;
import com.example.newsapid3.data.TestCategoryServiceModule;
import com.example.newsapid3.database.DatabaseModule;
import com.example.newsapid3.details.NewsDetailsControllerTest;
import com.example.newsapid3.networking.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;
import ui.TestNavigationModule;
import ui.TestScreenNavigator;

/**
 * @author Mugiwara_Munyi
 * @date 14/06/2019
 */
@Singleton
@Component (modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestCategoryServiceModule.class,
        ServiceModule.class,
        TestNavigationModule.class,
        DatabaseModule.class,
})

public interface TestApplicationComponent extends ApplicationComponent {
    void inject(CategoryControllerTest categoryControllerTest);

    void inject(NewsDetailsControllerTest newsDetailsControllerTest);

    TestScreenNavigator screenNavigator();

    TestCategoryService categoryService();

    CategoryRepository categoryRepository();
}
