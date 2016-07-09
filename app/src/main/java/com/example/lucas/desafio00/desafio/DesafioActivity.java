package com.example.lucas.desafio00.desafio;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.desafio00.R;
import com.example.lucas.desafio00.cadastro.Cadastro;
import com.example.lucas.desafio00.cadastro.CadastroDAO;

public class DesafioActivity extends Activity {

    final int MENU_SALVAR=1;
    final int MENU_BUSCAR=2;
    final int MENU_ALTERAR=3;
    final int MENU_EXCLUIR=4;

    TextView tvID;
    EditText edtNome, edtTelefone, edtEndereco, edtSite;
    CadastroDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desafio);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtSite = (EditText) findViewById(R.id.edtSite);
        tvID = (TextView) findViewById(R.id.edtID);

        dao = new CadastroDAO(this);

        Intent it = getIntent();

    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("ID",tvID.getText().toString());
        outState.putString("valor", edtNome.getText().toString());
        outState.putString("valor2", edtEndereco.getText().toString());
        outState.putString("valor3", edtTelefone.getText().toString());
        outState.putString("valor4", edtSite.getText().toString());
        Log.i("bundle", "save");
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        tvID.setText(bundle.getString("ID"));
        edtNome.setText(bundle.getString("valor"));
        edtEndereco.setText(bundle.getString("valor2"));
        edtTelefone.setText(bundle.getString("valor3"));
        edtSite.setText(bundle.getString("valor4"));
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


    public void setarNome() {edtNome.setText( edtNome.getText().toString());}
    public void setarEndereco(){edtEndereco.setText(edtEndereco.getText().toString());}
    public void setarTelefone(){edtTelefone.setText(edtTelefone.getText().toString());}
    public void setarSite(){edtSite.setText(edtSite.getText().toString());}


    public void goToUrl(String url){
        Uri uri = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }


    //Menu do desafio 2
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0,MENU_SALVAR,0,"Salvar");
        menu.add(0,MENU_BUSCAR,0,"Buscar");
        menu.add(0,MENU_ALTERAR,0,"Alterar");
        menu.add(0,MENU_EXCLUIR,0,"Excluir");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case MENU_SALVAR:
                salvar();
                break;
            case MENU_BUSCAR:
                buscar();
                break;
            case MENU_ALTERAR:
                alterar();
                break;
            case MENU_EXCLUIR:
                excluir();
                break;
        }
        return true;
    }

    public void salvar(){
        Cadastro c = new Cadastro();
        c.setNome(edtNome.getText().toString());
        c.setEndereco(edtEndereco.getText().toString());
        c.setTelefone(edtTelefone.getText().toString());
        c.setSite(edtSite.getText().toString());
        dao.salvar(c);
        setResult(1);
        Toast.makeText(this,"SALVO COM SUCESSO",Toast.LENGTH_LONG).show();
    }

    public void buscar(){
        Cadastro c = dao.buscar(tvID.getText().toString());
        edtNome.setText(c.getNome());
        edtEndereco.setText(c.getEndereco());
        edtTelefone.setText(c.getTelefone());
        edtSite.setText(c.getSite());
        setResult(2);
        Toast.makeText(this,"Cadastro encontrado",Toast.LENGTH_LONG).show();
    }

    public void alterar(){
        Cadastro c = new Cadastro();
        c.setNome(edtNome.getText().toString());
        c.setEndereco(edtEndereco.getText().toString());
        c.setTelefone(edtTelefone.getText().toString());
        c.setSite(edtSite.getText().toString());
        dao.alterar(c);
        setResult(3);
        Toast.makeText(this,"Alterado com sucesso!",Toast.LENGTH_LONG).show();
    }

    public void excluir(){
        dao.excluir(tvID.getText().toString());
        setResult(4);
        Toast.makeText(this,"Cadastro excluído",Toast.LENGTH_LONG).show();
    }

}
