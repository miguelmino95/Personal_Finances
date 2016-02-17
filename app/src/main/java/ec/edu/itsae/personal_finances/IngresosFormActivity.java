package ec.edu.itsae.personal_finances;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class IngresosFormActivity extends AppCompatActivity {
    private EditText edtfecha;
    private Button btnfecha;
    private int año, mes, dia;
    private static final int TIPO_DIALOGO = 0;
    private static DatePickerDialog.OnDateSetListener oyenteSelectorFecha;
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
        edtfecha.setText(año+"/"+(mes+1)+"/"+dia);//ubico la fecha en el editext

    }

}
