package com.example.tokattanitimodevi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MekanAdaptoruEmirhan007 extends BaseAdapter {

    Context con;
    ArrayList<Mekan> mekan007;

    public MekanAdaptoruEmirhan007(Context con, ArrayList<Mekan> mekan) {
        this.con = con;
        this.mekan007 = mekan;
    }

    @Override
    public int getCount() {
        return mekan007.size();
    }

    @Override
    public Object getItem(int position) {
        return mekan007.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inf = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View herbirmekan007 = inf.inflate(R.layout.her_bir_mekan_emirhan, parent, false);

        ImageView foto = herbirmekan007.findViewById(R.id.fotograf);
        TextView isim = herbirmekan007.findViewById(R.id.txtbaslik);
        TextView tarih = herbirmekan007.findViewById(R.id.txttarih);
        TextView kisaaciklama = herbirmekan007.findViewById(R.id.txtkisaaciklama);

        foto.setImageBitmap(mekan007.get(position).resim);
        isim.setText(mekan007.get(position).isim);
        tarih.setText("İnşa Tarihi: " + mekan007.get(position).tarih);
        kisaaciklama.setText(mekan007.get(position).kisaaciklama);

        return herbirmekan007;

    }


}

