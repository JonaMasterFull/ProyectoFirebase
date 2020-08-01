package com.example.proyectofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class inicio extends AppCompatActivity implements View.OnClickListener {

    //Definimos view objetos
    private EditText TextoEmail;
    private EditText TextoPassword;
    private Button Registrar;
    private ProgressDialog progressDialog;

    //Declaramos un Objeto
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Inicialiazamos el objeto firebase
        mAuth = FirebaseAuth.getInstance();

        //Referenciasmos los views
        TextoEmail = (EditText) findViewById(R.id.TextEmail);
        TextoPassword = (EditText) findViewById(R.id.TextPassword);

        Registrar = (Button) findViewById(R.id.RegistroButton);

        progressDialog = new ProgressDialog(this);

        Registrar.setOnClickListener(this);

    }

    private  void RegistrarUsuario(){

        String Email = TextoEmail.getText().toString().trim();
        String Password = TextoPassword.getText().toString().trim();

        if(TextUtils.isEmpty(Email)){
            Toast.makeText(this,"Se debe Ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(Password)){
            Toast.makeText(this,"Se debe Ingresar un password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizado Registro Listo.!!");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(inicio.this,"Se ha registrado el Email",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(inicio.this,"No se puede Registrar",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        RegistrarUsuario();
    }
}