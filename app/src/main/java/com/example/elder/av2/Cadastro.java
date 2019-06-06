package com.example.elder.av2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        final EditText nome = (EditText) findViewById(R.id.nome);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText login = (EditText) findViewById(R.id.login);
        final EditText senha = (EditText) findViewById(R.id.senha);
        Button btn1 = (Button) findViewById(R.id.enviar);

        btn1.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v){

                Consultar c = new Consultar();

                c.setNome( nome.getText().toString());
                c.setEmail(email.getText().toString());
                c.setLogin( login.getText().toString());
                c.setSenha(senha.getText().toString());
                c.setAcao("cadastrar");

                String Teste = null;
                /*try {
                    Teste = c.buscaServidor();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }*/
                Teste = c.execute().toString();
                //Teste = c.getRes();

                Toast.makeText(getApplicationContext(), Teste, Toast.LENGTH_SHORT).show();

            }

        });
    }
}
