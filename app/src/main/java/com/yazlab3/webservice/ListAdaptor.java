package com.yazlab3.webservice;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yazlab3.webservice.model.Ev;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Extension;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by alpaydin on 20.05.2017.
 */

public class ListAdaptor extends BaseAdapter {

    LayoutInflater inflater;
    List<Ev> evList;
    ImageView resim;

    public ListAdaptor(Activity activity , List<Ev> ev){
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        evList = ev;
    }

     @Override
   public int getCount() {return evList.size();}

    @Override
    public Object getItem(int position) {
        return evList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        view = inflater.inflate(R.layout.liste,null);

        resim = (ImageView)view.findViewById(R.id.resim);
        TextView baslik = (TextView) view.findViewById(R.id.baslik);
        TextView fiyat = (TextView) view.findViewById(R.id.fiyat);

        Ev ev = evList.get(position);

        baslik.setText(ev.getAciklama().toString());
        fiyat.setText(NumberFormat.getCurrencyInstance().format(ev.getFiyat()));
        if(ev.getResimler() != null){
            resim.setImageBitmap(ev.getResimler().get(0).getBitmap());
        }

        return view;
    }
}
