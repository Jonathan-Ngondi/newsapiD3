package com.example.newsapid3.base;

import android.support.test.InstrumentationRegistry;

/**
 * @author Mugiwara_Munyi
 * @date 14/06/2019
 */
public class TestApplication extends MyApplication {
    @Override
    protected TestApplicationComponent initComponent() {
        return DaggerTestApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static TestApplicationComponent getComponent(){
        return (TestApplicationComponent)
                ((TestApplication) InstrumentationRegistry.getTargetContext().getApplicationContext()).component;
    }
}
