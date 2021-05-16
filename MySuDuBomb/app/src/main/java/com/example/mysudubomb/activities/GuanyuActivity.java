package com.example.mysudubomb.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.mysudubomb.R;
import com.example.mysudubomb.utils.NoActionBar;

public class GuanyuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanyu);
        NoActionBar.noActionBar(this);



    }

    public void onclick(View view) {
        finish();
    }
}
