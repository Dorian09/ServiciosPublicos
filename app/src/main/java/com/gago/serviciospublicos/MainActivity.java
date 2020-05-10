package com.gago.serviciospublicos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.gago.serviciospublicos.BaseDato.DBControlador;
import com.gago.serviciospublicos.Modelos.Servicio;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DBControlador controlador;

    private Spinner spinner1;
    private EditText et1;
    private EditText et2;
    private TextView tv1;
    private Button btn1;
    private Button btn2;
    int x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        et1 = (EditText)findViewById(R.id.id_Medida);
        et2 = (EditText)findViewById(R.id.id_Direccion);
        tv1 = (TextView)findViewById(R.id.id_Unidad);
        btn1 = (Button)findViewById(R.id.id_Guardar);
        btn2 = (Button)findViewById(R.id.id_Cancelar);
        spinner1 =(Spinner)findViewById(R.id.id_Spinner_Servicio);

        String[] opciones = {"Agua", "Luz","Gas"};

        controlador = new DBControlador(getApplicationContext());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_lol, opciones);
        spinner1.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                x = position;
                switch (x) {
                    case 0:
                        tv1.setText("m^3");
                        break;
                    case 1:
                        tv1.setText("kw/h");
                        break;
                    case 2:
                        tv1.setText("CC");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_Guardar:
                Calendar calendar = Calendar.getInstance();
                try {
                    int medicion = et1.getText().toString().isEmpty() ? 0 : Integer.parseInt(et1.getText().toString());
                    Servicio servicio = new Servicio(tv1.getText().toString(), calendar, medicion, x);
                    long retorno = controlador.agregarRegistro(servicio);
                    if (retorno != -1) {
                        Toast.makeText(v.getContext(), "Registo exitoso", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(v.getContext(), "Error en el registro", Toast.LENGTH_SHORT).show();
                    }
                    limpiarCampo();
                } catch (NumberFormatException numEx) {
                    Toast.makeText(getApplicationContext(), "El numero es muy grande", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_Cancelar:
                limpiarCampo();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_listado) {
            Intent i = new Intent(this, ListadoActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void limpiarCampo() {
        et1.setText("");
        et2.setText("");
    }

}
