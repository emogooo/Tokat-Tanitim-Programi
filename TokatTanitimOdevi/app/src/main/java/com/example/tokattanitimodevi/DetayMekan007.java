package com.example.tokattanitimodevi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetayMekan007 extends AppCompatActivity {

    TextView baslik, aciklama;
    ImageView fotog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detay_mekan_007);

        fotog = findViewById(R.id.fotograf2);
        baslik = findViewById(R.id.txtbaslik2);
        aciklama = findViewById(R.id.txtaciklama);

        Intent i = getIntent();
        Mekan m = i.getParcelableExtra("obje");

        fotog.setImageBitmap(m.resim);
        baslik.setText(m.isim);
        aciklama.setText(m.aciklama);
    }
}
