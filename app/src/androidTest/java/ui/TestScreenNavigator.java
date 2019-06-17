package ui;

import android.support.v7.app.AppCompatActivity;

import com.bluelinelabs.conductor.Controller;
import com.example.newsapid3.lifecycle.ActivityLifeCycleTask;
import com.example.newsapid3.ui.DefaultScreenNavigator;
import com.example.newsapid3.ui.RouterProvider;
import com.example.newsapid3.ui.ScreenNavigator;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Mugiwara_Munyi
 * @date 15/06/2019
 */
@Singleton
public class TestScreenNavigator extends ActivityLifeCycleTask implements ScreenNavigator {

    private final DefaultScreenNavigator screenNavigator;
    private Controller overrideController;

    @Inject
    TestScreenNavigator(){
        this.screenNavigator = new DefaultScreenNavigator();
    }

    /**
     * Set the Controller to launch when the Activity attaches to the Screen Navigator. This will be
     * used instead of the Controller provided by {@link RouterProvider#initialScreen()} )}
     *
     * @param overrideController Controller to launch when the Activity starts. Or null to fall back to default.
     */
    public void overrideInitialController(Controller overrideController){
        this.overrideController = overrideController;
    }

    @Override
    public void onCreate(AppCompatActivity activity) {
        if(!(activity instanceof RouterProvider)){
            throw new IllegalArgumentException("Activity must be an instance of Router Provider.");
        }
        Controller launchController = overrideController==null?
                ((RouterProvider) activity).initialScreen(): overrideController;
        screenNavigator.initWithRouter(((RouterProvider) activity).getRouter(),
                launchController);

    }

    @Override
    public void goToNewsCategories(String categoryName) {
        screenNavigator.goToNewsCategories(categoryName);
    }

    @Override
    public boolean pop() {
        return screenNavigator.pop();
    }

    @Override
    public void onDestroy(AppCompatActivity activity) {
        screenNavigator.onDestroy(activity);
    }
}
