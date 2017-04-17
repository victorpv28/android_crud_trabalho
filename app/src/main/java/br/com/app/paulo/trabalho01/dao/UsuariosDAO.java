package br.com.app.paulo.trabalho01.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.app.paulo.trabalho01.model.Usuarios;

/**
 * Created by pvnluz on 17/04/2017.
 */

public class UsuariosDAO {

    private SQLiteDatabase db;
    private DBOpenHelper dbo;

    public UsuariosDAO(Context context) {
        dbo = new DBOpenHelper(context);
    }

    private static final String TABELA_USUARIO = "usuario";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_LOGIN = "login";
    private static final String COLUNA_SENHA = "senha";

    public String add(Usuarios usuarios) {

        long resultado;
        SQLiteDatabase db = dbo.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_LOGIN, usuarios.getLogin());
        values.put(COLUNA_SENHA, usuarios.getSenha());
        resultado = db.insert(TABELA_USUARIO,
                null,
                values);
        db.close();
        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }

    public boolean procurarUsuarios(String login, String senha) {
        SQLiteDatabase db = dbo.getWritableDatabase();
        String query = "SELECT id, login, senha FROM " + UsuariosDAO.TABELA_USUARIO + " WHERE login = ? AND senha = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(login), String.valueOf(senha)});
        cursor.moveToFirst();

        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        Usuarios usuarios = new Usuarios();
        usuarios.setId(cursor.getLong(0));
        usuarios.setLogin(cursor.getString(1));
        usuarios.setSenha(cursor.getString(2));
        db.close();
        cursor.close();

        return true;

    }


}
