package com.example.gorent;

public class DataAdapter {
    public String adapter_id;
    public String adapter_nama;
    public String adapter_url;
    public String adapter_harga;
    public String adapater_jeniskendaraan;

    public String getid(){return adapter_id;}
    public void setid(String id){
        this.adapter_id=id;
    }
    public String getjudul(){return adapter_nama;}
    public void setnama(String nama){
        this.adapter_nama=nama;
    }
    public String geturl(){return adapter_url;}
    public void seturl(String url){
        this.adapter_url=url;
    }

    public String getharga(){return adapter_harga;}
    public void setharga(String harga){this.adapter_harga=harga;}
    public String getjeniskendaraan(){return adapater_jeniskendaraan;}
    public void setjeniskendaraan(String jeniskendaraan){this.adapater_jeniskendaraan=jeniskendaraan;}
}
