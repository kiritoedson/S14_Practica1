package com.example.usuario.s14practica;

/**
 * Created by usuario on 14/04/2018.
 */

public class Constantes {
    public static final String BD = "COLEGIO";

    public static final String TABLA_US = "USUARIO";

    public static final String TABLA_ALU = "Alumno";
    //columnas
    public static final String ID = "_id";
    public static final String NOMBRE = "nombre";
    public static final String CARRERA = "carrera";
    public static final String CURP = "curp";

    public static final String CREATE_TABLE_ALU = "CREATE TABLE " + TABLA_ALU + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOMBRE + " TEXT NOT NULL, " + CARRERA + " VARCHAR, " + CURP + " VARCHAR );";

    public static final int DB_VERSION = 1;

    public static final String ACCION = "accion";
    public static final String EDITAR = "editar";
    public static final String INSERTAR = "insertar";
    public static final String ELIMINAR = "eliminar";
}
