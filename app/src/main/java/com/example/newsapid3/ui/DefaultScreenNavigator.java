package com.example.newsapid3.ui;

import android.support.v7.app.AppCompatActivity;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.example.newsapid3.details.NewsDetailsController;
import com.example.newsapid3.di.ActivityScope;
import com.example.newsapid3.lifecycle.ActivityLifeCycleTask;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
@ActivityScope
public class DefaultScreenNavigator extends ActivityLifeCycleTask implements ScreenNavigator {


    private Router router;

    @Inject public DefaultScreenNavigator(){}



    @Override
    public void onCreate(AppCompatActivity activity) {
        if(!(activity instanceof RouterProvider)){
            throw new IllegalArgumentException("Activity must implement Router Provider");
        }

        initWithRouter(((RouterProvider) activity).getRouter(), ((RouterProvider) activity).initialScreen());
    }

    public void initWithRouter(Router router, Controller rootScreen){
        this.router = router;
        if(!router.hasRootController()){
            router.setRoot(RouterTransaction.with(rootScreen));
        }
    }

    @Override
    public void goToNewsCategories(String categoryName) {
        Timber.i("Is router null? "+String.valueOf(router==null));

        if(router !=null){
            router.pushController(RouterTransaction.with(
                    NewsDetailsController.newInstance(categoryName))
            .pushChangeHandler(new FadeChangeHandler())
            .popChangeHandler(new FadeChangeHandler()));
        }

    }

    @Override
    public boolean pop() {
        return router!=null && router.handleBack();
    }

    @Override
    public void onDestroy(AppCompatActivity activity) {
        router = null;
    }
}
