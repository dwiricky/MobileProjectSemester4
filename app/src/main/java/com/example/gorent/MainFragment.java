package com.example.gorent;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.gorent.DataAdapter;
import com.example.gorent.R;
import com.example.gorent.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private List<DataAdapter> ListOfDataAdapter;

    private RecyclerView recyclerView;
    private String URL_JSON = "http://10.10.179.223/go-rent/app/testapi/json.php";

    private String TAG_ID = "id_unit";
    private String TAG_URL = "gambar";
    private String TAG_NAMA = "nama";
    private String TAG_JENIS = "jeniskendaraan";
    private String TAG_HARGA = "hargasewa";

    private JsonArrayRequest RequestOfJsonArray;
    private RequestQueue requestQueue;

    private View view;
    private int RecycleViewItemPosition;

    private RecyclerView.LayoutManager layoutManagerOfrecyclerView;
    private RecyclerView.Adapter recyclerViewadapter;
    private ArrayList<String> ImageTittleidArrayListForClick;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);

        ImageTittleidArrayListForClick = new ArrayList<>();
        ListOfDataAdapter = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerview_layout);
        recyclerView.setHasFixedSize(true);
        layoutManagerOfrecyclerView = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);


        JSON_HTTP();

        return view;
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

        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(RequestOfJsonArray);
    }

    public void ParseJsonResponse(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            DataAdapter GetDataAdapter2 = new DataAdapter();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                ImageTittleidArrayListForClick.add(json.getString(TAG_ID));
                GetDataAdapter2.setid(json.getString(TAG_ID));
                GetDataAdapter2.setnama(json.getString(TAG_NAMA));
                GetDataAdapter2.seturl(json.getString(TAG_URL));
                GetDataAdapter2.setharga("Rp. " + json.getString(TAG_HARGA));
                GetDataAdapter2.setjeniskendaraan(json.getString(TAG_JENIS));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ListOfDataAdapter.add(GetDataAdapter2);
        }
        recyclerViewadapter = new RecyclerViewAdapter(ListOfDataAdapter, getActivity());
        recyclerView.setAdapter(recyclerViewadapter);
    }
}
