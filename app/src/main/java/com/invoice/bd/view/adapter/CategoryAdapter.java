package com.invoice.bd.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.invoice.bd.R;
import com.invoice.bd.databinding.CategoryItemBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<String> categories;
    private Activity activity;

    public CategoryAdapter(List<String> categories, Activity activity) {
        this.categories = categories;
        this.activity = activity;
    }

    public void updateList(ArrayList<String> list){
        categories.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CategoryItemBinding binding = DataBindingUtil.inflate( LayoutInflater.from(parent.getContext()),R.layout.category_item,
                parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.categoryItemBinding.category.setText(categories.get(position));
        holder.categoryItemBinding.categoryLayout.setOnClickListener(view -> {

            Navigation.findNavController(activity, R.id.nav_host_fragment).navigate(R.id.action_categoriesFragment_to_categoryInfoFragment);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public CategoryItemBinding categoryItemBinding;


        public ViewHolder(CategoryItemBinding categoryItemBinding) {
            super(categoryItemBinding.getRoot());
            this.categoryItemBinding = categoryItemBinding;
        }

//        public void bind(String category) {
//            categoryItemBinding.setVariable(BR.transaction, transaction);
//            transactionsItemBinding.setVariable(BR.user, user);
//            transactionsItemBinding.setDate(Constant.getDate(transaction.date));
//            transactionsItemBinding.executePendingBindings();
//        }
    }
}
