package com.example.springsophsoft.ui.Balance;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BalanceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BalanceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}