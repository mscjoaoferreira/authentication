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

public class CadastroActivity extends AppCompatActivity {


    private EditText etNome;
    private EditText etEmail;
    private EditText etSenha;
    private Button btCadastrar;
    private FirebaseAuth mAuth;
    private Usuario u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etNome = findViewById(R.id.etNome);
        etEmail = findViewById(R.id.etEmailLogin_Firebase2);
        etSenha = findViewById(R.id.etSenha);
        btCadastrar = findViewById(R.id.btCadastrar);
        mAuth = FirebaseAuth.getInstance();

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recuprardados();
                criarLogin();
            }
        });

    }

    private void criarLogin() {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(u.getEmail(),u.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(CadastroActivity.this,"Entrou no OnComplete", Toast.LENGTH_SHORT).show();
                if(task.isSuccessful()){
                    //
                    FirebaseUser user = mAuth.getCurrentUser();
                    u.setId(user.getUid());
                    u.salvarDados();

                    //Abrir a Tela
                    // Saiu da tela de Cadastro e Vai para tela Principal
                    startActivity(new Intent(CadastroActivity.this, PrincipalActivity.class));
                }else{
                    Toast.makeText(CadastroActivity.this,"Erro ao Criar Login", Toast.LENGTH_SHORT).show();
                }
            }


        });
        Toast.makeText(CadastroActivity.this,"final", Toast.LENGTH_SHORT).show();
    }

    private void recuprardados() {

        if(etNome.getText().toString().equals("") || etEmail.getText().toString().equals("") || etSenha.getText().toString().equals("")){
            Toast.makeText(this,"Você Deve preencher todos os campos!", Toast.LENGTH_SHORT).show();
        }else{
            //Recuperar os dados e criar o usuário



            u = new Usuario();
            u.setNome(etNome.getText().toString());
            u.setEmail(etEmail.getText().toString());
            u.setSenha(etSenha.getText().toString());


        }
    }

}