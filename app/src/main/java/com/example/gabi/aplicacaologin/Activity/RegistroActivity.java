package com.example.gabi.aplicacaologin.Activity;

/**
 * Created by gabi on 15/04/17.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.gabi.aplicacaologin.Model.Usuario;
import com.example.gabi.aplicacaologin.SQL.DataBase;
import com.example.gabi.aplicacaologin.Validacao.Validacao;
import com.example.gabi.aplicacaologin.*;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = RegistroActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private Validacao validacao;
    private DataBase dataBase;
    private Usuario usuario;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextName = (TextInputEditText) findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    private void initObjects() {
        validacao = new Validacao(activity);
        dataBase = new DataBase(activity);
        usuario = new Usuario();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;

            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }

    private void postDataToSQLite() {
        if (!validacao.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.erro_mensagem_nome))) {
            return;
        }
        if (!validacao.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.erro_mensagem_email))) {
            return;
        }
        if (!validacao.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.erro_mensagem_email))) {
            return;
        }
        if (!validacao.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.erro_mensagem_password))) {
            return;
        }
        if (!validacao.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.erro_password_diferente))) {
            return;
        }

        if (!dataBase.checkUsuario(textInputEditTextEmail.getText().toString().trim())) {

            usuario.setNome(textInputEditTextName.getText().toString().trim());
            usuario.setEmail(textInputEditTextEmail.getText().toString().trim());
            usuario.setSenha(textInputEditTextPassword.getText().toString().trim());

            dataBase.adicionaUsuario(usuario);

            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            Snackbar.make(nestedScrollView, getString(R.string.erro_email_existe), Snackbar.LENGTH_LONG).show();
        }

    }

    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }

}
