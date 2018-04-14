package com.example.usuario.s14practica;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DBActivity extends AppCompatActivity {
    SQLiteDatabase db;

    EditText us,pass;
    Button button;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        db=openOrCreateDatabase(Constantes.BD,MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS "+Constantes.TABLA_US+"(_id INTEGER PRIMARY KEY AUTOINCREMENT, Usuario VARCHAR NOT NULL, password VARCHAR NOT NULL);");

        us=findViewById(R.id.editTextUS);
        pass=findViewById(R.id.editTextPass);
        tv=findViewById(R.id.textView2);
        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertar();
                listar();
            }
        });
        listar();
    }

    private void insertar(){
        String sql="INSERT INTO "+Constantes.TABLA_US + " VALUES (NULL,'"+us.getText().toString()+"','"+pass.getText().toString()+"');";
        Log.i(Constantes.TABLA_US,sql);
        db.execSQL(sql);
        us.setText("");
        pass.setText("");
    }

    private void listar(){
        String sql="SELECT * FROM "+ Constantes.TABLA_US;
        Cursor rs=db.rawQuery(sql,null);
        tv.setText("");
        if (rs.moveToFirst()){
            do {
                tv.append("ID "+rs.getInt(0)+" US: "+rs.getString(1)+" pass: "+rs.getString(2)+"\n");
            }while (rs.moveToNext());
        }


    }
}
