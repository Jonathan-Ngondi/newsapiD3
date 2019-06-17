package com.example.newsapid3.data;

import com.example.newsapid3.models.Category;
import com.example.newsapid3.models.NewsArticle;
import com.example.newsapid3.utils.Constants;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public class CategoryRequester {

    private final CategoryService service;

    @Inject
    CategoryRequester(CategoryService service){
        this.service = service;
    }

    Single<List<Category>> getCategories(){
        return Constants.homeCategories();
    }

    Single<List<NewsArticle>> getArticles(String category){
        return service.getNewsCategory(category)
                .map(NewsCategoryResponse::articles);
    }
}
