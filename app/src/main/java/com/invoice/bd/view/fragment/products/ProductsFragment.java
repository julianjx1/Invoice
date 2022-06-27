package com.invoice.bd.view.fragment.products;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoice.bd.R;
import com.invoice.bd.databinding.FragmentProductsBinding;
import com.invoice.bd.databinding.InvoiceItemRowBinding;
import com.invoice.bd.databinding.ProductItemRowBinding;

import java.util.ArrayList;


public class ProductsFragment extends Fragment {


    FragmentProductsBinding binding;
    ArrayList<ProductItemRowBinding> rowBindingArrayList = new ArrayList<>();

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle("Products");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_products, container, false);
        binding.add.setOnClickListener(view -> {
            ProductItemRowBinding rowBinding = newRow();
            binding.table.addView(rowBinding.getRoot());
            rowBindingArrayList.add(rowBinding);
        });

        return binding.getRoot();
    }

    private ProductItemRowBinding newRow(){
        ProductItemRowBinding rowBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.product_item_row,binding.table,false);
        rowBinding.productItemRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_categoriesFragment_to_categoryInfoFragment);

            }
        });


        return rowBinding;
    }


    private void removeRow(){
        ProductItemRowBinding rowBinding = rowBindingArrayList.remove(rowBindingArrayList.size()-1);

        binding.table.removeView(rowBinding.getRoot());
    }
}