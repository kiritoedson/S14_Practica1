package com.example.usuario.s14practica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by usuario on 14/04/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context) {
        super(context, Constantes.BD, null, Constantes.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(Constantes.BD, Constantes.CREATE_TABLE_ALU);
        db.execSQL(Constantes.CREATE_TABLE_ALU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constantes.TABLA_ALU);
        onCreate(db);
    }
}
