package com.example.testeestagio.api;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;


import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConexaoImage {

    @Nullable
    public static Bitmap getDados(String uri) {
        Bitmap img = null;

        try {
            URL url = new URL(uri);
            Log.e("Tste","a");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();;
            InputStream input = httpURLConnection.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(input);
            img = BitmapFactory.decodeStream(bis);
            bis.close();
            input.close();
            Log.e("Ts34te","d");
            return img;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ERRO", String.valueOf(e));
            return null;
        }
    }
}
