package com.example.trabalhosqlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.trabalhosqlite.model.Livro;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 4;
    // Nome do Banco de Dados
    private static final String DATABASE_NAME = "livros.db";
    //Necessário um construtor padrão
    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Todas as tabelas necessárias para o programa devem ser criadas aqui
        String CREATE_TABLE_LIVRO = "CREATE TABLE " + Livro.TABLE  + "("
                + Livro.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Livro.KEY_nome + " TEXT, "
                + Livro.KEY_isbn + " INTEGER, "
                + Livro.KEY_ano + " INTEGER, "
                + Livro.KEY_autor + " TEXT )";
        sqLiteDatabase.execSQL(CREATE_TABLE_LIVRO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Apgar as tabelas velhas, se existentes, todos os dados serão apagados!!!
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Livro.TABLE);
        // Criar tabelas novamente
        onCreate(sqLiteDatabase);
    }
}
