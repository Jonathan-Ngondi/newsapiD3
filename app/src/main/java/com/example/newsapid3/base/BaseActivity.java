package com.example.newsapid3.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.Router;
import com.example.newsapid3.R;
import com.example.newsapid3.di.Injector;
import com.example.newsapid3.di.ScreenInjector;
import com.example.newsapid3.lifecycle.ActivityLifeCycleTask;
import com.example.newsapid3.ui.RouterProvider;
import com.example.newsapid3.ui.ScreenNavigator;

import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public abstract class BaseActivity extends AppCompatActivity implements RouterProvider {

    @Inject ScreenInjector screenInjector;
    @Inject Set<ActivityLifeCycleTask> activityLifeCycleTasks;
    @Inject ScreenNavigator screenNavigator;


    private static String INSTANCE_ID_KEY = "instance_id";
    private Router router;
    private String instanceId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if(savedInstanceState!=null){
            instanceId = savedInstanceState.getString(INSTANCE_ID_KEY);
        } else {
            instanceId = UUID.randomUUID().toString();
        }

        Injector.inject(this);

        setContentView(layoutRes());

        ViewGroup screenContainer = findViewById(R.id.screen_container);
        if(screenContainer == null){
            throw new IllegalArgumentException("Activity must have a view with id: screen_container");
        }

        router = Conductor.attachRouter(this, screenContainer, savedInstanceState);
        monitorBackStack();
        for(ActivityLifeCycleTask task: activityLifeCycleTasks){
            task.onCreate(this);
        }


        super.onCreate(savedInstanceState);
    }

    private void monitorBackStack(){
        router.addChangeListener(new ControllerChangeHandler.ControllerChangeListener() {
            @Override
            public void onChangeStarted(@Nullable Controller to, @Nullable Controller from, boolean isPush, @NonNull ViewGroup container, @NonNull ControllerChangeHandler handler) {

            }

            @Override
            public void onChangeCompleted(@Nullable Controller to, @Nullable Controller from, boolean isPush, @NonNull ViewGroup container, @NonNull ControllerChangeHandler handler) {
                if(!isPush && from !=null){
                    Injector.clearComponent(from);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        for(ActivityLifeCycleTask task: activityLifeCycleTasks){
            task.onStart(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        for(ActivityLifeCycleTask task: activityLifeCycleTasks){
            task.onResume(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for(ActivityLifeCycleTask task: activityLifeCycleTasks){
            task.onPause(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        for(ActivityLifeCycleTask task: activityLifeCycleTasks){
            task.onStop(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isFinishing()){
            Injector.clearComponent(this);
        }

        for(ActivityLifeCycleTask task: activityLifeCycleTasks){
            task.onDestroy(this);
        }
    }

    @Override
    public void onBackPressed() {
        if(!screenNavigator.pop()){
            super.onBackPressed();
        }


    }

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    public Router getRouter() {
        return router;
    }

    @Override
    public Controller initialScreen() {
        return null;
    }


    public String getInstanceId() {
        return instanceId;
    }

    public ScreenInjector getScreenInjector() {return screenInjector;}
}
