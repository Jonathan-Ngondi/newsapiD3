package ui;

import com.example.newsapid3.lifecycle.ActivityLifeCycleTask;
import com.example.newsapid3.ui.ScreenNavigator;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

/**
 * @author Mugiwara_Munyi
 * @date 15/06/2019
 */
@Module
public abstract class TestNavigationModule {

    @Binds
    abstract ScreenNavigator bindsScreenNavigator(TestScreenNavigator screenNavigator);

    @Binds
    @IntoSet
    abstract ActivityLifeCycleTask bindsScreenNavigatorTask(TestScreenNavigator screenNavigator);


}
