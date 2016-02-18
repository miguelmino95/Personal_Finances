package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import ec.edu.itsae.personal_finances.R;

import java.util.List;

import modelo.Gastos;

/**
 * Created by jmio on 17/2/2016.
 */
public class GastosAdapter extends BaseAdapter {
    private Context context;
    private List<Gastos> lista;


    public GastosAdapter(Context context, List<Gastos> model){
        this.context = context;
        this.lista = model;
    }
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Gastos gasto = lista.get(position);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.gastos, null);
        }
        TextView txtNom = (TextView) view.findViewById(R.id.gasto_lista_nombre);
        txtNom.setText(gasto.getNombreGast());

        /*TextView txtVal = (TextView) view.findViewById(R.id.gasto_lista_valor);
        txtNom.setText(gasto.getValorGast());*/

        TextView txtFech = (TextView) view.findViewById(R.id.gasto_lista_fecha);
        txtNom.setText(gasto.getFechaGast());
        return view;
    }
}
