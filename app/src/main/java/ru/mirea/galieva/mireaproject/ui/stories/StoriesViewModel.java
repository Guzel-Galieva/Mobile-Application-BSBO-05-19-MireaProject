package ru.mirea.galieva.mireaproject.ui.stories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StoriesViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public StoriesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is stories fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}