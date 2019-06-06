package com.example.elder.av2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {
    //Intent it = new Intent(this, Cadastro.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText login = (EditText) findViewById(R.id.login);
        final EditText senha = (EditText) findViewById(R.id.senha);
        Button btn1 = (Button) findViewById(R.id.button);
        Button btn2 = (Button) findViewById(R.id.cad);

        btn1.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v){

                Consultar c = new Consultar();

                c.setLogin( login.getText().toString());
                c.setSenha(senha.getText().toString());
                c.setAcao("login");

                String retorno = null;
                /*try {
                    Teste = c.buscaServidor();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }*/
                c.execute();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                retorno = c.getRes();

                if (retorno.contains("logon")){
                    Toast.makeText(getApplicationContext(), "Login efetuado com sucesso", Toast.LENGTH_SHORT).show();
                    logado();
                }else {

                    Toast.makeText(getApplicationContext(), "Login e Senha incorreto", Toast.LENGTH_SHORT).show();
                }

            }

        });

    }

    public void proximaTela(View v){

        Intent intent = new Intent(this, Cadastro.class);
        startActivity(intent);

    }

    public void logado(){

        Intent intent = new Intent(this, Home.class);
        startActivity(intent);

    }


}
