package com.example.desamparados;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PetshopViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public PetshopViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Esta es la vista de petshop");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
