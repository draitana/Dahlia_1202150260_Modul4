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
    ListView listnm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        setTitle ( "AsyncTask" );
        listnm = findViewById ( R.id.listnama );
    }

    public void mulai(View view) {
        new getData (listnm).execute();
    }

    private class getData extends AsyncTask {
        ListView listnm;
        ArrayAdapter adapter;
        ArrayList<String> listnama;
        ProgressDialog dialog;

        public getData(ListView listnm) {
            this.listnm = listnm;
            dialog = new ProgressDialog(MainActivity.this);
            listnama = new ArrayList<>();
        }


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

        @Override
        protected Object doInBackground(Object[] objects) {
            adapter = new ArrayAdapter<> (MainActivity.this, android.R.layout.simple_list_item_1, listnama  );
            String[] mhs = getResources().getStringArray(R.array.namaMhs);
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

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute ( o );
            listnm.setAdapter(adapter);
            dialog.dismiss();
        }
    }
}
