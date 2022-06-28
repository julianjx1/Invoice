package com.invoice.bd.view.fragment.categories;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoice.bd.R;
import com.invoice.bd.databinding.FragmentCategoriesBinding;
import com.invoice.bd.view.adapter.CategoryAdapter;

import java.util.ArrayList;
import java.util.List;


public class CategoriesFragment extends Fragment {

    FragmentCategoriesBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_categories, container, false);
        List<String> categories = new ArrayList<>();
        categories.add("Electronics");
        categories.add("Foods");
        CategoryAdapter categoryAdapter = new CategoryAdapter(categories, requireActivity());
        binding.categoryList.setLayoutManager( new LinearLayoutManager(requireContext()));
        binding.categoryList.setAdapter(categoryAdapter);
        binding.add.setOnClickListener(view -> {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_categoriesFragment_to_categoryInfoFragment);
        });
        return binding.getRoot();
    }
}