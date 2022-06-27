package com.invoice.bd.view.fragment.products;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoice.bd.R;
import com.invoice.bd.databinding.FragmentProductInfoBinding;
import com.invoice.bd.databinding.ProductItemRowBinding;


public class ProductInfoFragment extends Fragment {

    FragmentProductInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_product_info, container, false);
        return binding.getRoot();
    }
}