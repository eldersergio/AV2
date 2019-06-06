package com.example.elder.av2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buscar();
    }

    public void buscar(){
        Consultar c = new Consultar();
        c.setAcao("buscar");
        //ListView lista = (ListView) findViewById(R.id.lw1);

        c.execute();
        String retorno = null;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        retorno = c.getRes();

        /*try {
            JSONArray jarray = new JSONArray(retorno);
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
    }
}
