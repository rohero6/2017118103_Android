package com.example.xposedtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SyncStatusObserver;
import android.nfc.Tag;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("手机IMEI：",getIMEI(this));
        Toast.makeText(this, "IMEL"+getIMEI(this), Toast.LENGTH_SHORT).show();
    }

    public static final String getIMEI(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

            String imei = telephonyManager.getDeviceId();
            if (imei == null) {
                return "";
            }
            return imei;
        } catch (Exception e) {
           System.out.println(e);
            return "";
        }

    }
}
