package dao;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by jmio on 17/2/2016.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "DBeasymoney";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table ingresos(_idIng integer primary key autoincrement, "
                +"descripcionIng text no null, valorIng real no null, fechaIng text no null)");

        db.execSQL("create table gastos(_idGast integer primary key autoincrement, "
                +"descripcionGast text no null, valorGast real no null, fechaGast text no null)");

        db.execSQL("insert into ingresos(descripcionIng, valorIng, fechaIng) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into ingresos(descripcionIng, valorIng, fechaIng) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into ingresos(descripcionIng, valorIng, fechaIng) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into ingresos(descripcionIng, valorIng, fechaIng) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into ingresos(descripcionIng, valorIng, fechaIng) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into ingresos(descripcionIng, valorIng, fechaIng) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into ingresos(descripcionIng, valorIng, fechaIng) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into ingresos(descripcionIng, valorIng, fechaIng) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into ingresos(descripcionIng, valorIng, fechaIng) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into ingresos(descripcionIng, valorIng, fechaIng) values('Este es un ejemplo', 0.00, '2016/02/17')");

        db.execSQL("insert into gastos(descripcionGast, valorGast, fechaGast) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into gastos(descripcionGast, valorGast, fechaGast) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into gastos(descripcionGast, valorGast, fechaGast) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into gastos(descripcionGast, valorGast, fechaGast) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into gastos(descripcionGast, valorGast, fechaGast) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into gastos(descripcionGast, valorGast, fechaGast) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into gastos(descripcionGast, valorGast, fechaGast) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into gastos(descripcionGast, valorGast, fechaGast) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into gastos(descripcionGast, valorGast, fechaGast) values('Este es un ejemplo', 0.00, '2016/02/17')");
        db.execSQL("insert into gastos(descripcionGast, valorGast, fechaGast) values('Este es un ejemplo', 0.00, '2016/02/17')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public static class Ingresos {
        public static final String TABLEING = "ingresos";
        public static final String _IDING = "_idIng";
        public static final String NOMBREING = "descripcionIng";
        public static final String VALORING = "valorIng";
        public static final String FECHAING = "fechaIng";
        public static final String[] COLUMNASING = new String[]{_IDING, NOMBREING, VALORING, FECHAING};
    }

    public static class Gastos {
        public static final String TABLEGAST = "gastos";
        public static final String _IDGAST = "_idGast";
        public static final String NOMBREGAST = "descripcionGast";
        public static final String VALORGAST = "valorGast";
        public static final String FECHAGAST = "fechaGast";
        public static final String[] COLUMNASGAST = new String[]{_IDGAST, NOMBREGAST, VALORGAST, FECHAGAST};
    }


}
