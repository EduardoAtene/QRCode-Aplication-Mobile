package com.example.testeestagio.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testeestagio.R;

public class TelaInicialAcitivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial_acitivity);

        Button button_scan = findViewById(R.id.button_scan_qrcode);
        button_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TelaInicialAcitivity.this,ScanQrcodeActivity.class));

            }
        });

    }
}