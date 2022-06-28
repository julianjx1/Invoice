package com.invoice.bd.model;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;

public class InvoiceItem {
    public int invoiceItemNumber;
    public String productName;
    public int quantity;
    public int rate;
    public int amount;

    public InvoiceItem() {
    }

    @BindingAdapter("android:text")
    public static void setText(TextView view, int value) {
        view.setText(Integer.toString(value));
    }

    @InverseBindingAdapter(attribute = "android:text")
    public static int getText(TextView view) {
        return Integer.parseInt(view.getText().toString());
    }
}
