package com.example.DuAn1_SangCtph07783.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.DuAn1_SangCtph07783.R;
import com.example.DuAn1_SangCtph07783.activity.LoginPresenter;
import com.example.DuAn1_SangCtph07783.activity.LoginView;


public class FrmAcount extends Fragment  implements LoginView {
    private EditText edtUsername;
    private EditText edtPassword;
    private LoginPresenter loginPresenter;
    private SlideshowViewModel slideshowViewModel;
    private Button btnLogin;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_account, container, false);

        loginPresenter = new LoginPresenter(this);
        edtUsername = root.findViewById(R.id.edtUsername);
        edtPassword = root.findViewById(R.id.edtPassword);

        btnLogin = root.findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginPresenter.login(edtUsername.getText().toString().trim(), edtPassword.getText().toString().trim());



            }
        });

        return root;


    }

    @Override
    public void checkLogin() {

        Toast.makeText(getContext(), " Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void setErrorUsername() {
        edtUsername.setError("nhập Username");

    }

    @Override
    public void setErrorPassword() {
        edtPassword.setError("nhập Password");

    }

    @Override
    public void navigate() {
        Toast.makeText(getContext(), " Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), FrmTruyen.class);
        startActivity(intent);

    }


}
