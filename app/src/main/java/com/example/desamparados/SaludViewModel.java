package com.example.desamparados;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SaludViewModel extends ViewModel {
    private MutableLiveData<String> mText;
    public SaludViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Esta es la vista de veterinarias.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
