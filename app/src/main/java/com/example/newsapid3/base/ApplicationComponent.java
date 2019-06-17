package com.example.newsapid3.base;

import com.example.newsapid3.data.CategoryServiceModule;
import com.example.newsapid3.database.DatabaseModule;
import com.example.newsapid3.networking.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityBindingModule.class,
        ServiceModule.class,
        CategoryServiceModule.class,
        DatabaseModule.class,
})
public interface ApplicationComponent {
    void inject(MyApplication myApplication);
}
