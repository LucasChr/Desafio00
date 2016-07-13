package com.example.lucas.desafio00.cadastro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.lucas.desafio00.sqlite.BancoDados;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 03/07/16.
 */
public class CadastroDAO {

    SQLiteDatabase db;

    public CadastroDAO(Context context){ db = BancoDados.getDB(context);}

    public void salvar(Cadastro cadastro){
        ContentValues values = new ContentValues();
        values.put("nome", cadastro.getNome());
        values.put("endereco", cadastro.getEndereco());
        values.put("telefone", cadastro.getTelefone());
        values.put("site", cadastro.getSite());
        db.insert("tbl_desafio",null,values);
        Log.i("Cadastro","Salvo");
    }

    public void alterar(Cadastro cadastro){
        ContentValues v = new ContentValues();
        v.put("nome", cadastro.getNome());
        v.put("endereco", cadastro.getEndereco());
        v.put("telefone", cadastro.getTelefone());
        v.put("site", cadastro.getSite());

        String id = String.valueOf(cadastro.getId());
        String[] whereArgs = new String[]{id};

        db.update("tbl_desafio",v,"_id = ?",whereArgs);
    }

    public Cadastro buscar(String id){

        String[] col = new String[]{"_id","nome","endereco","telefone","site"};
        String[] whereArgs = new String[]{id};
        Cursor c = db.query("tbl_desafio",col, "_id = ?", whereArgs,null,null,null);
        c.moveToFirst();

        Cadastro cadastro = new Cadastro();
            cadastro.setId(c.getLong(c.getColumnIndex("_id")));
            cadastro.setNome(c.getString(c.getColumnIndex("nome")));
            cadastro.setEndereco(c.getString(c.getColumnIndex("endereco")));
            cadastro.setTelefone(c.getString(c.getColumnIndex("telefone")));
            cadastro.setSite(c.getString(c.getColumnIndex("site")));

        return cadastro;
    }

    public void excluir(String id){
        String[] whereArgs = new String[]{id};
        db.delete("tbl_desafio","_id=?",whereArgs);
    }


    public List<Cadastro> listar(){

        String[] col = new String[]{"_id","nome","endereco","telefone","site"};
        Cursor cursor = db.query("tbl_desafio",col,null,null,null,null,null);

        List<Cadastro>cadastros = new ArrayList<Cadastro>();
        if(cursor.moveToFirst()){
            do{
                Cadastro c = new Cadastro();
                c.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                c.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                c.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
                c.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
                c.setSite(cursor.getString(cursor.getColumnIndex("site")));

                cadastros.add(c);
            }while (cursor.moveToNext());
        }

        return cadastros;
    }

}
