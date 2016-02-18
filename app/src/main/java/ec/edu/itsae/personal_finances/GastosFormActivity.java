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

import java.util.Calendar;

import dao.GastosDAO;
import modelo.Gastos;
import util.Mensajes;

public class GastosFormActivity extends AppCompatActivity {
    private EditText edtfecha, edtnombre, edtvalor;
    private Button btnfecha;
    private int año, mes, dia, idgast;
    private static final int TIPO_DIALOGO = 0;
    private static DatePickerDialog.OnDateSetListener oyenteSelectorFecha;
    private GastosDAO gastosDAO;
    private Gastos gastos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos_form);

        edtfecha = (EditText) findViewById(R.id.edtfgast);
        edtnombre = (EditText) findViewById(R.id.edtngast);
        edtvalor = (EditText) findViewById(R.id.edtvgast);
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

        idgast = getIntent().getIntExtra("GASTO_ID",0);
        if(idgast > 0){
            Gastos modelgast = gastosDAO.buscarGastoPorID(idgast);
            edtnombre.setText(""+modelgast.getNombreGast());
            edtvalor.setText(""+modelgast.getValorGast());
            edtfecha.setText(""+modelgast.getFechaGast());
            setTitle("Modificar Gastos");
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
    public void goMain (View v){
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);

    }

    @Override
    protected void onDestroy() {
        gastosDAO.cerrarDB();
        super.onDestroy();
    }

    private void registrar() {
        boolean validar = true;
        String nombre = edtnombre.getText().toString();
        double valor = Double.parseDouble(edtvalor.getText().toString());
        String fecha = edtfecha.getText().toString();
        /*if (prod == null || prod.equals("")) {
            validar = false;
            edtProducto.setError(getString(R.string.Producto_validaProducto));
        }
        if (prec !=0) {
            validar = false;
            edtProducto.setError(getString(R.string.Producto_validaPrecio));
        }
        if (canti !=0) {
            validar = false;
            edtProducto.setError(getString(R.string.Producto_validaCantidad));
        }*/

        if(validar){
            gastos = new Gastos();
            gastos.setNombreGast(nombre);
            gastos.setValorGast(valor);
            gastos.setFechaGast(fecha);
            if(idgast > 0){
                gastos.set_idGast(idgast);
            }
            long resultado = gastosDAO.modificarGastos(gastos);
            if(resultado != -1){
                if(idgast > 0) {
                    Mensajes.Msg(this, getString(R.string.msg_ing_modificado));
                }else{
                    Mensajes.Msg(this, getString(R.string.msg_ing_guardado));
                }
                finish();
                startActivity(new Intent(this, GastosListActivity.class));
            }else{
                Mensajes.Msg(this, getString(R.string.msg_ing_eliminado));
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gastos_form_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_menu_guardar:
                this.registrar();
                break;

        }

        return super.onOptionsItemSelected(item);
    }


}
