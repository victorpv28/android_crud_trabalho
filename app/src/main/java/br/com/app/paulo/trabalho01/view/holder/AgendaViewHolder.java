package br.com.app.paulo.trabalho01.view.holder;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import br.com.app.paulo.trabalho01.AgendaActivity;
import br.com.app.paulo.trabalho01.R;
import br.com.app.paulo.trabalho01.dao.AgendaDAO;
import br.com.app.paulo.trabalho01.model.Agenda;
import br.com.app.paulo.trabalho01.view.adapter.AgendaAdapter;

/**
 * Created by pvnluz on 06/04/2017.
 */

public class AgendaViewHolder  extends RecyclerView.ViewHolder implements View.OnLongClickListener {

    public final TextView nome;
    public final TextView telefone;
    public final RatingBar nota;
    private Long agendaId;
    public final AgendaAdapter adapter;


    public AgendaViewHolder(final View view, final AgendaAdapter adapter){

        super(view);
        this.adapter = adapter;
        view.setOnLongClickListener(this);

        nome = (TextView) view.findViewById(R.id.tvNome);
        telefone = (TextView) view.findViewById(R.id.tvTelefone);
        nota = (RatingBar) view.findViewById(R.id.rbMostrarNota);

    }

    public void preencher(Agenda agenda) {
        agendaId = agenda.getId();
        nome.setText(agenda.getNome());
        telefone.setText(agenda.getTelefone());
        nota.setProgress(agenda.getNota().intValue());
    }

    @Override
    public boolean onLongClick(View v) {

        PopupMenu popup = new PopupMenu(v.getContext(), v);
        popup.getMenuInflater().inflate(R.menu.menu_agenda, popup.getMenu());

        final Activity context = (Activity)v.getContext();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.menuEditar:

                        final Intent intent = new Intent(context, AgendaActivity.class);
                        intent.putExtra("agendaId", agendaId);
                        context.startActivityForResult(intent, AgendaActivity.CODE_AGENDA);

                        break;

                    case R.id.menuDeletar:
                        AgendaDAO agendaDAO = new AgendaDAO(context);
                        agendaDAO.deletaContato(agendaId);
                        deletarAgenda();
                        break;

                }

                return true;
            }
        });

        popup.show();
        return false;
    }

    public void deletarAgenda(){
        adapter.remove(getAdapterPosition());
    }

}
