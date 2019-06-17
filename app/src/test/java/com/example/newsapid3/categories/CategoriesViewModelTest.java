package com.example.newsapid3.categories;

import com.example.newsapid3.testutils.TestUtils;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;

/**
 * @author Mugiwara_Munyi
 * @date 14/06/2019
 */
public class CategoriesViewModelTest {


    private CategoriesViewModel viewModel;

    @Before
    public void setUp() throws Exception {
        viewModel = new CategoriesViewModel();
    }

    @Test
    public void loading() throws Exception {
        TestObserver<Boolean>loadingObserver = viewModel.loading().test();

        viewModel.loadingUpdated().accept(true);
        viewModel.loadingUpdated().accept(false);

        loadingObserver.assertValues(true, false);

    }

    @Test
    public void categories() throws Exception{
        CategoriesResponse response = TestUtils.loadJson("mock/categories/get_categories.json", CategoriesResponse.class);
        viewModel.categoriesUpdated().accept(response.categories());
        viewModel.categories().test().assertValue(response.categories());
    }

    @Test
    public void error() throws Exception{

    }

    @Test
    public void categoriesUpdated() throws Exception {
    }

    @Test
    public void loadingUpdated() throws Exception{
    }

    @Test
    public void onError() {
    }
}