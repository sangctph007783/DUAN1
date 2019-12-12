package com.example.DuAn1_SangCtph07783.activity;

public class LoginPresenter {
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }
    public void login(String username,
                      String password) {
        if (username.isEmpty()) {
            loginView.setErrorUsername();

        } else if (password.isEmpty()) {
            loginView.setErrorPassword();
        } else if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
            loginView.navigate();
        } else {
            loginView.checkLogin();
//        } if (){
//            loginView.LoginSuccessful();
//        }

        }
    }}

