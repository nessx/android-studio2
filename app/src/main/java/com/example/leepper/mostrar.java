package com.example.leepper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class mostrar extends AppCompatActivity {

    String [] array_noms;
    int [] array_nota;
    int [] array_nota2;
    int [] array_nota3;
    float [] calculos;
    int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent recogerDatos = getIntent();
        array_noms = recogerDatos.getStringArrayExtra("array_noms");
        array_nota = recogerDatos.getIntArrayExtra("array_nota");
        array_nota2 = recogerDatos.getIntArrayExtra("array_nota2");
        array_nota3 = recogerDatos.getIntArrayExtra("array_nota3");
        contador = recogerDatos.getIntExtra("contador", 0);

        ArrayList<String> personNames = new ArrayList<>();
        calculos = calco(contador,array_nota,array_nota2,array_nota3);
        for (int i=0;i<contador;i++){
            float mid = calculos[i];
            DecimalFormat df = new DecimalFormat("#.#");
            df.setRoundingMode(RoundingMode.CEILING);

            //antes solo tenia calculos[i] para imprimir luego añadi el float
            personNames.add(array_noms[i]+ "----> "+"\t"+array_nota[i]+", "+array_nota2[i]+", "+array_nota3[i]+" MEDIA---> "+df.format(mid));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        com.example.leepper.CustomAdapter customAdapter = new com.example.leepper.CustomAdapter(mostrar.this, personNames);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }

    public static float [] calco(int contador, int [] array_nota, int [] array_nota2, int [] array_nota3){
        float [] result = new float [contador];
        float suma;
        for(int i=0;i<contador;i++){
            suma=array_nota[i]+array_nota2[i]+array_nota3[i];
            result [i] = suma/3.0f;
            suma=0;
            Log.d("PRUEBA", "Esta es la prueba "+result[i]);
        }
        return result;

    }

}
