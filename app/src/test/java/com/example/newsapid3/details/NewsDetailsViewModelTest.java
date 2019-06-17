package com.example.newsapid3.details;

import com.example.newsapid3.R;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import io.reactivex.observers.TestObserver;

/**
 * @author Mugiwara_Munyi
 * @date 15/06/2019
 */
public class NewsDetailsViewModelTest {

    private NewsDetailsViewModel viewModel;

    @Before
    public void setUp(){
        viewModel = new NewsDetailsViewModel();
    }

    @Test
    public void loading() throws Exception{

        TestObserver<Boolean> loadingObserver = viewModel.loading().test();

        viewModel.loadingUpdated().accept(true);
        viewModel.loadingUpdated().accept(false);

        loadingObserver.assertValues(true, false);
    }

    @Test
    public void error() throws Exception{
        TestObserver<Integer> errorObserver = viewModel.error().test();

        viewModel.onError().accept(new IOException());
        viewModel.articlesUpdated().run();

        errorObserver.assertValues(R.string.api_error_loading_articles, -1);
    }

}
