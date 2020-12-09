package com.akh.fireauth.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.akh.fireauth.model.FireUserRepo;
import com.google.firebase.auth.FirebaseUser;

public class HomeViewModel extends AndroidViewModel {
    private FireUserRepo fireUserRepo;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private MutableLiveData<Boolean> logedOutMutableLiveData;
    public HomeViewModel(@NonNull Application application) {
        super(application);
        fireUserRepo = new FireUserRepo(application);
        userMutableLiveData = fireUserRepo.getUserMutableLiveData();
        logedOutMutableLiveData = fireUserRepo.getLoggedOutMutableLiveData();
    }
    public void signOut(){
        fireUserRepo.signOut();
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        return userMutableLiveData;
    }

    public MutableLiveData<Boolean> getLogedOutMutableLiveData() {
        return logedOutMutableLiveData;
    }
}
