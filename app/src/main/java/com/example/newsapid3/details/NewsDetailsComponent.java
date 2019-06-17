package com.example.newsapid3.details;

import com.example.newsapid3.base.ScreenModule;
import com.example.newsapid3.di.ScreenComponent;
import com.example.newsapid3.di.ScreenScope;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * @author Mugiwara_Munyi
 * @date 14/06/2019
 */
@ScreenScope
@Subcomponent (modules = {
         ScreenModule.class,
         NewsDetailsScreenModule.class,
})
public interface NewsDetailsComponent extends ScreenComponent<NewsDetailsController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<NewsDetailsController>{
        @BindsInstance
        public abstract void bindCategoryName(@Named("category_name") String categoryName);

        @Override
        public void seedInstance(NewsDetailsController instance) {
            bindCategoryName(instance.getArgs().getString(NewsDetailsController.CATEGORY_NAME_KEY));
        }
    }
}
