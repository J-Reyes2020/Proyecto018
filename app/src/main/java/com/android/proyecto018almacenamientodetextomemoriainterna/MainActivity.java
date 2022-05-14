package com.android.proyecto018almacenamientodetextomemoriainterna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    EditText et2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = findViewById(R.id.id_etxt_us);
        et2 = findViewById(R.id.id_etxt_muestra);
    }

    public void onClickGrabar(View view) {
        String nombreArchivo= et1.getText().toString();
        String contenido = et2.getText().toString();
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(nombreArchivo, Context.MODE_PRIVATE));
            archivo.write(contenido);
            archivo.flush();
            archivo.close();
            et1.setText("");
            et2.setText("");
            Toast.makeText(this, "Los datos fueron grabados correctamente", Toast.LENGTH_SHORT).show();
        }catch (IOException e){
            Toast.makeText(this, "No se pudo crear el Archivo", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickRecuoerar(View view) {
        String nomArchivo = et1.getText().toString();
        try {
            InputStreamReader archivo = new InputStreamReader(openFileInput(nomArchivo));
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            String contenido = "";
            while (linea!=null){
                contenido = contenido+linea+"\n";
                linea = br.readLine();
            }
            br.close();
            archivo.close();
            et2.setText(contenido);
            Toast.makeText(this,"Archivo Ubicado", Toast.LENGTH_LONG).show();
        }catch (IOException e){
            Toast.makeText(this, "No Existe el Archivo", Toast.LENGTH_SHORT).show();
        }
    }
}