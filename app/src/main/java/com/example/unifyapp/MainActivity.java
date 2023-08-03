package com.example.unifyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unifyapp.Repository.Repository;
import com.example.unifyapp.ViewModel.ViewModel;

public class MainActivity extends AppCompatActivity {

    private String[] dropdown_type,dropdown_length,dropdown_weight;

    ViewModel viewModel;
    EditText input_val;
    TextView result_view;
    private AutoCompleteTextView dropdownMenu,dropdown_input_1,dropdown_input_2;
    private ArrayAdapter arrayAdapter,input_1_length_adapter,input_1_weight_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        dropdownMenu.setAdapter(arrayAdapter);

        dropdownMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viewModel.clear();
                viewModel.setConversionType((String) adapterView.getAdapter().getItem(i));
                if(i==0){
                    dropdown_input_1.setAdapter(input_1_length_adapter);
                    dropdown_input_2.setAdapter(input_1_length_adapter);
                }
                else if(i==1){
                    dropdown_input_1.setAdapter(input_1_weight_adapter);
                    dropdown_input_2.setAdapter(input_1_weight_adapter);
                }

            }
        });

        dropdown_input_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                viewModel.setUnit_1((String) adapterView.getAdapter().getItem(i));
            }
        });

        dropdown_input_2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viewModel.setUnit_2((String) adapterView.getAdapter().getItem(i));
            }
        });

        viewModel.getResult().observe(this, new Observer<Double>() {
            @Override
            public void onChanged(Double ans) {
                if(viewModel.isAllFiledOk())
                result_view.setText(Repository.formatAnswer(viewModel.getUnit_2(),ans));
            }
        });

        input_val.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.toString().length()>0 && editable.toString().trim()!=".") {
                    try {
                        double x = Double.parseDouble(editable.toString());
//                    Toast.makeText(MainActivity.this, "" + editable.toString(), Toast.LENGTH_SHORT).show();
                        viewModel.setInput_value(x);
                    }catch (Exception e){
                        Toast.makeText(MainActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                    viewModel.setInput_value(0.0);
            }
        });

    }
    void init(){
        result_view=findViewById(R.id.result_view);
        viewModel=new ViewModel();

        input_val=findViewById(R.id.input_1);

        dropdown_type = getResources().getStringArray(R.array.type);
        dropdown_length=getResources().getStringArray(R.array.length);
        dropdown_weight=getResources().getStringArray(R.array.weight);
        arrayAdapter=new ArrayAdapter(getBaseContext(),R.layout.dropdown_item, dropdown_type);
        input_1_length_adapter= new ArrayAdapter(getBaseContext(), R.layout.dropdown_item,dropdown_length);
        input_1_weight_adapter= new ArrayAdapter(getBaseContext(), R.layout.dropdown_item,dropdown_weight);
        dropdownMenu= findViewById(R.id.dropdown_menu);
        dropdown_input_1=findViewById(R.id.unit_1);
        dropdown_input_2=findViewById(R.id.unit_2);
    }
}