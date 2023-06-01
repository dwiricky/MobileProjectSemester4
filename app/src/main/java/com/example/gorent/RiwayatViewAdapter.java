package com.example.gorent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.NumberFormat;


public class RiwayatViewAdapter extends RecyclerView.Adapter<RiwayatViewAdapter.ViewHolder> {
    private Context context;
    private List<RiwayatAdapter> pesananList;

    private String id_user;
    public RiwayatViewAdapter(List<RiwayatAdapter> pesananList, Context context) {
        this.pesananList = pesananList;
        this.context = context;
        this.id_user = id_user;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.riwayat_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RiwayatAdapter pesanan = pesananList.get(position);

        // Mendapatkan string tanggal pemesanan dan pengembalian
        String tanggalPemesananString = pesanan.getTanggalPemesanan();
        String tanggalPengembalianString = pesanan.getTanggalPengembalian();

        // Mengatur format tanggal yang diinginkan
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));

        // Mengatur locale menjadi Indonesia untuk format angka
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(localeID);


        holder.txtIdPemesanan.setText("" + pesanan.getIdPemesanan());
        holder.txtTanggalPemesanan.setText("" + pesanan.getTanggalPemesanan());
        holder.txtTanggalPengembalian.setText("" + pesanan.getTanggalPengembalian());

        try {
            // Parsing string tanggal menjadi objek Date
            Date tanggalPemesanan = inputDateFormat.parse(tanggalPemesananString);
            Date tanggalPengembalian = inputDateFormat.parse(tanggalPengembalianString);

            // Mengatur nilai pada TextView dengan format tanggal
            holder.txtTanggalPemesanan.setText(outputDateFormat.format(tanggalPemesanan));
            holder.txtTanggalPengembalian.setText(outputDateFormat.format(tanggalPengembalian));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.txtJumlahHari.setText("" + pesanan.getJumlahHari() + " hari");
        holder.txtHargaTotal.setText( ""+ formatter.format(pesanan.getHargaTotal()));
        holder.txtDenda.setText("" + formatter.format(pesanan.getDenda()));
        holder.txtStatus.setText("" + pesanan.getStatus());
    }

    @Override
    public int getItemCount() {
        return pesananList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIdPemesanan;
        TextView txtTanggalPemesanan;
        TextView txtTanggalPengembalian;
        TextView txtJumlahHari;
        TextView txtHargaTotal;
        TextView txtDenda;
        TextView txtStatus;

        ViewHolder(View itemView) {
            super(itemView);
            txtIdPemesanan = itemView.findViewById(R.id.txtIdPemesanan);
            txtTanggalPemesanan = itemView.findViewById(R.id.txtTanggalPemesanan);
            txtTanggalPengembalian = itemView.findViewById(R.id.txtTanggalPengembalian);
            txtJumlahHari = itemView.findViewById(R.id.txtJumlahHari);
            txtHargaTotal = itemView.findViewById(R.id.txtHargaTotal);
            txtDenda = itemView.findViewById(R.id.txtDenda);
            txtStatus = itemView.findViewById(R.id.txtStatus);
        }
    }
}
