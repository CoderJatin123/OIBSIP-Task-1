package com.example.unifyapp.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.unifyapp.Repository.Repository;

public class ViewModel extends androidx.lifecycle.ViewModel {

    private Repository repository;
    private double input_value;
    private MutableLiveData<Double> result;
    private String convertType="",unit_1="",unit_2="";
    public ViewModel(){
        result = new MutableLiveData<>();
        result.setValue(0.0);
        repository=new Repository();
    }

    public LiveData<Double> getResult(){
        return  result;
    }
    public void setConversionType(String type){
        this.convertType=type;
        Log.d("TAG", "setConversionType:  ");
         if(isAllFiledOk()){
             calculateFinalResult();
         }
    }

    public String getUnit_2() {
        return unit_2;
    }

    public void setUnit_1(String unit_1) {
        this.unit_1 = unit_1;
        if(isAllFiledOk()){
            calculateFinalResult();
        }
    }

    public void setUnit_2(String unit_2) {
        this.unit_2 = unit_2;
        if(isAllFiledOk()){
            calculateFinalResult();
        }
    }

     public Boolean isAllFiledOk(){
         Log.d("TAG", "isAllFiledOk: "+input_value+" "+unit_1+" "+unit_2+" "+convertType);
        if(convertType!="" && unit_1!="" && unit_2!="")
            return true;
        else
            return false;
    }

    public void setInput_value(double input_value) {
        this.input_value = input_value;
        if(isAllFiledOk()){
            calculateFinalResult();
        }
    }

    void calculateFinalResult(){
       //    Log.d("TAG", "calculateFinalResult: Ok done "+input_value+" "+unit_1+" "+unit_2);
        if(convertType.equals("Length")){
            result.setValue(repository.getLength(input_value,unit_1,unit_2));
        } else if (convertType.equals("Weight")) {
            Log.d("TAG", "calculateFinalResult: weight");
            result.setValue(repository.getWeight(input_value,unit_1,unit_2));
        }
    }
    public void clear(){
        convertType="";
        setUnit_1("");
        setUnit_2("");
        result.setValue(0.00);
    }


}
