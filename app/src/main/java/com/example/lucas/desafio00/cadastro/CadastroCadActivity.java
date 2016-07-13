package com.example.lucas.desafio00.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lucas.desafio00.R;

public class CadastroCadActivity extends Activity {

    CadastroDAO dao;

    final int MENU_SALVAR=1;
    final int MENU_ALTERAR=2;
    final int MENU_BUSCAR=3;
    final int MENU_EXCLUIR=4;

    EditText edtNome, edtTelefone, edtEndereco, edtSite,edtID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cad);

        edtNome = (EditText) findViewById(R.id.cadastro_cad_edtNome);
        edtEndereco = (EditText) findViewById(R.id.cadastro_cad_edtEndereco);
        edtTelefone = (EditText) findViewById(R.id.cadastro_cad_edtTelefone);
        edtSite = (EditText) findViewById(R.id.cadastro_cad_edtSite);
        edtID = (EditText) findViewById(R.id.cadastro_cad_edtID);

        dao = new CadastroDAO(this);

        Intent it = getIntent();

        if(!it.getStringExtra("ID").equals("")){
            String id = it.getStringExtra("ID");
            edtID.setText(id);
            buscar();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0,MENU_SALVAR,0,"Salvar");
        menu.add(0,MENU_ALTERAR,0,"Alterar");
        menu.add(0,MENU_BUSCAR,0,"Buscar");
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
            case MENU_ALTERAR:
                alterar();
                break;
            case MENU_BUSCAR:
                buscar();
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
        setResult(MENU_SALVAR);
        Log.i("Cadastro","Salvo corretamente");
        finish();
    }

    public void alterar(){
        Cadastro c = new Cadastro();
        c.setId(new Long(edtID.getText().toString()));
        c.setNome(edtNome.getText().toString());
        c.setEndereco(edtEndereco.getText().toString());
        c.setTelefone(edtTelefone.getText().toString());
        c.setSite(edtSite.getText().toString());
        dao.alterar(c);
        setResult(MENU_ALTERAR);
        finish();
    }

    public void buscar(){
        Cadastro c = dao.buscar(edtID.getText().toString());
        edtNome.setText(c.getNome());
        edtEndereco.setText(c.getEndereco());
        edtTelefone.setText(c.getTelefone());
        edtSite.setText(c.getSite());
//        setResult(MENU_BUSCAR);
    }

    public void excluir(){
        dao.excluir(edtID.getText().toString());
        //setResult(4);
        finish();
    }


    public void fazerLigacao(View v){
        Uri uri = Uri.parse("tel:" + edtTelefone.getText());
        Intent it = new Intent(Intent.ACTION_CALL, uri);
        startActivity(it);
    }

    public void abrirSite(View v){
        goToUrl( "http:" + edtSite.getText());
    }

    public void goToUrl(String url){
        Uri uri = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

}
