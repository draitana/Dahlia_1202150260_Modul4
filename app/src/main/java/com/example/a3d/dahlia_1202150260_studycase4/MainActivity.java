package com.example.a3d.dahlia_1202150260_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   //deklarasi variable
    ListView listnm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        //mengatur judul tampilan
        setTitle ( "AsyncTask" );

        //memangil variable dari layout
        listnm = findViewById ( R.id.listnama );
    }

    //method ketika button di-klik
    public void mulai(View view) {

        new getData (listnm).execute(); //asynctak mulai berjalan
    }

    private class getData extends AsyncTask {
        ListView listnm;
        ArrayAdapter adapter;
        ArrayList<String> listnama;
        ProgressDialog dialog;

        //konstruktur saat asynctask
        public getData(ListView listnm) {
            this.listnm = listnm;
            dialog = new ProgressDialog(MainActivity.this);
            listnama = new ArrayList<>();
        }

    //method ketika proses asynctask belum berjalan
        @Override
        protected void onPreExecute() {
            super.onPreExecute ();

            dialog.setTitle ( "Loading" );
            dialog.setIndeterminate ( true );
            dialog.setProgress ( 0 );
            dialog.setMax ( 100 );
            dialog.setProgressStyle ( ProgressDialog.STYLE_SPINNER );
            dialog.setCancelable ( true );
            dialog.setButton ( DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialog.dismiss();
                    getData.this.cancel(true);
                }
            });
            dialog.show ();

        }
    //method ketika proses asynctask berjalan
        @Override
        protected Object doInBackground(Object[] objects) {
            //adapter array
            adapter = new ArrayAdapter<> (MainActivity.this, android.R.layout.simple_list_item_1, listnama  );

            //array disimpan pada variable mhs
            String[] mhs = getResources().getStringArray(R.array.namaMhs);

            //perulangan untuk menyimpan array
            for (int a = 0; a < mhs.length; a++) {
                final long persen = 100L * a / mhs.length;
                final String nama = mhs[a];
                try {
                    Runnable change = new Runnable() {
                        @Override
                        public void run() {
                            dialog.setMessage((int) persen+"% - Adding "+nama);
                        }
                    };
                    runOnUiThread(change);
                    Thread.sleep(300);
                    listnama.add(mhs[a]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    //proses ketika asynctask sudah berjalan
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute ( o );
            listnm.setAdapter(adapter);
            dialog.dismiss();
        }
    }
}
