package com.example.avelino.firebaseautenticacao;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * classe exemplo de autenticacao no Firebase.
 *
 * Atencao. os criar a conta de login. automaticamente o
 * usuario ficara logado no app ate ele solicitar a saida
 *
 */
public class MainActivityLogin extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private EditText login;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        login = (EditText) findViewById(R.id.formulario_login_login);
        senha = (EditText) findViewById(R.id.formulario_login_senha);

        firebaseAuth = FirebaseAuth.getInstance();

        //criarLoginESenhaFirebase();
        //realizarLogin();

        verificaUsuarioEstaLogado();

        //deslogarUsuario();

    }

    private void deslogarUsuario() {
        firebaseAuth.signOut();
    }

    private void verificaUsuarioEstaLogado() {

        if(firebaseAuth.getCurrentUser() != null) {
            Log.i("Firebase", "Voce ja esta logado. " +firebaseAuth.getCurrentUser().getEmail());
            Toast.makeText(MainActivityLogin.this, "Voce ja esta logado", Toast.LENGTH_LONG).show();

            Intent irParaTelaPrincipal = new Intent(MainActivityLogin.this, PrincipalActivity.class);
            startActivity(irParaTelaPrincipal);
            finish();

        } else {
            Log.i("Firebase", "Voce nao esta logado. log novamente");
            Toast.makeText(MainActivityLogin.this, "Voce nao esta logado. log novamente", Toast.LENGTH_LONG).show();
        }

    }

    public void realizarLogin(View view) {

        Editable login =  this.login.getText();
        Editable senha =  this.senha.getText();

        Toast.makeText(MainActivityLogin.this, "Login e senha: " +login.toString() + " - " + senha.toString(), Toast.LENGTH_LONG).show();

        firebaseAuth.signInWithEmailAndPassword(login.toString(), senha.toString())
                .addOnCompleteListener(MainActivityLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Log.i("Firebase", "Logado com sucesso");
                            Toast.makeText(MainActivityLogin.this, "Logado com sucesso", Toast.LENGTH_LONG).show();
                            Intent irParaTelaPrincipal = new Intent(MainActivityLogin.this, PrincipalActivity.class);
                            startActivity(irParaTelaPrincipal);
                            finish();

                        } else {
                            Log.i("Firebase", "ERRO  ao logar" + task.getException());
                            Toast.makeText(MainActivityLogin.this, "ERRO  ao logar." + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    /* exemplo cadastro de um email pelo app */
    public void criarLoginESenhaFirebase(View v) {

        Editable login =  this.login.getText();
        Editable senha =  this.senha.getText();

        firebaseAuth.createUserWithEmailAndPassword(login.toString(), senha.toString())
                .addOnCompleteListener(MainActivityLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.i("Firebase", "Login e senha criado com sucesso");
                            Toast.makeText(MainActivityLogin.this, "Login e senha criado com sucesso", Toast.LENGTH_LONG).show();
                        } else {
                            Log.i("Firebase", "ERRO  AO CRIAR Login e senha");
                            Toast.makeText(MainActivityLogin.this, "ERRO  AO CRIAR Login e senha", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
