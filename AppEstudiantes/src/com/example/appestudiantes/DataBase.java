package com.example.appestudiantes;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper  {

	public DataBase(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table alumnos(carnet integer primary key, nombre text, materia text, codigo integer)");
	}
	
	public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
		db.execSQL("drop table if exists alumnos");
		db.execSQL("create table alumnos(carnet integer primary key, nombre text, materia text, codigo integer)");		
	}	

}
