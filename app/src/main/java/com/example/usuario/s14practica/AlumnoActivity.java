package com.example.usuario.s14practica;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlumnoActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etNombre,etCarrera,etCurp;
    private Button bUpdate,bDelete,bInsert;
    private long id;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumno);

        etNombre=findViewById(R.id.editTextNombre);
        etCarrera=findViewById(R.id.editTextCarrera);
        etCurp=findViewById(R.id.editTextCurp);
        bUpdate=findViewById(R.id.buttonUp);
        bDelete=findViewById(R.id.buttonDel);
        bInsert=findViewById(R.id.buttonIn);

        dbManager=new DBManager(this);
        dbManager.open();

        Intent i=getIntent();
        setTitle(i.getStringExtra(Constantes.ACCION)+" Alumno ");
        try {
            id=Long.parseLong(i.getStringExtra("id"));
        }catch (Exception e){
            e.printStackTrace();
        }

        if (i.getStringExtra(Constantes.ACCION).equals(Constantes.EDITAR)){
            Cursor c=dbManager.select();
            i.getStringExtra("id");
            etNombre.setText(c.getString(c.getColumnIndex(Constantes.NOMBRE)));
            etCarrera.setText(c.getString(c.getColumnIndex(Constantes.CARRERA)));
            etCurp.setText(c.getString(c.getColumnIndex(Constantes.CURP)));
        }
        bInsert.setOnClickListener(this);
        bDelete.setOnClickListener(this);
        bUpdate.setOnClickListener(this);
        if (i.getStringExtra(Constantes.ACCION).equals(Constantes.INSERTAR)){
            bUpdate.setEnabled(false);
            bDelete.setEnabled(false);
            bInsert.setEnabled(false);
        }else if (i.getStringExtra(Constantes.ACCION).equals(Constantes.EDITAR)){
            bUpdate.setEnabled(true);
            bDelete.setEnabled(true);
            bInsert.setEnabled(true);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonIn:
                dbManager.insert(etNombre.getText().toString(),etCarrera.getText().toString(),etCurp.getText().toString());
                returnHome();
                break;

            case R.id.buttonUp:
                dbManager.update(id,etNombre.getText().toString(),etCarrera.getText().toString(),etCurp.getText().toString());
                returnHome();
                break;

            case R.id.buttonDel:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("Seguro de Eliminar al Alumno\n Nombre: "+etNombre.getText().toString()+"?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbManager.delete(id);
                        Toast.makeText(getApplicationContext(), "Registro Borrado ", Toast.LENGTH_SHORT).show();
                        returnHome();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog d= builder.create();
                d.setTitle("Confirmacion");
                d.show();
                break;

            default:
        }
    }

    public void returnHome(){
        Intent i=new Intent(getApplicationContext(),MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
