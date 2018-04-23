package com.yazlab3.webservice;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.yazlab3.webservice.model.Ev;
import com.yazlab3.webservice.model.Resim;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    Handler handler;
    ListView listView;
    List<Ev> evler;
    Map<Integer , Ev> evMap;
    List<Resim> resimList;
    String json;
    Button b;
    ListAdaptor listAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lv);
        evler = new ArrayList<>();
        evMap = new HashMap<>();




        handler = new Handler();

        final Runnable runnable = new Runnable() {
            String url = "http://192.168.200.2:8080/YazLabWebService/DB/EvList";
            String resim = "http://192.168.200.2:8080/YazLabWebService/DB/ResimList";
            @Override
            public void run() {
                new EvList().execute(url);
                new ResimList().execute(resim);
                if(resimList != null){
                    new ResimCek().execute(resimList);
                }



                listAdaptor = new ListAdaptor(MainActivity.this,evler);
                listView.setAdapter(listAdaptor);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Bundle bundle = new Bundle();

                        Intent intent = new Intent();
                        bundle.putParcelable("evBundle",evler.get(0));

                        for(int i = 0;i<evler.get(position).getResimler().size();i++){
                            Resim r = evler.get(position).getResimler().get(i);
                            if(r!=null){
                                Bitmap bmp = evler.get(position).getResimler().get(i).getBitmap();
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                byte[] byteArray = stream.toByteArray();
                                intent.putExtra("resim"+i, byteArray);
                            }
                        }
                       //// Log.d("Ev1" , ""+evler.get(0).getId());
                        intent.setClass(MainActivity.this,EvActivity.class);
                        intent.putExtra("Ev",bundle);
                        startActivity(intent);
                    }
                });
                handler.postDelayed(this,2000);
            }
        };
        handler.postDelayed(runnable,1000);
    }
    class EvList extends AsyncTask<String , String , String>{

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader br = null;
            try{
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                String satir;
                String dosya = "";
                while((satir=br.readLine())!=null){
                    dosya+=satir;
                }



                return dosya;

            }catch (Exception e){
                e.printStackTrace();
            }


            return "hata";
        }

        @Override
        protected void onPostExecute(String s) {
            evler = new ArrayList<>();
            json = s;
            Ev ev = new Ev();
            try {
                JSONArray array = new JSONArray(json);
                JSONObject js;
                for(int i = 0;i<array.length();i++){
                    js = array.getJSONObject(i);
                    ev.setId(js.getInt("id"));
                    ev.setAlan(js.getInt("alan"));
                    ev.setBinaYasi(js.getInt("binaYasi"));
                    ev.setBulKat(js.getInt("bulKat"));
                    ev.setEmlakTipi(js.getString("emlakTipi"));
                    ev.setFiyat(js.getInt("fiyat"));
                    ev.setIl(js.getString("il"));
                    ev.setOdaSayisi(js.getInt("odaSayisi"));
                    ev.setAciklama(js.getString("aciklama"));

                    evMap.put(ev.getId(),ev);
                    evler.add(ev);
                    ev = new Ev();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    class ResimList extends AsyncTask<String , String , String>{

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader br = null;
            try{
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is));
                String satir;
                String dosya = "";
                while((satir=br.readLine())!=null){
                    dosya+=satir;
                }
                return dosya;

            }catch (Exception e){
                e.printStackTrace();
            }


            return "hata";
        }

        @Override
        protected void onPostExecute(String s) {
            json = s;
            Resim r = new Resim();
            resimList = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(json);
                JSONObject js;
                for(int i = 0;i<array.length();i++){
                    js = array.getJSONObject(i);
                    r.setId(js.getInt("id"));
                    r.setEvID(js.getInt("evID"));
                    r.setDizin(js.getString("dizin"));

                    if(evMap.get(r.getEvID()).resimler == null){
                        evMap.get(r.getEvID()).resimler = new ArrayList<>();
                    }
                    evMap.get(r.getEvID()).resimler.add(r);
                    resimList.add(r);
                    r = new Resim();

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    class ResimCek extends AsyncTask<List<Resim> , String , List<Bitmap>>{

        @Override
        protected List<Bitmap> doInBackground(List<Resim>... params) {

            List<Bitmap> resimList = new ArrayList<>();
            try{
                String url = "http://192.168.200.2:8080/YazLabWebService/DB/Resim/";
                Bitmap mIcon11 = null;

                for(Resim r : params[0]){
                    InputStream in = new java.net.URL(url+r.getId()).openStream();
                    mIcon11 = BitmapFactory.decodeStream(in);
                    resimList.add(mIcon11);
                }

                return resimList;

            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Bitmap> gResim) {
            for(int i = 0;i<gResim.size();i++){
                resimList.get(i).setBitmap(gResim.get(i));
            }
        }
    }

}
