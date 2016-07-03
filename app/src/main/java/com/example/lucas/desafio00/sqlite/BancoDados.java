package com.example.lucas.desafio00.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lucas on 29/06/16.
 */
public class BancoDados {

    private static final String NOME_BANCO = "desafio02";
    private static final int VERSAO_BANCO = 1;

    //Script
    private static final String[] SCRIPT_DATABASE_DELETE = new String[] {"drop table if exists tbl_desafio;"};

    //Tabela com id sequencial usa-se _id
    private static final String[] SCRIPT_DATABASE_CREATE = new String[]{
            "create table tbl_desafio(_id integer primary key, nome text, endereço text, telefone int, site text);"
    };

    private static SQLiteDatabase db;

    public static SQLiteDatabase getDB(Context ctx){
        if(db == null){
            SQLiteHelper dbHelper = new SQLiteHelper(ctx, NOME_BANCO,VERSAO_BANCO,SCRIPT_DATABASE_CREATE,SCRIPT_DATABASE_DELETE);
            db = dbHelper.getWritableDatabase();
        }
        return db;
    }
}
