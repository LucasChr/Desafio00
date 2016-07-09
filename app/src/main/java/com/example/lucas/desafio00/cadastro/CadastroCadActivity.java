package com.example.lucas.desafio00.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.desafio00.R;

public class CadastroCadActivity extends Activity {

    CadastroDAO dao;

    final int MENU_SALVAR=1;
    final int MENU_BUSCAR=2;
    final int MENU_ALTERAR=3;
    final int MENU_EXCLUIR=4;

    EditText edtNome, edtTelefone, edtEndereco, edtSite,edtID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cad);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtEndereco = (EditText) findViewById(R.id.edtEndereco);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        edtSite = (EditText) findViewById(R.id.edtSite);
        edtID = (EditText) findViewById(R.id.edtID);

        dao = new CadastroDAO(this);

        Intent it = getIntent();
    }

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
        Cadastro c = dao.buscar(edtID.getText().toString());
        edtNome.setText(c.getNome());
        edtEndereco.setText(c.getEndereco());
        edtTelefone.setText(c.getTelefone());
        edtSite.setText(c.getSite());
        setResult(2);
    }

    public void alterar(){
        Cadastro c = new Cadastro();
        c.setId(new Long(edtID.getText().toString()));
        c.setNome(edtNome.getText().toString());
        c.setEndereco(edtEndereco.getText().toString());
        c.setTelefone(edtTelefone.getText().toString());
        c.setSite(edtSite.getText().toString());
        dao.alterar(c);
        setResult(3);
    }

    public void excluir(){
        dao.excluir(edtID.getText().toString());
        setResult(4);
    }



}
