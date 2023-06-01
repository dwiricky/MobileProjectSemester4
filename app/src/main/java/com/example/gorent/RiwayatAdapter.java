package com.example.gorent;

public class RiwayatAdapter {

        private String id_pemesanan;
        private String platnomor;
        private String tanggal_pemesanan;
        private String tanggal_pengembalian;
        private int jumlah_hari;
        private int hargatotal;
        private int denda;
        private String status;

        public String getIdPemesanan() {
            return id_pemesanan;
        }

        public void setIdPemesanan(String id_pemesanan) {
            this.id_pemesanan = id_pemesanan;
        }

        public String getPlatnomor() {
            return platnomor;
        }

        public void setPlatnomor(String platnomor) {
            this.platnomor = platnomor;
        }

        public String getTanggalPemesanan() {
            return tanggal_pemesanan;
        }

        public void setTanggalPemesanan(String tanggal_pemesanan) {
            this.tanggal_pemesanan = tanggal_pemesanan;
        }

        public String getTanggalPengembalian() {
            return tanggal_pengembalian;
        }

        public void setTanggalPengembalian(String tanggal_pengembalian) {
            this.tanggal_pengembalian = tanggal_pengembalian;
        }

        public int getJumlahHari() {
            return jumlah_hari;
        }

        public void setJumlahHari(int jumlah_hari) {
            this.jumlah_hari = jumlah_hari;
        }

        public int getHargaTotal() {
            return hargatotal;
        }

        public void setHargaTotal(int hargatotal) {
            this.hargatotal = hargatotal;
        }

        public int getDenda() {
            return denda;
        }

        public void setDenda(int denda) {
            this.denda = denda;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }


}
