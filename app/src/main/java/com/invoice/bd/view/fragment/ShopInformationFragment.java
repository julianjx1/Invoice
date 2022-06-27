package com.invoice.bd.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.invoice.bd.R;


public class ShopInformationFragment extends Fragment {
    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle("Shop Information");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop_information, container, false);
    }
}