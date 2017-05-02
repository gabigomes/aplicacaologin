package com.example.gabi.aplicacaologin.SQL;

/**
 * Created by gabi on 15/04/17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import  com.example.gabi.aplicacaologin.Model.Usuario;

public class DataBase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    //Nome do banco de dados.
    private static final String DATABASE_NAME = "LoginDataBase.db";

    //Nome da tabela
    private static final String TABLE_USUARIO = "usuario";

    //Colunas
    private static final String COLUMN_USUARIO_ID = "usuario_id";
    private static final String COLUMN_USUARIO_NOME = "usuario_nome";
    private static final String COLUMN_USUARIO_EMAIL = "usuario_email";
    private static final String COLUMN_USUARIO_SENHA = "usuario_senha";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USUARIO + "("
            + COLUMN_USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USUARIO_NOME + " TEXT,"
            + COLUMN_USUARIO_EMAIL + " TEXT," + COLUMN_USUARIO_SENHA + " TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USUARIO;

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public void adicionaUsuario(Usuario user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USUARIO_NOME, user.getNome());
        values.put(COLUMN_USUARIO_EMAIL, user.getEmail());
        values.put(COLUMN_USUARIO_SENHA, user.getSenha());

        db.insert(TABLE_USUARIO, null, values);
        db.close();
    }

    public String procuraUsuario(String email) {
        String nomeUsuario = "null";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c=db.rawQuery("SELECT usuario_nome FROM usuario WHERE usuario_email='"+email+"'", null);
        if (c.moveToFirst()) {
            nomeUsuario = c.getString(0);
        }
        return nomeUsuario;
    }

    public List<Usuario> getAllUser() {

        String[] columns = {
                COLUMN_USUARIO_ID,
                COLUMN_USUARIO_EMAIL,
                COLUMN_USUARIO_NOME,
                COLUMN_USUARIO_SENHA
        };

        String sortOrder = COLUMN_USUARIO_NOME + " ASC";
        List<Usuario> userList = new ArrayList<Usuario>();

        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = db.query(TABLE_USUARIO,
                columns,
                null,
                null,
                null,
                null,
                sortOrder);

        if (cursor.moveToFirst()) {
            do {
                Usuario user = new Usuario();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USUARIO_ID))));
                user.setNome(cursor.getString(cursor.getColumnIndex(COLUMN_USUARIO_NOME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USUARIO_EMAIL)));
                user.setSenha(cursor.getString(cursor.getColumnIndex(COLUMN_USUARIO_SENHA)));
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return userList;
    }

    public boolean checkUsuario(String email) {

        String[] columns = {
                COLUMN_USUARIO_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USUARIO_EMAIL + " = ?";

        String[] selectionArgs = {email};


        Cursor cursor = db.query(TABLE_USUARIO,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkUser(String email, String password) {

        String[] columns = {
                COLUMN_USUARIO_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_USUARIO_EMAIL + " = ?" + " AND " + COLUMN_USUARIO_SENHA + " = ?";

        String[] selectionArgs = {email, password};


        Cursor cursor = db.query(TABLE_USUARIO,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

}
