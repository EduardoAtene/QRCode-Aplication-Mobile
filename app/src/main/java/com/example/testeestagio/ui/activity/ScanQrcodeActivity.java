package com.example.testeestagio.ui.activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.testeestagio.R;
import com.google.zxing.Result;


public class ScanQrcodeActivity extends Activity {
    private CodeScanner mCodeScanner;
    private static String dadoScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qrcode);

        // Validacao permissao da camera
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},0);
        }

        CodeScannerView scanView = findViewById(R.id.scan_qrcode);
        mCodeScanner = new CodeScanner(this,scanView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dadoScan = result.getText();
                        startActivity(new Intent(ScanQrcodeActivity.this,DadosPokemonActivity.class));

                    }



                });
            }
        });
    }




    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    protected static String getDadoScan() {
        return dadoScan;
    }
}