package com.example.springsophsoft.ui.AddCash;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddCashViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddCashViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is add cash fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}