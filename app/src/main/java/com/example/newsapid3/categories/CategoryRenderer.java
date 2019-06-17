package com.example.newsapid3.categories;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsapid3.R;
import com.example.newsapid3.models.Category;
import com.example.poweradapter.item.ItemRenderer;

import javax.inject.Inject;
import javax.inject.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Mugiwara_Munyi
 * @date 13/06/2019
 */
public class CategoryRenderer implements ItemRenderer<Category> {

    private final Provider<CategoriesPresenter> presenterProvider;

    @Inject CategoryRenderer(Provider<CategoriesPresenter> presenterProvider){
        this.presenterProvider = presenterProvider;
    }

    @Override
    public int layoutRes() {
        return R.layout.view_category_list_item;
    }

    @Override
    public View createView(@NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutRes(), parent, false);
        view.setTag(new ViewBinder(view, presenterProvider.get()));
        return view;
    }

    @Override
    public void render(@NonNull View itemView, @NonNull Category item) {
        ((ViewBinder) itemView.getTag()).bind(item);
    }

    static class ViewBinder{
        @BindView(R.id.tv_category_name)
        TextView categoryName;

        private Category category;

        ViewBinder(View itemView, CategoriesPresenter presenter){
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener( v->{
                    presenter.onCategoryClicked(category.name());

            });
        }

        void bind(Category category){
            this.category = category;
            categoryName.setText(category.name());
        }
    }
}
