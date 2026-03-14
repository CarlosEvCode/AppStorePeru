package com.example.appstoreperu;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.example.appstoreperu.entidades.Marca;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Registrar extends AppCompatActivity {

    Spinner spMarca;
    EditText edtDescripcion, edtPrecio, edtStock, edtGarantia;
    Button btnRegistrarProducto;

    // Volley
    RequestQueue requestQueue;

    private final String URL_MARCAS = "http://192.168.18.61:3000/marcas";

    private void loadUI() {
        spMarca = findViewById(R.id.spMarca);
        edtDescripcion = findViewById(R.id.edtDescripcion);
        edtPrecio = findViewById(R.id.edtPrecio);
        edtStock = findViewById(R.id.edtStock);
        edtGarantia = findViewById(R.id.edtGarantia);
        btnRegistrarProducto = findViewById(R.id.btnRegistrarProducto);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadUI();
        obtenerMarcas(); // Cargar el Spinner al entrar en la pantalla
    }

    // Obtener las marcas desde la API
    private void obtenerMarcas() {
        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL_MARCAS,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        llenarSpinner(jsonArray);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("ErrorWS_Marcas", volleyError.toString());
                        Toast.makeText(getApplicationContext(), "No se pudieron cargar las marcas", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    // Llenar el spinner con las marcas
    private void llenarSpinner(JSONArray jsonMarcas) {
        try {
            ArrayList<Marca> listaMarcas = new ArrayList<>();

            for (int i = 0; i < jsonMarcas.length(); i++) {
                JSONObject jsonObject = jsonMarcas.getJSONObject(i);

                // Crear el objeto Marca con el id y el nombre
                Marca marcaObj = new Marca(
                        jsonObject.getInt("id"),
                        jsonObject.getString("nombreM"));
                listaMarcas.add(marcaObj);
            }

            // Pasar objetos al adaptador... El Spinner usará toString() para mostra nombres en la vista
            ArrayAdapter<Marca> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaMarcas);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spMarca.setAdapter(adapter);

        } catch (Exception e) {
            Log.e("ErrorJSON_Marcas", e.toString());
        }
    }
}