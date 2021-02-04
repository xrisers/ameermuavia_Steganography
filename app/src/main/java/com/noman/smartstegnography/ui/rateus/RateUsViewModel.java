package com.noman.smartstegnography.ui.rateus;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RateUsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RateUsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is RateUs fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}