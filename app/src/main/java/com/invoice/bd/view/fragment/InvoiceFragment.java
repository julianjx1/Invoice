package com.invoice.bd.view.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.invoice.bd.R;
import com.invoice.bd.databinding.FragmentInvoiceBinding;
import com.invoice.bd.databinding.InvoiceItemRowBinding;
import com.invoice.bd.pdf.PDFUtility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InvoiceFragment extends Fragment implements PDFUtility.OnDocumentClose{


    FragmentInvoiceBinding binding;

    ArrayList<InvoiceItemRowBinding> rowBindingArrayList = new ArrayList<>();
    private int totalAmount = 0;

    @Override
    public void onResume() {
        super.onResume();
        requireActivity().setTitle("Invoice");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_invoice, container, false);


        binding.add.setOnClickListener(view -> {
            InvoiceItemRowBinding rowBinding = newRow();
            binding.table.addView(rowBinding.getRoot());
            rowBindingArrayList.add(rowBinding);
        });



        binding.print.setOnClickListener(this::generatePDF);
        return binding.getRoot();
    }

    private InvoiceItemRowBinding newRow(){
        InvoiceItemRowBinding rowBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.invoice_item_row,binding.table,false);
      //  rowBinding.no.setText(String.valueOf(rowBindingArrayList.size()+1));
        String[] language ={"Biscuit", "Chocolate"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (requireContext(),android.R.layout.select_dialog_item,language);
//        rowBinding.productName.setThreshold(1);//will start working from first character
//        rowBinding.productName.setAdapter(adapter);//setting the adapter data into the
//        rowBinding.quantity.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                setAmount(rowBinding);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//        rowBinding.rate.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                setAmount(rowBinding);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

        rowBinding.addQuantity.setOnClickListener(view -> {
            Integer quantity = Integer.parseInt(rowBinding.quantity.getText().toString());
            quantity += 1;
            rowBinding.quantity.setText(String.valueOf(quantity));
            setAmount(rowBinding);
        });
        rowBinding.removeQuantity.setOnClickListener(view -> {
            Integer quantity = Integer.parseInt(rowBinding.quantity.getText().toString());
            quantity -= 1;
            if(quantity < 0)
                removeRow(rowBinding);
            else {
                rowBinding.quantity.setText(String.valueOf(quantity));
                setAmount(rowBinding);
            }
        });
        return rowBinding;
    }


    private void setAmount(InvoiceItemRowBinding rowBinding){
        try{
            int quantity = Integer.parseInt(rowBinding.quantity.getText().toString());
            int rate = Integer.parseInt(rowBinding.rate.getText().toString());
            int previewsTotal = Integer.parseInt(rowBinding.amount.getText().toString());
            int total = quantity * rate;
            totalAmount -= previewsTotal;
            totalAmount += total;
            rowBinding.amount.setText(String.valueOf(total));
            binding.totalAmount.setText(String.valueOf(totalAmount));
        }catch (NumberFormatException ignored){

        }

    }
    private void removeRow(InvoiceItemRowBinding rowBinding){
        rowBindingArrayList.remove(rowBindingArrayList.size()-1);
        try{
            int previewsTotal = Integer.parseInt(rowBinding.amount.getText().toString());
            totalAmount -= previewsTotal;
            binding.totalAmount.setText(String.valueOf(totalAmount));
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }
        binding.table.removeView(rowBinding.getRoot());
    }

    private String getFilePath(){
        String dir = Environment.getExternalStorageDirectory() + "/Documents/";
        File invoiceFile = new File(dir, "Invoice.pdf");
        return invoiceFile.getPath();
    }
    private void generatePDF(View v){
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {

            ActivityCompat.requestPermissions(requireActivity(), new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},0);

        }
        try
        {
            PDFUtility.createPdf(v.getContext(), this,getSampleData(),getFilePath(),true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            //  Log.e(TAG,"Error Creating Pdf");
            Toast.makeText(v.getContext(),"Error Creating Pdf", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onPDFDocumentClose(File file) {
        Log.e("path",file.getAbsolutePath());
        Toast.makeText(requireContext(),"Invoice pdf file has created successfully",Toast.LENGTH_SHORT).show();
    }

    private List<String[]> getSampleData()
    {
        List<String[]> temp = new ArrayList<>();
        for(InvoiceItemRowBinding tableRowBinding: rowBindingArrayList){
            temp.add(new String[] {
                   // tableRowBinding.no.getText().toString(),
                    tableRowBinding.productName.getText().toString(),
                    tableRowBinding.quantity.getText().toString(),
                    tableRowBinding.rate.getText().toString(),
                    tableRowBinding.amount.getText().toString(),
            });
        }
        temp.add(new String[] {
                "",
                "",
                "",
                getResources().getString(R.string.total),
                binding.totalAmount.getText().toString(),
        });
        return  temp;
    }

}