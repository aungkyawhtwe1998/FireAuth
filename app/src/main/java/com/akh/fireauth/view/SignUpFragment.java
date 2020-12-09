package com.akh.fireauth.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.akh.fireauth.R;
import com.akh.fireauth.viewModel.SignUpViewModel;
import com.google.firebase.auth.FirebaseUser;

public class SignUpFragment extends Fragment {
    private EditText edt_email,edt_password,edtUsername;
    private Button btn_signup;
    private SignUpViewModel signUpViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        signUpViewModel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                Navigation.findNavController(getView())
                        .navigate(R.id.action_signUpFragment_to_loginFragment);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgment_signup,container,false);
        edt_email = view.findViewById(R.id.edt_email_signup);
        edt_password = view.findViewById(R.id.edt_pass_singnup);
        btn_signup = view.findViewById(R.id.btn_signup);
        edtUsername = view.findViewById(R.id.edt_username);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //final ProgressDialog pd = new ProgressDialog(getContext());
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();
                String username = edtUsername.getText().toString();
                if(email.length()>0 && password.length()>0){
                    signUpViewModel.signUp(email,password,username);
                }
            }
        });
        return view;
    }
}
