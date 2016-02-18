package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import modelo.Gastos;

/**
 * Created by jmio on 17/2/2016.
 */
public class GastosDAO {
    private DBHelper helper;
    private SQLiteDatabase database;
    public GastosDAO(Context context){helper = new DBHelper(context);}
    private SQLiteDatabase getDatabase(){
        if(database == null){
            database = helper.getWritableDatabase();
        }
        return database;
    }

    private Gastos crearGastos(Cursor cursor){
        Gastos gastos = new Gastos(
                cursor.getInt(cursor.getColumnIndex(DBHelper.Gastos._IDGAST)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Gastos.NOMBREGAST)),
                cursor.getDouble(cursor.getColumnIndex(DBHelper.Gastos.VALORGAST)),
                cursor.getString(cursor.getColumnIndex(DBHelper.Gastos.FECHAGAST))
        );
        return gastos;
    };
    public List<Gastos> listarGastos(){
        Cursor cursor = getDatabase().query(DBHelper.Gastos.TABLEGAST,DBHelper.Gastos.COLUMNASGAST, null, null, null, null, null);
        List<Gastos> lista = new ArrayList<Gastos>();
        while(cursor.moveToNext()){
            Gastos modeloGast = crearGastos(cursor);
            lista.add(modeloGast);
        }
        return lista;
    }
    public long modificarGastos(Gastos gasto){
        ContentValues valuesGast = new ContentValues();
        valuesGast.put(DBHelper.Gastos.NOMBREGAST, gasto.getNombreGast());
        valuesGast.put(DBHelper.Gastos.VALORGAST, gasto.getValorGast());
        valuesGast.put(DBHelper.Gastos.FECHAGAST, gasto.getFechaGast());
        if(gasto.get_idGast() != null){
            return database.update(DBHelper.Gastos.TABLEGAST, valuesGast,
                    "_idGast = ?", new String[]{gasto.get_idGast().toString()});
        }
        return getDatabase().insert(DBHelper.Gastos.TABLEGAST,null,valuesGast);
    }
    public boolean eliminarGastos(int idGast){
        return getDatabase().delete(DBHelper.Gastos.TABLEGAST,"_idGast = ?", new String[]{Integer.toString(idGast)}) > 0;
    }
    public Gastos buscarGastoPorID(int idGast){
        Cursor cursor = getDatabase().query(DBHelper.Gastos.TABLEGAST,
                DBHelper.Gastos.COLUMNASGAST, "_idGast = ?", new String[]{ Integer.toString(idGast)}, null, null, null);
        if(cursor.moveToNext()){
            Gastos modelBgast = crearGastos(cursor);
            cursor.close();
            return modelBgast;
        }
        return null;
    }
    public void cerrarDB(){
        helper.close();
        database = null;
    }
}
