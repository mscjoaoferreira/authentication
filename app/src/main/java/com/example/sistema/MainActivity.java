package com.example.sistema;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sistema.modelo.LoginActivityCode;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class MainActivity extends AppCompatActivity {

    private Button btLogar;
    private Button btCadastrar;
    private Button btCadastrar_LoginCode;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    private Button btLoginGoogle;
    private Button btLog_Off_Google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLogar = findViewById(R.id.btLogar);
        btCadastrar = findViewById(R.id.btCadastrar);
        btCadastrar_LoginCode = findViewById(R.id.btCadastrar_LoginCode);
        btLog_Off_Google = findViewById(R.id.btLog_Off_Google);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build();
        gsc = GoogleSignIn.getClient(this,gso);
        btLoginGoogle = findViewById(R.id.btLogar_Google);

        btLoginGoogle.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View view) {
                                                    logar_Google();
                                             }
                                         }
        );

        btLog_Off_Google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                log_Off_Google();
            }
        });


        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telaLogar();
            }
        });

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telaCadastrar();
            }
        });

        btCadastrar_LoginCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { telaLogar_Cred_Code(); }
        });
    }

    private void log_Off_Google() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                Toast.makeText(MainActivity.this, "Log Off Google!.",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void logar_Google() {
        Intent singInIntent = gsc.getSignInIntent();
        startActivityForResult(singInIntent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        //System.out.println("====== "+requestCode+"========");

        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                task.getResult(ApiException.class);
                navegarActivityPrincipal();


                /*
                GoogleSignInAccount account = task.getResult(ApiException.class);

                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);

                FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    navegarActivityPrincipal();
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Authenticação Google Falhou!.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        })*/
            }
            catch (ApiException e) {
                e.printStackTrace();
            }




        }
    }


    private void telaLogar_Cred_Code() {
        //Mudando de Activity
        // Sai do MainActivity e vai pro LoginActivityCode
        startActivity(new Intent(this, LoginActivityCode.class));
    }

    private void telaCadastrar() {
        //Mudando de Activity
        // Sai do this e vai pro CadastroActivity
        startActivity(new Intent(this,CadastroActivity.class));
    }

    private void telaLogar() {
        //Mudando de Activity
        // Sai do this e vai pro LoginActivity
        startActivity(new Intent(this,LoginActivity.class));

    }

    private void navegarActivityPrincipal() {
        //Mudando de Activity
        // Sai do this e vai pra PrincipalActivity
        finish();
        Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
        startActivity(intent);
    }

}