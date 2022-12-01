package com.example.sistema.modelo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistema.MainActivity;
import com.example.sistema.PrincipalActivity;
import com.example.sistema.R;

public class LoginActivityCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_code);

        TextView email_LoginCode = findViewById(R.id.etEmailLogin_Firebase2);
        TextView senha_LoginCode = findViewById(R.id.editTextTextPassword_LoginCode);
        Button btLogarAcLoginCode = findViewById(R.id.btLogar_Firebase);

        //Credenciais usadas: a e a

        btLogarAcLoginCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(email_LoginCode.getText().toString().equals("a") && senha_LoginCode.getText().toString().equals("a")){
                    //senha correta
                    Toast.makeText(LoginActivityCode.this,
                            "Login OK!",
                            Toast.LENGTH_SHORT).show();
                    navegarActivityPrincipal();

                }else{
                    // senha incorreta
                    Toast.makeText(LoginActivityCode.this,
                            "Não funcionou! ->> a",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    private void navegarActivityPrincipal() {

        finish();
        Intent intent = new Intent(LoginActivityCode.this, PrincipalActivity.class);
        startActivity(intent);
    }


}