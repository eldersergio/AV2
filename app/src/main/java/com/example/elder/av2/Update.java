package com.example.elder.av2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Update extends AppCompatActivity {
String id_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        String id_user = null;
        String nome_user = null;
        String email_user = null;
        String login_user = null;
        String senha_user = null;
        final EditText nome = (EditText) findViewById(R.id.nome);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText login = (EditText) findViewById(R.id.login);
        final EditText senha = (EditText) findViewById(R.id.senha);
        Button btn1 = (Button) findViewById(R.id.enviar);
        Button btn2 = (Button) findViewById(R.id.remover);
        Consultar c = new Consultar();

        String resultado = null;
        String retorno;
        Intent it = getIntent();
        if (it != null){
            Bundle par = it.getExtras();
            if (par != null) {
                resultado = par.getString("id");
                //Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
            }
        }
        id_usuario = resultado;
        c.setId(resultado);
        c.setAcao("buscar2");
        c.execute();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        retorno = c.getRes();
        JSONObject json = null;
        try {
            json = new JSONObject(retorno);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject arrayDentroDoArray = null;
        arrayDentroDoArray = json;
        Iterator<String> keys = arrayDentroDoArray.keys();

        // Verifica se há mais alguma key
        while (keys.hasNext()){
            // Captura a key e seu valor; e avança para a próxima
            //System.out.println( arrayDentroDoArray.get( keys.next() ).toString() );
            try {
                id_user = arrayDentroDoArray.get( keys.next() ).toString();
                nome_user = arrayDentroDoArray.get( keys.next() ).toString();
                email_user = arrayDentroDoArray.get( keys.next() ).toString();
                login_user = arrayDentroDoArray.get( keys.next() ).toString();
                senha_user = arrayDentroDoArray.get( keys.next() ).toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Log.i("retornoJson", nome_user+" "+email_user);
        }

        nome.setText(String.valueOf(nome_user));
        email.setText(email_user);
        login.setText(login_user);
        senha.setText(senha_user);

        btn1.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v){

                Consultar c = new Consultar();

                c.setId(id_usuario);
                c.setNome( nome.getText().toString());
                c.setEmail(email.getText().toString());
                c.setLogin( login.getText().toString());
                c.setSenha(senha.getText().toString());
                c.setAcao("update");

                String Teste = null;
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
                Teste = c.getRes();

                if (Teste.contains("atualizado")){
                    GoHome();
                    Toast.makeText(getApplicationContext(), Teste, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), Teste, Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getApplicationContext(), Teste, Toast.LENGTH_SHORT).show();

            }

        });

        btn2.setOnClickListener(new Button.OnClickListener(){

            public void onClick(View v){

                Consultar c = new Consultar();

                c.setId(id_usuario);
                c.setAcao("deletar");

                String Teste = null;
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
                Teste = c.getRes();

                if (Teste.contains("removeu")){
                    GoHome();
                    Toast.makeText(getApplicationContext(), Teste, Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), Teste, Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(getApplicationContext(), Teste, Toast.LENGTH_SHORT).show();

            }

        });



    }

    public void GoHome(){

        Intent intent = new Intent(this, Home.class);
        startActivity(intent);

    }
}
