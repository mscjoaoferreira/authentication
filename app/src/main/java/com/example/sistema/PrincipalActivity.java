package com.example.sistema;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity {

    private Button btVoltar_MainAcvitity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

    btVoltar_MainAcvitity = findViewById(R.id.bt_Voltar_MainActivity);

    btVoltar_MainAcvitity.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            navegarMainActivity();
        }
    });

    }

    private void navegarMainActivity() {

        finish();
        Intent intent = new Intent(PrincipalActivity.this, MainActivity.class);
        startActivity(intent);
    }

}