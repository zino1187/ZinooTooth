package com.example.student.zinootooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String TAG=this.getClass().getName();
    BluetoothAdapter bluetoothAdapter;
    BroadcastReceiver broadcastReceiver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
               if(intent.getAction().equals(BluetoothDevice.ACTION_FOUND)){
                    //Toast.makeText(getApplicationContext(), "장치발견!!", Toast.LENGTH_SHORT).show();
                   Log.d(TAG, "장치발견");
               }
            }
        };

        init();
    }

    public void init(){
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        String msg=null;
        if(bluetoothAdapter == null){
            msg="이 디바이스는 블루투스를 지원하지 않습니다.";
        }else{
            msg="블루투스 사용가능";
        }
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    //주변 다른 디바이스를 검색
    public void scanDevice(){
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(broadcastReceiver, filter);
        bluetoothAdapter.startDiscovery();//검색시작!!
    }

    public void btnClick(View view){
        scanDevice();
    }
}
