package com.example.sam.testapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sam);

        Intent intent = getIntent();
        String message = intent.getStringExtra("Mensaje");
        TextView txtShow = (TextView) findViewById(R.id.txtView);
        Button btn_close = (Button) findViewById(R.id.btnBack);
        txtShow.setText(message);

        btn_close.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
            }
        });
    }
}
