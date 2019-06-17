package com.example.newsapid3.categories;

import com.example.newsapid3.data.CategoryRepository;
import com.example.newsapid3.lifecycle.DisposableManager;
import com.example.newsapid3.models.Category;
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
 * @date 13/06/2019
 */
public class CategoriesPresenterTest {

    static {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
    }

    @Mock CategoryRepository categoryRepository;
    @Mock CategoriesViewModel viewModel;
    @Mock Consumer<Throwable> onErrorConsumer;
    @Mock Consumer<Boolean> loadingConsumer;
    @Mock Consumer<List<Category>> onSuccessConsumer;
    @Mock ScreenNavigator screenNavigator;
    @Mock RecyclerDataSource dataSource;

    private CategoriesPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(viewModel.loadingUpdated()).thenReturn(loadingConsumer);
        when(viewModel.onError()).thenReturn(onErrorConsumer);
        when(viewModel.categoriesUpdated()).thenReturn(onSuccessConsumer);

    }

    @Test
    public void categoriesLoaded() throws Exception {
        List<Category> categories = setUpSuccess();
        initializePresenter();
        verify(dataSource).setData(categories);
        verifyZeroInteractions(onErrorConsumer);

    }

    @Test
    public void loadingError()throws Exception{
        setUpError();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }

    private List<Category> setUpSuccess() {
        List<Category> repos;
        CategoriesResponse  response = TestUtils.loadJson("mock/categories/get_categories.json", CategoriesResponse.class);
        repos = response.categories();
        when(categoryRepository.getCategories()).thenReturn(Single.just(repos));
        return repos;

    }

    private Throwable setUpError(){
        Throwable error = new IOException();
        when(categoryRepository.getCategories()).thenReturn(Single.error(error));
        return error;
    }



    private void initializePresenter(){
        presenter = new CategoriesPresenter(viewModel, screenNavigator, categoryRepository, Mockito.mock(DisposableManager.class), dataSource);
    }
}
