package com.gago.serviciospublicos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gago.serviciospublicos.BaseDato.DBControlador;
import com.gago.serviciospublicos.Modelos.Servicio;

import java.util.ArrayList;
import java.util.Calendar;

public class ModificarActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv1;
    private TextView tv2;
    private EditText et1;
    private EditText et2;
    private Spinner spinnerServicio;
    private Button btn1;
    private Button btn2;

    DBControlador controlador;

    int tipoDServicio;
    int indice;
    long id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        tv1 = findViewById(R.id.id_Titulo);
        tv2 = findViewById(R.id.id_Unidad);
        et1 = findViewById(R.id.id_Direccion);
        et2 = findViewById(R.id.id_Medida);
        spinnerServicio =(Spinner)findViewById(R.id.id_Spinner_Servicio);
        btn1 = findViewById(R.id.id_Guardar);
        btn2 = findViewById(R.id.id_Cancelar);

        tv1.setText(getString(R.string.modificar_registro));

        controlador = new DBControlador(getApplicationContext());

        //obtenemos el indice del registro y mostramos los datos en pantalla
        Intent i = getIntent();
        indice = i.getIntExtra("indice", 0);

        ArrayList<Servicio> lista = controlador.optenerRegistros();

        Servicio servicio = lista.get(indice);
        id = servicio.getId();

        et1.setText(servicio.getDireccion());
        et2.setText(Integer.toString(servicio.getMedida()));

        String[] opciones = {"Agua", "Luz","Gas"};

        controlador = new DBControlador(getApplicationContext());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_lol, opciones);
        spinnerServicio.setAdapter(adapter);

        spinnerServicio.setAdapter(adapter);
        spinnerServicio.setSelection(servicio.getTipoServicio());
        spinnerServicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipoDServicio = position;
                switch (tipoDServicio) {
                    case 0:
                        tv2.setText("m^3");
                        break;
                    case 1:
                        tv2.setText("kw/h");
                        break;
                    case 2:
                        tv2.setText("cc");
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
                    int medicion = et2.getText().toString().isEmpty() ? 0 : Integer.parseInt(et2.getText().toString());
                    Servicio servicio = new Servicio(id, et1.getText().toString(), calendar, medicion, tipoDServicio);
                    int retorno = controlador.actualizarRegistro(servicio);
                    if (retorno == 1) {
                        Toast.makeText(getApplicationContext(), "actualizacion exitosa", Toast.LENGTH_SHORT).show();
                        setResult(Activity.RESULT_OK);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "fallo en la actualizacion", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException nuEx) {
                    Toast.makeText(getApplicationContext(), "numero muy grande", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_Cancelar:
                setResult(Activity.RESULT_CANCELED);
                finish();
                break;
        }
    }
}
