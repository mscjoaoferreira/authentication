package com.example.sistema;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sistema.modelo.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etEmailLogin_Firebase;
    private EditText edTextSenhaLogar_Firebase;
    private Button btLogar_Firebase;
    private FirebaseAuth mAuth;
    private Usuario u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        etEmailLogin_Firebase = findViewById(R.id.etEmailLogin_Firebase2);
        edTextSenhaLogar_Firebase = findViewById(R.id.edTextSenhaLogar_Firebase2);
        btLogar_Firebase = findViewById(R.id.btLogar_Firebase);

        btLogar_Firebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("========  ENTROU AQUIIII =======");
                receberDados();

                logar();
            }
        });


    }

    private void logar() {
        // pego daqui: https://firebase.google.com/docs/auth/android/start

        mAuth.signInWithEmailAndPassword(u.getEmail(), u.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();

                            //Feito o login, pode abrir a página desejada!
                            startActivity(new Intent(LoginActivity.this,PrincipalActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(LoginActivity.this, "Authenticação Falhou!.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void receberDados() {
        u = new Usuario();
    u.setEmail(etEmailLogin_Firebase.getText().toString());
    u.setSenha(edTextSenhaLogar_Firebase.getText().toString());



    }
}