package com.example.gorent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<DataAdapter> ListOfDataAdapter;

    RecyclerView recyclerView;    String URL_JSON="http://10.10.4.147/go-rent/app/testapi/json.php";

    String TAG_ID ="id_unit";
    String TAG_URL="gambar";
    String TAG_NAMA ="nama";
    String TAG_JENIS="jeniskendaraan";
    String TAG_HARGA="hargasewa";

    JsonArrayRequest RequestOfJsonArray;
    RequestQueue requestQueue;

    View view;
    int RecycleViewItemPosition;

    RecyclerView.LayoutManager layoutManagerOfrecyclerView;
    RecyclerView.Adapter recyclerViewadapter;
    ArrayList<String> ImageTittleidArrayListForClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageTittleidArrayListForClick = new ArrayList<>();
        ListOfDataAdapter = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_layout);
        recyclerView.setHasFixedSize(true);
        layoutManagerOfrecyclerView = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);

        ImageButton btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, dashboard.class);
                startActivity(intent);
            }
        });

        JSON_HTTP();

    }

    public void JSON_HTTP() {
        RequestOfJsonArray = new JsonArrayRequest(URL_JSON,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ParseJsonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(RequestOfJsonArray);
    }
    public void ParseJsonResponse(JSONArray array){
        for (int i=0; i<array.length(); i++){
            DataAdapter GetDataAdapter2 = new DataAdapter();
            JSONObject json=null;
            try {
                json = array.getJSONObject(i);
                ImageTittleidArrayListForClick.add(json.getString(TAG_ID));
                GetDataAdapter2.setid(json.getString(TAG_ID));
                GetDataAdapter2.setnama(json.getString(TAG_NAMA));
                GetDataAdapter2.seturl(json.getString(TAG_URL));
                GetDataAdapter2.setharga("Rp. " + json.getString(TAG_HARGA));
                GetDataAdapter2.setjeniskendaraan(json.getString(TAG_JENIS));
            } catch (JSONException e){
                e.printStackTrace();
            }
            ListOfDataAdapter.add(GetDataAdapter2);
        }
        recyclerViewadapter = new RecyclerViewAdapter(ListOfDataAdapter, this);
        recyclerView.setAdapter(recyclerViewadapter);
    }

}
