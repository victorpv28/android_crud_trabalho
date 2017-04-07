package br.com.app.paulo.trabalho01.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import br.com.app.paulo.trabalho01.model.Agenda;


/**
 * Created by pvnluz on 06/04/2017.
 */

public class AgendaDAO {

    private SQLiteDatabase db;
    private DBOpenHelper dbo;

    public AgendaDAO(Context context) {
        dbo = new DBOpenHelper(context);
    }

    private static final String TABELA_AGENDA = "agenda";
    private static final String COLUNA_ID = "id";
    private static final String COLUNA_NOME = "nome";
    private static final String COLUNA_TELEFONE = "telefone";
    private static final String COLUNA_NOTA = "nota";

    public String add(Agenda agenda) {

        long resultado;
        SQLiteDatabase db = dbo.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, agenda.getNome());
        values.put(COLUNA_TELEFONE, agenda.getTelefone());
        values.put(COLUNA_NOTA, agenda.getNota());
        resultado = db.insert(TABELA_AGENDA,
                null,
                values);
        db.close();
        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }


    public String update(Agenda agenda, Long id) {

        long resultado;
        SQLiteDatabase db = dbo.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, agenda.getNome());
        values.put(COLUNA_TELEFONE, agenda.getTelefone());
        values.put(COLUNA_NOTA, agenda.getNota());
        resultado = db.update(AgendaDAO.TABELA_AGENDA, values, AgendaDAO.COLUNA_ID + "=" + id, null);

        if (resultado == -1) {
            return "Erro ao inserir registro";
        } else {
            return "Registro inserido com sucesso";
        }
    }

    public List<Agenda> getAll() {
        List<Agenda> agendas = new LinkedList<>();
        String rawQuery = "SELECT id, nome, telefone, nota FROM " +
                AgendaDAO.TABELA_AGENDA;
        SQLiteDatabase db = dbo.getReadableDatabase();
        Cursor cursor = db.rawQuery(rawQuery, null);
        Agenda agenda = null;
        if (cursor.moveToFirst()) {
            do {
                agenda = new Agenda();
                agenda.setId(cursor.getLong(0));
                agenda.setNome(cursor.getString(1));
                agenda.setTelefone(cursor.getString(2));
                agenda.setNota(cursor.getDouble(3));
                agendas.add(agenda);
            } while (cursor.moveToNext());
        }
        return agendas;
    }

    public Agenda getAgendaById(Long id) {
        SQLiteDatabase db = dbo.getWritableDatabase();
        String query = "SELECT id, nome, telefone, nota FROM " + AgendaDAO.TABELA_AGENDA + " WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        Agenda agenda = new Agenda();
        agenda.setId(cursor.getLong(0));
        agenda.setNome(cursor.getString(1));
        agenda.setTelefone(cursor.getString(2));
        agenda.setNota(cursor.getDouble(3));
        db.close();
        return agenda;
    }

    public void deletaContato(Long id) {
        String where = COLUNA_ID + "=" + id;
        SQLiteDatabase db = dbo.getWritableDatabase();
        db.delete(AgendaDAO.TABELA_AGENDA, where, null);

    }

}
