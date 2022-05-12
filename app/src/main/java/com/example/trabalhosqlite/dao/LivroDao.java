package com.example.trabalhosqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.trabalhosqlite.helper.DBHelper;
import com.example.trabalhosqlite.model.Livro;

import java.util.ArrayList;
import java.util.HashMap;

public class LivroDao {
    public DBHelper dbHelper;

    public LivroDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    public int insert(Livro livro) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Livro.KEY_nome, livro.getNome());
        values.put(Livro.KEY_isbn, livro.getIsbn());
        values.put(Livro.KEY_ano, livro.getAno());
        values.put(Livro.KEY_autor, livro.getAutor());

        long livro_Id = db.insert(Livro.TABLE, null, values);
        db.close();
        return (int) livro_Id;
    }

    public void delete(int livro_Id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(Livro.TABLE, Livro.KEY_ID + "= ?", new String[]{
                String.valueOf(livro_Id)});
        db.close();
    }

    public void update(Livro livro) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Livro.KEY_nome, livro.getNome());
        values.put(Livro.KEY_isbn, livro.getIsbn());
        values.put(Livro.KEY_ano, livro.getAno());
        values.put(Livro.KEY_autor, livro.getAutor());

        db.update(Livro.TABLE, values, Livro.KEY_ID + "= ?", new String[]{
                String.valueOf(livro.getLivroid())});
        db.close(); // Fechando a conexão
    }

    public ArrayList<HashMap<String, String>> getLivroList() {
        //Abrindo a conexão apenas para leitura
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Livro.KEY_ID + "," +
                Livro.KEY_nome + "," +
                Livro.KEY_isbn + "," +
                Livro.KEY_ano + "," +
                Livro.KEY_autor +
                " FROM " + Livro.TABLE;
        ArrayList<HashMap<String, String>> livrolista = new ArrayList<HashMap<String, String>>();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> livro = new HashMap<String, String>();
                livro.put("id", cursor.getString(cursor.getColumnIndexOrThrow(Livro.KEY_ID)));
                livro.put("name", cursor.getString(cursor.getColumnIndexOrThrow(Livro.KEY_nome)));
                livrolista.add(livro);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return livrolista;
    }

    public Livro getLivroById(int Id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery = "SELECT  " +
                Livro.KEY_ID + "," +
                Livro.KEY_nome + "," +
                Livro.KEY_isbn + "," +
                Livro.KEY_ano + "," +
                Livro.KEY_autor +
                " FROM " + Livro.TABLE
                + " WHERE " + Livro.KEY_ID + "=?";

        Livro livro = new Livro();
        Cursor cursor = db.rawQuery(selectQuery, new String[]{String.valueOf(Id)});
        if (cursor.moveToFirst()) {
            do {
                livro.setLivroid(cursor.getInt(cursor.getColumnIndexOrThrow(Livro.KEY_ID)));
                livro.setNome(cursor.getString(cursor.getColumnIndexOrThrow(Livro.KEY_nome)));
                livro.setIsbn(cursor.getInt(cursor.getColumnIndexOrThrow(Livro.KEY_isbn)));
                livro.setAno(cursor.getInt(cursor.getColumnIndexOrThrow(Livro.KEY_ano)));
                livro.setAutor(cursor.getString(cursor.getColumnIndexOrThrow(Livro.KEY_autor)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return livro;
    }
}

