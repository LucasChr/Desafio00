package com.example.lucas.desafio00.desafio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lucas.desafio00.R;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicioToast();
    }


    public void abrirTela(View v){
        Intent it = new Intent(this, DesafioActivity.class);
        startActivity(it);
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Desafio","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Desafio","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Desafio","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Desafio","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Desafio","onDestroy");
    }

    public void inicioToast(){
        Toast.makeText(this,"Iniciando Aplicação",Toast.LENGTH_LONG).show();
    }

}
