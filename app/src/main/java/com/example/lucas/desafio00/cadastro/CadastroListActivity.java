package com.example.lucas.desafio00.cadastro;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lucas.desafio00.R;

import java.util.List;

public class CadastroListActivity extends ListActivity {

    final int MENU_NOVO =1;
    final int MENU_SAIR =2;
    CadastroDAO dao;
    List<Cadastro> cadastros;
    CadastroListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_list);

        dao = new CadastroDAO(this);
        cadastros = dao.listar();

        adapter = new CadastroListAdapter(this, R.layout.activity_cadastro_list_item,cadastros);

        setListAdapter(adapter);
        Log.i("Cadastro","cadastro");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0,MENU_NOVO,0,"NOVO");
        menu.add(0,MENU_SAIR,0,"SAIR");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case MENU_NOVO:
                Intent intent= new Intent(this, CadastroCadActivity.class);
              // startActivity(intent);
                intent.putExtra("ID","");
                startActivityForResult(intent,1);
                break;
            case MENU_SAIR:
                //Fazer encerrar aplicação..
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        atualizaLista();

        if(resultCode == 1){
            Toast.makeText(this,"Salvo com sucesso",Toast.LENGTH_LONG).show();
        } else if(resultCode == 2 ){
            Toast.makeText(this, "Alterado com sucesso",Toast.LENGTH_LONG).show();
        }
        Log.i("Cadastro","Result");
    }

    public void atualizaLista(){
        List<Cadastro> cts = dao.listar();

        adapter.clear();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            adapter.addAll(cts);
        }

        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Cadastro c = cadastros.get(position);

        Intent intent = new Intent(this, CadastroCadActivity.class);
        intent.putExtra("ID",String.valueOf(c.getId()));
        startActivityForResult(intent,2);
    }
}
