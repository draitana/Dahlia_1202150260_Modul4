package com.example.a3d.dahlia_1202150260_studycase4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by 3D on 3/18/2018.
 */

public class Cari_Gambar extends AppCompatActivity {
    ImageView gambar;
    EditText link;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_cari_gambar );
        setTitle ( "AsyncTask" );
        gambar = findViewById ( R.id.gambar );
        link = findViewById ( R.id.link );
    }
    public void cari (View view) {
        Picasso.with ( Cari_Gambar.this ).load ( link.getText ().toString () )
                .placeholder ( R.mipmap.ic_launcher_round )
                .error ( R.mipmap.ic_launcher )
                .into ( gambar );
    }
}
