package com.example.newsapid3.data;

import com.example.newsapid3.test.TestUtils;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * @author Mugiwara_Munyi
 * @date 14/06/2019
 */
@Singleton
public class TestCategoryService implements CategoryService {

    private boolean sendError;
    private TestUtils testUtils;

    @Inject
    TestCategoryService(TestUtils testUtils){
        this.testUtils = testUtils;
    }

    @Override
    public Single<NewsCategoryResponse> getNewsCategory(String category) {
        if(!sendError){
            NewsCategoryResponse response = testUtils.loadJson("mock/top-headlines/get_category_business.json", NewsCategoryResponse.class);
            return Single.just(response);
        }

        return Single.error(new IOException());
    }

    public void setSendError(boolean sendError){
        this.sendError = sendError;
    }
}
