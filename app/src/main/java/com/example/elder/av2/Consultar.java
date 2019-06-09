package com.example.elder.av2;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by elder on 02/06/2019.
 */

public class Consultar extends AsyncTask<String, Void, String> {
    private String id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    private String res;
    private String acao;

    public String getId(){
        return this.id;
    }
    public String getNome(){
        return this.nome;
    }
    public String getEmail(){
        return this.email;
    }
    public String getLogin(){
        return this.login;
    }
    public String getSenha(){
        return this.senha;
    }
    public String getRes(){ return this.res; }
    public String getAcao(){ return this.acao; }

    public void setId(String id){
        this.id = id;
    }
    public void setNome(String n){
        this.nome = n;
    }
    public void setEmail(String e){
        this.email = e;
    }
    public void setLogin(String l){
        this.login = l;
    }
    public void setSenha(String s){
        this.senha = s;
    }
    public void setRes(String r){
        this.res = r;
    }
    public void setAcao(String a){
        this.acao = a;
    }

    @Override
    protected String doInBackground(String... params) {
        String t = "";
        try {
            t = buscaServidor();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        /*if(s.contains("logon")){
            Log.i("retorno3", "login feito com sucesso");
        }else{
            Log.i("retorno3", "login mal sucedido");
        }*/
        if (s != null) {
            //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
           // this.res = s;
            Log.i("retorno1", s);
        }
    }

    public String buscaServidor() throws MalformedURLException {



        HttpURLConnection connection;
        OutputStreamWriter request = null;

        URL url = null;
        String response = null;
        String parameters = "";
        if(this.acao == "login") {
            parameters = "login=" + this.login + "&senha=" + this.senha+"&acao="+this.acao;
        }else if (this.acao == "cadastrar"){
            parameters = "nome="+this.nome+"&email="+this.email+"&login=" + this.login + "&senha=" + this.senha+"&acao="+this.acao;
        }else if (this.acao == "buscar"){
            parameters = "acao="+this.acao;
        }else if (this.acao == "buscar2"){
            parameters = "id="+this.id+"&acao="+this.acao;
        }else if (this.acao == "update"){
            parameters = "id="+this.id+"&nome="+this.nome+"&email="+this.email+"&login=" + this.login + "&senha=" + this.senha+"&acao="+this.acao;
        }

        url = new  URL("http://jacksonsilva.dev/sistema/ws_android.php");
        try {
        connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("X-DreamFactory-Api-Key", "XXXXXXXXXXXXXXXXXXXX");
        connection.setRequestProperty("X-DreamFactory-Session-Token", "key");
        connection.setRequestProperty("Authorization", "auth");
        connection.setRequestMethod("POST");

        request = new OutputStreamWriter(connection.getOutputStream());
        request.write(parameters);
        request.flush();
        request.close();
        connection.connect();

        //response = connection.getResponseMessage();
        String line = "";
        InputStreamReader isr = new InputStreamReader(connection.getInputStream());
        BufferedReader reader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        response = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.res = response;

        return response;
    }
}
