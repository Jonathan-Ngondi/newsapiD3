package com.example.newsapid3.database;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.newsapid3.database.savedarticles.SaveArticleService;
import com.example.newsapid3.database.savedarticles.SavedArticle;
import com.example.newsapid3.database.savedarticles.SavedArticleDao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * @author Mugiwara_Munyi
 * @date 16/06/2019
 */
@RunWith(AndroidJUnit4.class)
public class AppDatabaseTest {

    private SavedArticleDao savedArticleDao;
    SaveArticleService service;
    private SavedArticle savedArticle1;
    private SavedArticle savedArticle2;
    private SavedArticle savedArticle3;


    @Before
    public void setUp(){

        savedArticle1 = new SavedArticle("Headline1");
        savedArticle2 = new SavedArticle("Headline2");
        savedArticle3 = new SavedArticle("Headline3");
        savedArticleDao = AppDatabase.getInstance(InstrumentationRegistry.getTargetContext());


    }

    @Test
    public void testDatabaseInsert(){
        savedArticleDao.clearDb();
        savedArticleDao.addSavedArticle(savedArticle1);
        assertEquals(savedArticleDao.getCount(),1);
        savedArticleDao.addSavedArticle(savedArticle2);
        savedArticleDao.addSavedArticle(savedArticle3);
        assertEquals(savedArticleDao.getCount(),3);

    }

    @Test
    public void testDatabaseDelete(){
        savedArticleDao.clearDb();
        savedArticleDao.addSavedArticle(savedArticle1);
        savedArticleDao.addSavedArticle(savedArticle2);
        savedArticleDao.addSavedArticle(savedArticle3);
        assertEquals(savedArticleDao.getCount(), 3);
        savedArticleDao.deleteSavedArticle(savedArticle1);
        savedArticleDao.deleteSavedArticle(savedArticle2);
        assertEquals(savedArticleDao.getCount(),1);

    }
}
