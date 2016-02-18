package ec.edu.itsae.personal_finances;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import dao.IngresosDAO;
import modelo.Ingresos;
import util.Mensajes;

public class IngresosFormActivity extends AppCompatActivity {
    private EditText edtfecha, edtNombre, edtValor;
    private Button btnfecha;
    private int año, mes, dia;
    private static final int TIPO_DIALOGO = 0;
    private static DatePickerDialog.OnDateSetListener oyenteSelectorFecha;
    private IngresosDAO ingresosDAO;
    private Ingresos ingresos;
    private int iding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresos_form);

        edtfecha = (EditText) findViewById(R.id.edtfing);
        btnfecha = (Button) findViewById(R.id.btncalendar);
        Calendar calendario = Calendar.getInstance(); //obtengo la fecha actual dia mes y año
        año = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);
        mostrarFecha();
        oyenteSelectorFecha = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                año = year;
                mes = monthOfYear;
                dia = dayOfMonth;
                mostrarFecha();
            }
        }; //se selecciona de acuerdo a lo que marcamos

        edtNombre = (EditText) findViewById(R.id.edtning);
        edtValor = (EditText) findViewById(R.id.edtving);

        iding = getIntent().getIntExtra("INGRESOS_ID", 0);
        if(iding > 0) {
            Ingresos model = ingresosDAO.buscarIngresoPorID(iding);
            edtNombre.setText(""+model.getNombreIng());
            edtValor.setText(""+model.getValorIng());
            edtfecha.setText(""+model.getFechaIng());
            setTitle("Modificar Ingresos");

        }

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id){
            case 0://AL SER TIPO_DIALOGO 0 se realiza esto
                return new DatePickerDialog(this, oyenteSelectorFecha, año, mes, dia);

        }
        return null;
    }

    //metodo para  que el boton muestre el dialogo
    public void mostrarCalendario(View control){
        showDialog(TIPO_DIALOGO);//se ejecuta el dialogo switch
    }

    public void mostrarFecha (){
        edtfecha.setText(año + "/" + (mes + 1) + "/" + dia);//ubico la fecha en el editext

    }
    @Override
    protected void onDestroy(){
        ingresosDAO.cerrarDB();
        super.onDestroy();
    }

    private void registraring(){
        boolean validar = true;
        String nombre = edtNombre.getText().toString();
        double valor = Double.parseDouble(edtValor.getText().toString());
        String fecha = edtfecha.getText().toString();
        if(nombre == null || nombre.equals("")){
            validar = false;
            edtNombre.setError(getString(R.string.Ingreso_validaNombre));
        }
        /*if(valor == null || valor.equals("")){
            validar = false;
            edtValor.setError(getString(R.string.Ingreso_validaValor));
        }*/
        if(fecha == null || fecha.equals("")){
            validar = false;
            edtfecha.setError(getString(R.string.Ingreso_validaFecha));
        }
        try {
            if (validar) {
                ingresos = new Ingresos();
                ingresos.setNombreIng(nombre);
                ingresos.setValorIng(valor);
                ingresos.setFechaIng(fecha);
                if (iding > 0) {
                    ingresos.set_idIng(iding);
                }
                long resultado = ingresosDAO.modificarIngresos(ingresos);
                if (resultado != -1) {
                    if (iding > 0) {
                        Mensajes.Msg(this, getString(R.string.msg_ing_modificado));
                    } else {
                        Mensajes.Msg(this, getString(R.string.msg_ing_guardado));
                    }
                    finish();
                    startActivity(new Intent(this, IngresosListActivity.class));
                } else {
                    Mensajes.Msg(this, getString(R.string.msg_ing_error));
                }
            }
        } catch (Exception e){
            Toast.makeText(this, "ingresos activity"+e, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ingresos_form_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_menu_guardar:
                this.registraring();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
    public void goMain (View v){
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);

    }


}
