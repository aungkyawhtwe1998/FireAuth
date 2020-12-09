package com.akh.fireauth.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.akh.fireauth.R;
import com.akh.fireauth.viewModel.HomeViewModel;
import com.google.firebase.auth.FirebaseUser;

public class HomeFragment extends Fragment {
    private TextView txtemail,txtusername,txtuserid ;
    private Button btn_logout;
    private HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser!=null){
                    txtemail.setText(firebaseUser.getEmail());
                    btn_logout.setEnabled(true);
                }else {
                    btn_logout.setEnabled(false);
                }
            }
        });
        homeViewModel.getLogedOutMutableLiveData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoggedOut) {
                if(isLoggedOut){
                    Toast.makeText(getContext(),"Logged Out",Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_loginFragment);
                }

            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        txtemail = view.findViewById(R.id.txt_email);
        txtuserid = view.findViewById(R.id.txt_user_id);
        txtusername = view.findViewById(R.id.txt_username);
        btn_logout = view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeViewModel.signOut();
            }
        });
        return view;
    }
}
