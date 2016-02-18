package ec.edu.itsae.personal_finances;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import adapter.GastosAdapter;
import dao.GastosDAO;
import modelo.Gastos;
import util.Mensajes;

public class GastosListActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener{
    private ListView lista1;
    private List<Gastos> listaList;
    private GastosAdapter adapter;
    private GastosDAO gastosDAO;
    private int idok;
    private AlertDialog alertDialog, alertconfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_list);

        alertDialog = Mensajes.crearAlertaDialog(this);
        alertconfirm = Mensajes.crearDialogConfirmacion(this);

        gastosDAO = new GastosDAO(this);
        listaList = gastosDAO.listarGastos();
        adapter = new GastosAdapter(this,listaList);

        lista1 = (ListView) findViewById(R.id.lvlistagast);
        lista1.setAdapter(adapter);
        lista1.setOnItemClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_gastos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu_guardar) {
            startActivity(new Intent(this, GastosFormActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        int id = listaList.get(idok).get_idGast();
        switch (which){
            case 0:
                Intent intent = new Intent(this, GastosFormActivity.class);
                intent.putExtra("GASTO_ID",id);
                startActivity(intent);
                break;
            case 1:alertconfirm.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                listaList.remove(idok);
                gastosDAO.eliminarGastos(id);
                lista1.invalidateViews();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                alertconfirm.dismiss();break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        idok = position;
        alertDialog.show();
    }
}
