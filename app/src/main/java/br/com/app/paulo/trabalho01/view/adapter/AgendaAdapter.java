package br.com.app.paulo.trabalho01.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.app.paulo.trabalho01.R;
import br.com.app.paulo.trabalho01.model.Agenda;
import br.com.app.paulo.trabalho01.view.holder.AgendaViewHolder;

/**
 * Created by pvnluz on 06/04/2017.
 */

public class AgendaAdapter extends RecyclerView.Adapter  {

    private List<Agenda> contatos;
    private Context context;

    public AgendaAdapter(List<Agenda> contatos, Context context) {
        this.contatos = contatos;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.agenda_item_lista, parent, false);
        AgendaViewHolder holder = new AgendaViewHolder(view, this);
        return holder;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        AgendaViewHolder viewHolder = (AgendaViewHolder) holder;

        Agenda agenda  = contatos.get(position);

        ((AgendaViewHolder) holder).preencher(agenda);


    }

    @Override
    public int getItemCount() {
        return contatos.size();
    }

    public void remove(int position) {
        contatos.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }
}
