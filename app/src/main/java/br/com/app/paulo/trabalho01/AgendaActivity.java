package br.com.app.paulo.trabalho01;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import br.com.app.paulo.trabalho01.dao.AgendaDAO;
import br.com.app.paulo.trabalho01.model.Agenda;

public class AgendaActivity extends AppCompatActivity {

    public final static int CODE_AGENDA = 1002;
    private TextInputLayout tilNome;
    private TextInputLayout tilTelefone;
    private EditText etNome;
    private EditText etTelefone;
    private RatingBar rbnota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        tilNome = (TextInputLayout) findViewById(R.id.tilNome);
        tilTelefone = (TextInputLayout) findViewById(R.id.tilTelefone);

        etNome = (EditText) findViewById(R.id.etNome);
        etTelefone = (EditText) findViewById(R.id.etTelefone);

        rbnota = (RatingBar) findViewById(R.id.rbNota);

        carregarAgenda();
    }

    public void cadastrar(View v) {
        final Bundle extras = getIntent().getExtras();
        Long agendaId = (extras != null) ? extras.getLong("agendaId") : null;
        if (agendaId == null) {
            AgendaDAO agendaDAO = new AgendaDAO(this);
            Agenda agenda = new Agenda();
            agenda.setNome(tilNome.getEditText().getText().toString());
            agenda.setTelefone(tilTelefone.getEditText().getText().toString());
            agenda.setNota(Double.valueOf(rbnota.getProgress()));
            agendaDAO.add(agenda);
        } else {
            Agenda agenda = new Agenda();
            AgendaDAO agendaDAO = new AgendaDAO(this);
            agenda.setNome(tilNome.getEditText().getText().toString());
            agenda.setTelefone(tilTelefone.getEditText().getText().toString());
            agenda.setNota(Double.valueOf(rbnota.getProgress()));
            agendaDAO.update(agenda, agendaId);

        }
        retornaParaTelaAnterior();
    }


    //retorna para tela de lista de torcedores
    public void retornaParaTelaAnterior() {
        Intent intentMessage = new Intent();
        setResult(CODE_AGENDA, intentMessage);
        finish();
    }

    public void carregarAgenda() {
        final Bundle extras = getIntent().getExtras();
        Long agendaId = (extras != null) ? extras.getLong("agendaId") : null;

        if (agendaId == null) {
            Agenda agenda = new Agenda();
        } else {
            Agenda agenda = new Agenda();
            AgendaDAO agendaDAO = new AgendaDAO(this);
            agenda = agendaDAO.getAgendaById(agendaId);
            etNome.setText(agenda.getNome().toString());
            etTelefone.setText(agenda.getTelefone().toString());
            rbnota.setProgress(agenda.getNota().intValue());

        }
    }

}
