package com.yazlab3.webservice;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.yazlab3.webservice.model.Ev;
import com.yazlab3.webservice.model.Resim;

import java.util.ArrayList;
import java.util.List;

public class EvActivity extends Activity {

    TextView fiyat , binaYasi , alan , il , emlakTipi, odaSayisi , bulKat , aciklama;
    ImageView r1,r2,r3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ev);

        fiyat = (TextView) findViewById(R.id.fiyat);
        binaYasi = (TextView) findViewById(R.id.binaYasi);
        alan = (TextView) findViewById(R.id.alan);
        il = (TextView) findViewById(R.id.il);
        emlakTipi = (TextView) findViewById(R.id.emlakTipi);
        odaSayisi = (TextView) findViewById(R.id.odaSayisi);
        bulKat = (TextView) findViewById(R.id.bulKat);
        aciklama = (TextView) findViewById(R.id.aciklama);

        r1 = (ImageView) findViewById(R.id.resim1);
        r2 = (ImageView) findViewById(R.id.resim2);
        r3 = (ImageView) findViewById(R.id.resim3);

        Bundle bundle = (Bundle) getIntent().getExtras().getBundle("Ev");
        Ev ev = bundle.getParcelable("evBundle");

        for(int i = 0;i<3;i++){
            if(getIntent().getExtras().getByteArray("resim"+i)!=null){
                byte[] byteArray = getIntent().getExtras().getByteArray("resim"+i);
                Bitmap bitMap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                if(i==0){
                    r1.setImageBitmap(bitMap);
                }else if(i==1){
                    r2.setImageBitmap(bitMap);
                }else{
                    r3.setImageBitmap(bitMap);
                }
            }
        }



        fiyat.setText(""+ev.getFiyat());
        binaYasi.setText(""+ev.getBinaYasi());
        alan.setText(""+ev.getAlan());
        il.setText(""+ev.getIl());
        emlakTipi.setText(""+ev.getEmlakTipi());
        odaSayisi.setText(""+ev.getOdaSayisi());
        bulKat.setText(""+ev.getBulKat());
        aciklama.setText(""+ev.getAciklama());




    }
}
