package br.ifpe.makeit.melhorprecogaranhuns.model.regranegocio;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.ifpe.makeit.melhorprecogaranhuns.R;
import br.ifpe.makeit.melhorprecogaranhuns.model.entidade.ArqJSON;

/**
 * Created by kaio on 19/07/2018.
 */

public class ArqJSONAdapter extends ArrayAdapter<ArqJSON> {
    private final Context context;
    private final ArrayList<ArqJSON> elementos;

    public ArqJSONAdapter(Context context, ArrayList<ArqJSON> elementos) {
        super(context, R.layout.row_list, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_list, parent, false);

        TextView descricao = (TextView) rowView.findViewById(R.id.descricao);
        TextView nomeFatasia = (TextView) rowView.findViewById(R.id.nome_fantasia);
        TextView horario = (TextView) rowView.findViewById(R.id.dia_hora);
        TextView preco = (TextView) rowView.findViewById(R.id.preco);

        descricao.setText(elementos.get(position).getDescricao());
        nomeFatasia.setText(elementos.get(position).getNomeFantasia());
        horario.setText(elementos.get(position).getDataEmissao());
        preco.setText(String.format("%.2f", elementos.get(position).getvUnit()));

        if(position%2 == 0) {
            rowView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            rowView.setBackgroundColor(Color.parseColor("#F5F5F5"));
        }

        return rowView;
    }

}
