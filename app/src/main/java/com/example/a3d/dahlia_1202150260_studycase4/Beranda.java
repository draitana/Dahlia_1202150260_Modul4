package com.example.a3d.dahlia_1202150260_studycase4;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by 3D on 3/18/2018.
 */

public class Beranda extends AppCompatActivity{

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.acitivity_beranda );
        setTitle ( "AsyncTask" );
    }
        //method ketika klik button
    public void list_mahasiswa(View view) {
        //untuk berpindah ke MainActivity
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    //method ketika klik button
    public void cari_gambar(View view) {
        //untuk berpindah ke Cari_Gambar
        Intent intent = new Intent(this, Cari_Gambar.class);
        startActivity(intent);
    }
}