package com.example.a001;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class db extends SQLiteOpenHelper{
    private static final String NOMBRE_Db = "test.db";
    private static final int VERSION_DB = 1;
    private static final String TABLA_TRBAJADORES =
            "CREATE TABLE TRABAJADORES(CODIGO TEXT PRIMARY KEY, NOMBRE TEXT, EMPLEO TEXT)";
    private static final String TURNOS_TRBAJADORES =
            "CREATE TABLE TURNOS(id INTEGER PRIMARY KEY AUTOINCREMENT, CODIGO TEXT, TIPO TEXT, FECHA_I TEXT, FECHA_F TEXT, " +
                    "FOREIGN KEY (CODIGO) REFERENCES TRABAJADORES(CODIGO))";

    public db(Context context){
        super(context, NOMBRE_Db, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_TRBAJADORES);
        sqLiteDatabase.execSQL(TURNOS_TRBAJADORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CREATE"+TABLA_TRBAJADORES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CREATE"+TURNOS_TRBAJADORES);
        sqLiteDatabase.execSQL(TABLA_TRBAJADORES);
        sqLiteDatabase.execSQL(TURNOS_TRBAJADORES);
        Log.d("OK", "Actualizando tablas");
    }

    public void agregarTrabajador(String codigo, String name, String empleo){
        SQLiteDatabase bd =getWritableDatabase();
        if(bd!=null){
            bd.execSQL("INSERT INTO TRABAJADORES VALUES('"+codigo+"', '"+name+"', '"+empleo+"' )");
            bd.close();
        }
    }

    public List<String> consultarTrabajador(String codigo, Context context){
        List<String> datos = new ArrayList<>();
        SQLiteDatabase bd = getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM TRABAJADORES WHERE codigo=?", new String[]{codigo});
        if(cursor != null && cursor.moveToFirst()){
            int n = cursor.getColumnIndex("NOMBRE");
            int n2 = cursor.getColumnIndex("EMPLEO");
            String name = cursor.getString(n);
            String empleo = cursor.getString(n2);

            datos.add(name);
            datos.add(empleo);

            cursor.close();
        } else {
            Log.d("TAG", "No se encontró ningún trabajador con código " + codigo);
            Toast.makeText(context, "No se encontraron registros", Toast.LENGTH_LONG).show();
        }
        bd.close();
        return datos;

    }

    public void agregarTurno(String codigo, String tipo, String fecha_i, String fecha_f){
        SQLiteDatabase bd = getWritableDatabase();
        if(bd!=null){
            //bd.execSQL("INSERT INTO TURNOS VALUES('"+codigo+"', '"+tipo+"', '"+fecha_i+"', '"+fecha_f+"' )");
            bd.execSQL("INSERT INTO TURNOS(CODIGO, TIPO, FECHA_I, FECHA_F) VALUES('"+codigo+"', '"+tipo+"', '"+fecha_i+"', '"+fecha_f+"' )");
            bd.close();
        }
    }

    public List<turnos> consultarTurnos(String codigo){
        List<turnos> listDatos = new ArrayList<>();

        SQLiteDatabase bd = getReadableDatabase();
        String query = "SELECT * FROM TURNOS " +
                "JOIN TRABAJADORES ON TURNOS.CODIGO = TRABAJADORES.CODIGO " +
                "WHERE TURNOS.CODIGO = ?";
        Cursor cursor = bd.rawQuery(query, new String[] { codigo });

        while (cursor.moveToNext()) {

            int n = cursor.getColumnIndex("FECHA_I");
            int n2 = cursor.getColumnIndex("FECHA_F");
            String fechaInicio = cursor.getString(n);
            String fechaFin = cursor.getString(n2);

            turnos datos = new turnos(fechaInicio, fechaFin);
            listDatos.add(datos);
        }
    return listDatos;
    }
}
