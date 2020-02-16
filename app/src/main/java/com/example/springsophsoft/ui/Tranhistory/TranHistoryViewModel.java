package com.example.springsophsoft.ui.Tranhistory;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TranHistoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TranHistoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is transaction history fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}