package com.example.avelino.firebaseautenticacao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class PrincipalActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void deslogar (View v) {
        firebaseAuth.signOut();

        Intent irParaTelaLogin = new Intent(PrincipalActivity.this, MainActivityLogin.class);
        startActivity(irParaTelaLogin);
        finish();

    }
}
