package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Ingresos;


/**
 * Created by jmio on 17/2/2016.
 */
public class IngresosDAO {
    private DBHelper helper;
    private SQLiteDatabase database;
    public IngresosDAO(Context context){helper = new DBHelper(context);}
    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = helper.getWritableDatabase();
        }
        return database;
    }

    private Ingresos crearIngresos(Cursor cursor){
        Ingresos ingresos = new Ingresos(
                cursor.getInt(cursor.getColumnIndex(DBHelper.Ingresos._IDING)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Ingresos.NOMBREING)),
                cursor.getDouble(cursor.getColumnIndex(DBHelper.Ingresos.VALORING)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Ingresos.FECHAING))
        );
        return ingresos;
    };
    public List<Ingresos> listarIngresos(){
        Cursor cursor = getDatabase().query(DBHelper.Ingresos.TABLEING,DBHelper.Ingresos.COLUMNASING, null, null, null, null, null);
        List<Ingresos> lista = new ArrayList<Ingresos>();
        while(cursor.moveToNext()){
            Ingresos modeloIng = crearIngresos(cursor);
            lista.add(modeloIng);
        }
        return lista;
    }
    public long modificarIngresos(Ingresos ingreso){
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(ingreso.getFechaIng());*/
        ContentValues valuesIng = new ContentValues();
        valuesIng.put(DBHelper.Ingresos.NOMBREING, ingreso.getNombreIng());
        valuesIng.put(DBHelper.Ingresos.VALORING, ingreso.getValorIng());
        valuesIng.put(DBHelper.Ingresos.FECHAING, ingreso.getFechaIng());
        if(ingreso.get_idIng() != null){
            return database.update(DBHelper.Ingresos.TABLEING, valuesIng,
                    "_idIng = ?", new String[]{ingreso.get_idIng().toString()});
        }
            return getDatabase().insert(DBHelper.Ingresos.TABLEING, null, valuesIng);


    }
    public boolean eliminarIngresos(int idIng){
        return getDatabase().delete(DBHelper.Ingresos.TABLEING,"_idIng = ?", new String[]{Integer.toString(idIng)}) > 0;
    }
    public Ingresos buscarIngresoPorID(int idIng){
        Cursor cursor = getDatabase().query(DBHelper.Ingresos.TABLEING,
                DBHelper.Ingresos.COLUMNASING, "_idIng = ?", new String[]{ Integer.toString(idIng)}, null, null, null);
        if(cursor.moveToNext()){
            Ingresos modelBing = crearIngresos(cursor);
            cursor.close();
            return modelBing;
        }
        return null;
    }
    public void cerrarDB(){
        helper.close();
        database = null;
    }
}
