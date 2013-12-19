package com.example.appestudiantes;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
private EditText EditTex1,EditTex2,EditTex3,EditTex4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		EditTex1= (EditText) findViewById(R.id.editText1);
		EditTex2= (EditText) findViewById(R.id.editText2);
		EditTex3= (EditText) findViewById(R.id.editText3);
		EditTex4 = (EditText) findViewById(R.id.editText4);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void Guardar(View v){
		DataBase admin = new DataBase(this,
				"administracion", null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String carnet = EditTex1.getText().toString();
		String nombre = EditTex2.getText().toString();
		String materia = EditTex3.getText().toString();
		String codigo = EditTex4.getText().toString();
		ContentValues registro = new ContentValues();
		registro.put("carnet", carnet);
		registro.put("nombre", nombre);
		registro.put("materia", materia);
		registro.put("codigo", codigo);
		bd.insert("alumnos", null, registro);
		bd.close();
		EditTex1.setText("");
		EditTex2.setText("");
		EditTex3.setText("");
		EditTex4.setText("");
		Toast.makeText(this, "Se cargaron los datos del Alumno",
				Toast.LENGTH_SHORT).show();
		
		
		
		
	}
	public void Consulta(View v) {
		DataBase admin = new DataBase(this,
				"administracion", null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String carnet = EditTex1.getText().toString();
		Cursor fila = bd.rawQuery(
				"select nombre,materia,codigo  from alumnos where carnet=" + carnet
						+ "", null);
		if (fila.moveToFirst()) {
			EditTex2.setText(fila.getString(0));
			EditTex3.setText(fila.getString(1));
			EditTex4.setText(fila.getString(2));
		} else
			Toast.makeText(this, "No existe un estudiante con dicho carnet",
					Toast.LENGTH_SHORT).show();
		bd.close();

	}
	public void Borrar(View v) {
		DataBase admin = new DataBase(this,
				"administracion", null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String carnet = EditTex1.getText().toString();
		int cant = bd.delete("alumnos", "carnet=" + carnet + "", null);
		bd.close();
		EditTex1.setText("");
		EditTex2.setText("");
		EditTex3.setText("");
		EditTex4.setText("");
		if (cant == 1)
			Toast.makeText(this, "Se elimino al estudiante con dicho Carnet",
					Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(this, "No existe un estudiante con ese Carnet",
					Toast.LENGTH_SHORT).show();
	}
	public void Edit(View v) {
		DataBase admin = new DataBase(this,
				"administracion", null, 1);
		SQLiteDatabase bd = admin.getWritableDatabase();
		String carnet = EditTex1.getText().toString();
		String nombre = EditTex2.getText().toString();
		String materia = EditTex3.getText().toString();
		String codigo = EditTex4.getText().toString();
		ContentValues registro = new ContentValues();
		registro.put("nombre", nombre);
		registro.put("materia", materia);
		registro.put("codigo", codigo);
		int cant = bd.update("alumnos", registro, "carnet=" + carnet, null);
		bd.close();
		if (cant == 1)
			Toast.makeText(this, "se modificaron los datos con exito", Toast.LENGTH_SHORT)
					.show();
		else
			Toast.makeText(this, "no existe una persona con ese carnet",
					Toast.LENGTH_SHORT).show();
	}


}
