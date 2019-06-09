package com.example.elder.av2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        final ListView lista = (ListView) findViewById(R.id.lw1);


        c.execute();
        String retorno = null;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        retorno = c.getRes();

        try {
            JSONArray jarray = new JSONArray(retorno);

                //Log.i("retornoJson", String.valueOf(jarray.getJSONObject(0)));

                /*Iterator<String> keys = jarray.getJSONObject(0).keys();

                while (keys.hasNext()){
                    // Captura a key e seu valor; e avança para a próxima
                    //System.out.println( arrayDentroDoArray.get( keys.next() ).toString() );
                    Log.i("retornoJson", jarray.getJSONObject(0).get( keys.next() ).toString());
                    nomes.add(jarray.getJSONObject(0).get( keys.next() ).toString());

                }
                Log.i("retornoStr", String.valueOf(nomes));*/

                //ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, (List<String>) jarray.getJSONObject(0));
                //lista.setAdapter(adapter);

            ArrayList<String> nomes = new ArrayList<String>();

            String id;
            String nome;

            for (int i = 0; i < jarray.length(); i++) {
                //...
                //Log.i("retornoJson", String.valueOf(jarray.getJSONObject(i)));
                JSONObject arrayDentroDoArray = jarray.getJSONObject(i);
                /*ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, (List<String>) arrayDentroDoArray);
                lista.setAdapter(adapter);*/
                Iterator<String> keys = arrayDentroDoArray.keys();

                // Verifica se há mais alguma key
                while (keys.hasNext()){
                    // Captura a key e seu valor; e avança para a próxima
                    //System.out.println( arrayDentroDoArray.get( keys.next() ).toString() );
                    id =arrayDentroDoArray.get( keys.next() ).toString();
                    nome =arrayDentroDoArray.get( keys.next() ).toString();
                    //Log.i("retornoJson", par);
                    nomes.add(id+"-"+nome);
                }

            }
            //Log.i("retornoStr", String.valueOf(nomes));
            ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, (List<String>) nomes);
            lista.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String  itemValue = (String) lista.getItemAtPosition(position);
                String[] id_user = itemValue.split("-");

                update(id_user[0]);
                // Show Alert
               // Toast.makeText(getApplicationContext(), "Position :"+itemPosition+"  ListItem : " +id_user[0] , Toast.LENGTH_LONG).show();


            }
        });


    }

    public void update(String id){
        Intent it = new Intent(this, Update.class);
        Bundle pa = new Bundle();
        pa.putString("id", id);
        it.putExtras(pa);
        startActivity(it);
    }
}
