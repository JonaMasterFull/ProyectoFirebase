package com.example.proyectofirebase;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText TextoEmail;
    private EditText TextoPassword;
    private Button btnLogin;
    private Button btnRegistro;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        //Referenciasmos los views
        TextoEmail = (EditText) findViewById(R.id.TextEmail);
        TextoPassword = (EditText) findViewById(R.id.TextPassword);

        btnLogin = (Button) findViewById(R.id.loginButton);
        btnRegistro = (Button) findViewById(R.id.RegistroButton);

        progressDialog = new ProgressDialog(this);

        btnLogin.setOnClickListener(this);
        btnRegistro.setOnClickListener(this);
    }
    private void Registro(){
        Intent registro = new Intent(MainActivity.this,inicio.class);
        startActivity(registro);
    }

    private void AutenticarUsuarios(){
            String Email = TextoEmail.getText().toString().trim();
            String Pass = TextoPassword.getText().toString().trim();

        if(TextUtils.isEmpty(Email)){
            Toast.makeText(this,"Se debe Ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(Pass)){
            Toast.makeText(this,"Se debe Ingresar un password",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Autenticacion Listo.!!");
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(Email,Pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,"Bienvenido",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplication(), Welcome.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this,"Verifique Email o Contraseña",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){

           case R.id.loginButton:
               AutenticarUsuarios();
               break;
           case R.id.RegistroButton:
               Registro();
            break;
       }

    }
}