package com.example.easynotes;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.auth.User;

import java.util.List;

public class StoreViewModel extends ViewModel {


    private String text=" Notes";
    public String getText() {
       return text;
    }



    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
