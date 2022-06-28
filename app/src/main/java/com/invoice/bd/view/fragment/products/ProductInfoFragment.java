package com.invoice.bd.view.fragment.products;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoice.bd.R;
import com.invoice.bd.databinding.FragmentProductInfoBinding;
import com.invoice.bd.databinding.ProductItemRowBinding;
import com.invoice.bd.model.Product;


public class ProductInfoFragment extends Fragment {

    FragmentProductInfoBinding binding;
    static Product product;
    static  int position;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_product_info, container, false);
//        if(getArguments() != null){
//            if(getArguments().getBoolean("add"))
//                binding.btnDelete.setVisibility(View.GONE);
//        }
        binding.setProduct(product);


        return binding.getRoot();
    }
}