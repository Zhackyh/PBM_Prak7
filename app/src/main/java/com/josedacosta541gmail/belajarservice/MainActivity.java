package com.josedacosta541gmail.belajarservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //todo 1 : merupakan variabel
    EditText editWaktu;
    Button tombolPlay,tombolStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //todo 2 : mengambil data variabel melalui Id nya.
        editWaktu = (EditText)findViewById(R.id.et_waktu);
        tombolPlay = (Button) findViewById(R.id.bt_play);
        tombolStop = (Button) findViewById(R.id.bt_stop);
        tombolPlay.setOnClickListener(this);
        tombolStop.setOnClickListener(this);
    }
    //todo 3 : merupakan aksi jika tombol diklik
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_play:
                callPlay();
                break;
            case R.id.bt_stop:
                stopPlay();
                break;
        }
    }

    //todo 4 : merupakan method stopPlay
    private void stopPlay() {
        stopService(new Intent(MainActivity.this, MyService.class));
    }
    //todo 4 : merupakan method CallPlay
    private void callPlay() {
        int detik = Integer.parseInt(editWaktu.getText().toString());
        Intent intent = new Intent(MainActivity.this, MyService.class);

        PendingIntent pendingIntent = PendingIntent.getService(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        if(alarmManager !=null){
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(detik * 1000), pendingIntent);

            Toast.makeText(getApplicationContext(),"Song Play After"+detik+" seconds !", Toast.LENGTH_LONG).show();
        }
    }
}
