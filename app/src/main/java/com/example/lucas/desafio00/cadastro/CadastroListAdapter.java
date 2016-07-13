package com.example.lucas.desafio00.cadastro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lucas.desafio00.R;

import java.util.List;

/**
 * Created by lucas on 10/07/16.
 */
public class CadastroListAdapter extends ArrayAdapter<Cadastro> {

    int layout;
    Context ctx;
    List<Cadastro> cadastros;

    public CadastroListAdapter(Context context, int layout, List cadastros) {
        super(context, layout, cadastros);
        this.layout = layout;
        this.ctx = context;
        this.cadastros = cadastros;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = (LayoutInflater)
                ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inf.inflate(layout,null);

        TextView tvNome = (TextView) itemView.findViewById(R.id.cadastro_list_item_tvNome);
        TextView tvTelefone = (TextView) itemView.findViewById(R.id.cadastro_list_item_tvTelefone);
        TextView tvSite = (TextView) itemView.findViewById(R.id.cadastro_list_item_tvSite);

        Cadastro cadastro = cadastros.get(position);
        tvNome.setText(cadastro.getNome());
        tvTelefone.setText(cadastro.getTelefone());
        tvSite.setText(cadastro.getSite());

        return itemView;
    }
}
