package com.akh.fireauth.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.akh.fireauth.R;
import com.akh.fireauth.viewModel.LoginViewModel;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    private EditText edt_email,edt_password;
    private Button btn_login;
    TextView txtgotosingup;
    private LoginViewModel loginViewModel;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        loginViewModel.getUserMutableLiveData().observe(this, new Observer<FirebaseUser>(){

            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if(firebaseUser!=null){
                    Navigation.findNavController(getView())
                            .navigate(R.id.action_loginFragment_to_homeFragment);
                    //getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        edt_email = view.findViewById(R.id.edt_email_login);
        edt_password = view.findViewById(R.id.edt_pass_login);
        btn_login = view.findViewById(R.id.btn_login);
        txtgotosingup = view.findViewById(R.id.txt_go_singup);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();
                if(email.length()>0 && password.length()>0){
                    loginViewModel.login(email,password);
                }
            }
        });

        txtgotosingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_signUpFragment);
            }
        });

        return view;
    }

}
