package com.example.newsapid3.details;

import com.example.newsapid3.data.CategoryRepository;
import com.example.newsapid3.data.NewsCategoryResponse;
import com.example.newsapid3.lifecycle.DisposableManager;
import com.example.newsapid3.models.NewsArticle;
import com.example.newsapid3.testutils.TestUtils;
import com.example.newsapid3.ui.ScreenNavigator;
import com.example.poweradapter.adapter.RecyclerDataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * @author Mugiwara_Munyi
 * @date 14/06/2019
 */
public class NewsDetailsPresenterTest {

    static {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    private static final String CATEGORY = "category";
    @Mock CategoryRepository categoryRepository;
    @Mock NewsDetailsViewModel viewModel;
    @Mock Consumer<Throwable> onErrorConsumer;
    @Mock Consumer<Boolean> loadingConsumer;
    @Mock ScreenNavigator screenNavigator;
    @Mock DisposableManager disposableManager;
    @Mock RecyclerDataSource dataSource;

    private NewsDetailsPresenter presenter;





    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(viewModel.loadingUpdated()).thenReturn(loadingConsumer);
        when(viewModel.onError()).thenReturn(onErrorConsumer);
        when(viewModel.articlesUpdated()).thenReturn(() -> {});
    }

    @Test
    public void reposLoaded() throws Exception {
        List<NewsArticle> articles = setUpSuccess();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);

    }

    @Test
    public void reposLoadedError()throws Exception{
        Throwable error = setUpError();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);

        verify(onErrorConsumer).accept(error);
        verifyZeroInteractions(dataSource);
    }

    @Test
    public void loadingSuccess()throws Exception{
        setUpSuccess();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }

    @Test
    public void loadingError()throws Exception{

        //noinspection ThrowableNotThrown
        setUpError();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }


    private Throwable setUpError(){
        Throwable error = new IOException();
        when(categoryRepository.getNewsArticles(CATEGORY)).thenReturn(Single.error(error));
        return error;
    }


    private List<NewsArticle> setUpSuccess(){
        List<NewsArticle> articles;
        NewsCategoryResponse response = TestUtils.loadJson("mock/top-headlines/get_category_business.json", NewsCategoryResponse.class);
        articles = response.articles();

        when(categoryRepository.getNewsArticles(CATEGORY)).thenReturn(Single.just(articles));
        return articles;
    }

    private void initializePresenter(){
        presenter = new NewsDetailsPresenter(viewModel, categoryRepository, screenNavigator, Mockito.mock(DisposableManager.class), dataSource, CATEGORY);
    }

}