package com.example.newsapid3.details;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newsapid3.R;
import com.example.newsapid3.database.savedarticles.SaveArticleService;
import com.example.newsapid3.di.ScreenScope;
import com.example.newsapid3.models.NewsArticle;
import com.example.newsapid3.utils.Animations;
import com.example.poweradapter.item.ItemRenderer;
import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * This class helps us to render Recycler View items using the power adapter class
 * @author Mugiwara_Munyi
 * @date 14/06/2019
 */
@ScreenScope
public class NewsDetailsRenderer implements ItemRenderer<NewsArticle> {

    private final SaveArticleService saveArticleService;


    @Inject NewsDetailsRenderer(SaveArticleService saveArticleService){
        this.saveArticleService = saveArticleService;

    }

    @Override
    public int layoutRes() {
        return R.layout.view_article_list_item;
    }

    @Override
    public View createView(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes(), parent, false);
        view.setTag(new ViewBinder(view, saveArticleService));
        return view;
    }

    @Override
    public void render(@NonNull View itemView, @NonNull NewsArticle item) {

        ((ViewBinder)itemView.getTag()).bind(item);
    }


    static class ViewBinder {

        private final SaveArticleService savedArticleService;
        @BindView(R.id.tv_article_title)
        TextView articleTitle;
        @BindView(R.id.iv_star)
        ImageView starImageView;
        @BindView(R.id.tv_article_description)
        TextView description;
        @BindView(R.id.iv_avatar)
        ImageView articleImageView;
        @BindView(R.id.parent_view)
        View parentView;

        private NewsArticle newsArticle;
        private Disposable savedDisposable;

        ViewBinder(View view, SaveArticleService saveArticleService){
            this.savedArticleService= saveArticleService;
            ButterKnife.bind(this, view);
            RxView.attachEvents(parentView)
                    .subscribe(event ->{
                        if(event.view().isAttachedToWindow()){
                            listenForFavoriteChanges();
                        }else {
                            if(savedDisposable !=null){
                                savedDisposable.dispose();
                                savedDisposable = null;
                            }
                        }
                    });
        }

        @OnClick(R.id.parent_view)
        void toggleDescription(){

            if(newsArticle !=null){
                if(description.getVisibility()==View.VISIBLE)
                    Animations.collapse(description);
                else
                    Animations.expand(description);
            }
        }

        @OnLongClick(R.id.parent_view)
        boolean toggleFavorite(){

            if(newsArticle!=null){
                savedArticleService.toggleFavorites(newsArticle);
            }
            return true;
        }

        private void listenForFavoriteChanges() {
            savedDisposable = savedArticleService.favoritedArticles()
                    .filter(__ -> newsArticle != null)
                    .map(favoriteArticles -> favoriteArticles.contains(newsArticle.title()))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(isFavorite -> parentView.setBackgroundColor(isFavorite ? parentView.getContext().getColor(R.color.saveMe): Color.TRANSPARENT));
        }

        void bind(NewsArticle newsArticle){
            this.newsArticle = newsArticle;
            articleTitle.setText(newsArticle.title());
            if(newsArticle.urlToImage()!=null){
                Glide.with(articleImageView.getContext())
                        .load(newsArticle.urlToImage())
                        .into(articleImageView);
            }

            description.setText(newsArticle.description());


        }


    }
}
