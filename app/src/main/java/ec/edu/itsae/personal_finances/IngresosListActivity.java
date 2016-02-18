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
import dao.DBHelper;
import adapter.IngresosAdapter;
import dao.IngresosDAO;
import modelo.Ingresos;
import util.Mensajes;

public class IngresosListActivity extends ActionBarActivity implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener {
    private ListView lista1;
    private ListView lista2;
    private ListView lista3;
    private List<Ingresos> listaIng;
    private IngresosAdapter adapter;
    private IngresosDAO ingresosDAO;
    private int idok;
    private AlertDialog alertDialog, alertconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresos_list);

        alertDialog = Mensajes.crearAlertaDialog(this);
        alertconfirm = Mensajes.crearDialogConfirmacion(this);

        ingresosDAO = new IngresosDAO(this);
        listaIng = ingresosDAO.listarIngresos();
        adapter = new IngresosAdapter(this,listaIng);

        lista1 = (ListView) findViewById(R.id.lvlistaing);
        lista1.setAdapter(adapter);
        lista1.setOnItemClickListener(this);




    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_usuarios, menu);
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
            startActivity(new Intent(this, UsuarioActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }*/

   @Override
    public void onClick(DialogInterface dialog, int which) {
        int id = listaIng.get(idok).get_idIng();
        switch (which){
            case 0:
                Intent intent = new Intent(this, IngresosListActivity.class);
                intent.putExtra("INGRESOS_ID",id);
                startActivity(intent);
                break;
            case 1:alertconfirm.show();
                break;
            case DialogInterface.BUTTON_POSITIVE:
                listaIng.remove(idok);
                ingresosDAO.eliminarIngresos(id);
                lista1.invalidateViews();
                lista2.invalidateViews();
                lista3.invalidateViews();
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
