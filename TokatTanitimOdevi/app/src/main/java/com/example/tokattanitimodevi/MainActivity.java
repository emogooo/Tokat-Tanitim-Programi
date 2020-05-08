package com.example.tokattanitimodevi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Mekan> mekan007 = new ArrayList<>();
    Button baglan, goster;
    String url="https://raw.githubusercontent.com/emogooo/MPVizeOdeviJSON/master/tokatim.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baglan = findViewById(R.id.btBaglan);
        goster = findViewById(R.id.btGoster);
        goster.setVisibility(View.INVISIBLE);

        baglan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonGetir jg = new JsonGetir();
                jg.execute(url);
                goster.setVisibility(View.VISIBLE);
                baglan.setVisibility(View.INVISIBLE);
            }
        });

        goster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView liste007 = findViewById(R.id.mekanListesi);
                MekanAdaptoruEmirhan007 mAE = new MekanAdaptoruEmirhan007(getApplicationContext(), mekan007);
                liste007.setAdapter(mAE);

                liste007.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent i = new Intent(getApplicationContext(), DetayMekan007.class);
                        i.putExtra("obje", mekan007.get(position));
                        startActivity(i);
                    }
                });
                goster.setVisibility(View.INVISIBLE);
            }
        });

    }

    class JsonGetir extends AsyncTask<String,Void,String> {
        StringBuilder sb = new StringBuilder();
        @Override
        protected String doInBackground(String... param) {
            StringBuffer cevap=null;
            try {
                URL url = new URL(param[0]);
                HttpURLConnection istek = (HttpURLConnection) url.openConnection();
                istek.setRequestMethod("GET");
                int cevap_kodu = istek.getResponseCode();
                Log.v("İZLEME", " : " + cevap_kodu );
                if(cevap_kodu==HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(istek.getInputStream()));
                    String satir="";
                    cevap = new StringBuffer();
                    while((satir=br.readLine())!=null){
                        cevap.append(satir);
                    }
                }
                istek.disconnect();
                JSONObject json = new JSONObject(cevap.toString());
                JSONArray liste = json.getJSONArray("tokatim");
                for(int i = 0; i<liste.length();i++){
                    JSONObject o = liste.getJSONObject(i);
                    String isim = o.getString("isim");
                    String tarih = o.getString("tarih");
                    String kisaaciklama = o.getString("kisaaciklama");
                    String aciklama = o.getString("aciklama");
                    String furl = o.getString("furl");
                    Bitmap b = getBitmapFromURL(furl);
                    mekan007.add(new Mekan(b,isim,tarih,aciklama,kisaaciklama));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return sb.toString() ;
        }
        @Override
        protected void onPostExecute(String s) {

        }

        public Bitmap getBitmapFromURL(String src) {
            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap mBitmap = BitmapFactory.decodeStream(input);
                return mBitmap;
            } catch (Exception e) {
                return null;
            }
        }
    }

}