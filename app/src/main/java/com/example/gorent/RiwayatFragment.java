package com.example.gorent;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class RiwayatFragment extends Fragment {

    private List<RiwayatAdapter> pesananList;
    private RecyclerView recyclerView;

    private RiwayatViewAdapter recyclerViewAdapter;

    private String URL_JSON;

    private String TAG_USER_ID = "id_user";

    private String TAG_ID = "id_pemesanan";
    private String TAG_PLAT = "platnomor";
    private String TAG_TGLPESAN = "tanggal_pemesanan";
    private String TAG_TGLKMBL = "tanggal_pengembalian";
    private String TAG_HARI = "jumlah_hari";
    private String TAG_HARGA = "hargatotal";
    private String TAG_DENDA = "denda";
    private String TAG_STATUS = "status";

    private View view;
    private JsonArrayRequest RequestOfJsonArray;
    private RequestQueue requestQueue;
    private int RecycleViewItemPosition;

    private RecyclerView.LayoutManager layoutManagerOfrecyclerView;

    private RecyclerView.Adapter recyclerViewadapter;

    private ArrayList<String> ImageTittleidArrayListForClick;

    private String id_user;


    public RiwayatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_riwayat, container, false);

        pesananList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerview_layout);
        recyclerView.setHasFixedSize(true);
        layoutManagerOfrecyclerView = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);

        // Retrieve id_user from SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        id_user = sharedPreferences.getString("id_user", "");

        // Update URL_JSON with id_user value
        URL_JSON = "http://10.10.179.223/go-rent/app/testapi/riwayat.php?user_id=" + id_user;

        // Method untuk mengambil data pesanan dari API dan memperbarui RecyclerView
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
            RiwayatAdapter GetDataAdapter2 = new RiwayatAdapter();
            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                String idPemesanan = json.getString(TAG_ID);
                String idUser = json.getString(TAG_USER_ID);

                if (idUser.equals(id_user)) {
                    GetDataAdapter2.setIdPemesanan(idPemesanan);
                    GetDataAdapter2.setPlatnomor(json.getString(TAG_PLAT));
                    GetDataAdapter2.setTanggalPemesanan(json.getString(TAG_TGLPESAN));
                    GetDataAdapter2.setJumlahHari(Integer.parseInt(json.getString(TAG_HARI)));
                    String hargaString = json.getString(TAG_HARGA).replace("Rp. ", "");
                    int harga = Integer.parseInt(hargaString);
                    GetDataAdapter2.setHargaTotal(harga);

                    GetDataAdapter2.setTanggalPengembalian(json.getString(TAG_TGLKMBL));
                    GetDataAdapter2.setDenda(Integer.parseInt(json.getString(TAG_DENDA)));
                    GetDataAdapter2.setStatus(json.getString(TAG_STATUS));

                    pesananList.add(GetDataAdapter2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        recyclerViewadapter = new RiwayatViewAdapter(pesananList, getActivity());
        recyclerView.setAdapter(recyclerViewadapter);
    }


}
