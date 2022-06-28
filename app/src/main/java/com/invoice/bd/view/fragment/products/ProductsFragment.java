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
import com.invoice.bd.model.Product;

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
//            ProductItemRowBinding rowBinding = newRow();
//            binding.table.addView(rowBinding.getRoot());
//            rowBindingArrayList.add(rowBinding);
            Bundle bundle = new Bundle();
            bundle.putBoolean("add", true);

            ProductInfoFragment.product = new Product();
            ProductInfoFragment.position = -1;

            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_productsFragment_to_productInfoFragment,bundle);

        });

        Product product = new Product();
        product.product_name = "Biscut";
        product.category_name = "Foods";
        product.purchase_rate = 20;
        product.price = 30;
        product.stock = 10;
        binding.table.addView( newRow(product,1).getRoot());
        binding.table.addView( newRow(product,2).getRoot());


        return binding.getRoot();
    }

    private ProductItemRowBinding newRow(Product product, int position){
        ProductItemRowBinding rowBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.product_item_row,binding.table,false);
        rowBinding.productItemRow.setOnClickListener(view -> {
            ProductInfoFragment.product = product;
            ProductInfoFragment.position = position;
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_productsFragment_to_productInfoFragment);

        });

        rowBinding.setProduct(product);
        return rowBinding;
    }


    private void removeRow(){
        ProductItemRowBinding rowBinding = rowBindingArrayList.remove(rowBindingArrayList.size()-1);

        binding.table.removeView(rowBinding.getRoot());
    }
}