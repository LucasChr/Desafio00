package com.example.lucas.desafio00.desafio;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lucas.desafio00.R;

public class DesafioActivity extends AppCompatActivity {


    TextView tvDNome, tvDEndereco, tvDTelefone, tvDSite;
    EditText edtNome, edtTelefone, edtEndereco, edtSite;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desafio);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtSite = (EditText) findViewById(R.id.edtSite);
        tvDNome = (TextView) findViewById(R.id.tvDNome);
        tvDEndereco = (TextView) findViewById(R.id.tvDEndereco);
        tvDTelefone = (TextView) findViewById(R.id.tvDTelefone);
        tvDSite = (TextView) findViewById(R.id.tvDSite);
    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("valor", tvDNome.getText().toString());
        outState.putString("valor2", tvDEndereco.getText().toString());
        outState.putString("valor3", tvDTelefone.getText().toString());
        outState.putString("valor4", tvDSite.getText().toString());
        Log.i("bundle", "save");
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        tvDNome.setText(bundle.getString("valor"));
        tvDEndereco.setText(bundle.getString("valor2"));
        tvDTelefone.setText(bundle.getString("valor3"));
        tvDSite.setText(bundle.getString("valor4"));
        Log.i("bundle", "restore");
    }




    public void confirmaDados(View v){
        setarNome();
        setarTelefone();
        setarEndereco();
        setarSite();
    }

    public void fazerLigacao(View v){
        Uri uri = Uri.parse("tel:" + edtTelefone.getText());
        Intent it = new Intent(Intent.ACTION_CALL, uri);
        startActivity(it);
    }

    public void abrirSite(View v){
        goToUrl( "http:" + edtSite.getText());
    }


    public void setarNome() {tvDNome.setText( edtNome.getText().toString());}
    public void setarTelefone(){tvDTelefone.setText(edtTelefone.getText().toString());}
    public void setarEndereco(){tvDEndereco.setText(edtEndereco.getText().toString());}
    public void setarSite(){tvDSite.setText(edtSite.getText().toString());}


    public void goToUrl(String url){
        Uri uri = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }


}
