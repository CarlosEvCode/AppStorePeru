package com.example.appstoreperu;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Listar extends AppCompatActivity {

    ListView lstProductos;
    RequestQueue requestQueue;

    private final String URL = "http://192.168.18.61:3000/productos";

    private void loadUI() {
        lstProductos = findViewById(R.id.lstProductos);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loadUI();
        obtenerDatos();
    }

    // Importar objetos JSON desde WS (API)
    private void obtenerDatos() {
        requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        renderizarListView(jsonArray);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("ErrorWS", volleyError.toString());
                        Toast.makeText(getApplicationContext(), "No se obtuvieron los datos", Toast.LENGTH_SHORT)
                                .show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    // Llenar ListView con los JSON parseados
    private void renderizarListView(JSONArray jsonProductos) {
        try {
            ArrayAdapter adapter;
            ArrayList<String> listaProductos = new ArrayList<>();

            for (int i = 0; i < jsonProductos.length(); i++) {
                JSONObject jsonObject = jsonProductos.getJSONObject(i);

                // Gracias al INNER JOIN que hicimos en backend, podemos leer nombre_marca
                String fila = jsonObject.getString("descripcion") + " - "
                        + jsonObject.getString("nombre_marca")
                        + " (S/ " + jsonObject.getString("precio") + ")";

                listaProductos.add(fila);
            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaProductos);
            lstProductos.setAdapter(adapter);

        } catch (Exception e) {
            Log.e("ErrorJSON", e.toString());
        }
    }
}