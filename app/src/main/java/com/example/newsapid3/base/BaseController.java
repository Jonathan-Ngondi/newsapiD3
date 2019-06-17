package com.example.newsapid3.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;
import com.example.newsapid3.di.Injector;
import com.example.newsapid3.lifecycle.ScreenLifeCycleTask;

import java.util.Set;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public abstract class BaseController extends Controller {

    @Inject Set<ScreenLifeCycleTask> screenLifeCycleTasks;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private boolean injected = false;
    Unbinder unbinder;

    public BaseController(){super();}
    public BaseController(Bundle bundle){super(bundle);}

    @Override
    protected void onContextAvailable(@NonNull Context context) {
        //Controller instances are retained across config changes, so this method can be called more than once. This makes
        //sure we don't waste any time injecting more than once, though technically it wouldn't change functionality.
        if(!injected) {
            Injector.inject(this);
            injected = true;
        }
        super.onContextAvailable(context);
    }


    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflater.inflate(layoutRes(), container, false);
        unbinder = ButterKnife.bind(this, view);
        onViewBound(view);
        disposables.addAll(subscriptions());
        return view;
    }

    @Override
    protected void onChangeStarted(@NonNull ControllerChangeHandler changeHandler, @NonNull ControllerChangeType changeType) {
        for(ScreenLifeCycleTask task: screenLifeCycleTasks){
            if(changeType.isEnter){
                task.onEnterScope(getView());
            } else {
                task.onExitScope();
            }
        }
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        disposables.clear();
        if(unbinder!=null){
            unbinder.unbind();
            unbinder = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for(ScreenLifeCycleTask task: screenLifeCycleTasks){
            task.onDestroy();
        }
    }

    protected void onViewBound(View v){

    }

    protected Disposable[] subscriptions(){ return new Disposable[0];}

    @LayoutRes
    protected abstract int layoutRes();
}
