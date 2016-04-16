package com.example.saveenergy;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.data.MySharedPerferences;

/**
 * Created by mc on 2016/4/4.
 */
public class EditPowerActivity extends AppCompatActivity implements View.OnClickListener{

    MySharedPerferences energyPowerSetting;
    EditText power1,power2,power3,power4;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editpower);
        power1=(EditText)findViewById(R.id.power1);
        power2=(EditText)findViewById(R.id.power2);
        power3=(EditText)findViewById(R.id.power3);
        power4=(EditText)findViewById(R.id.power4);
        toolbar=(Toolbar) findViewById(R.id.toolbar);
        Button oneKeySet=(Button)findViewById(R.id.one_key_set);
        oneKeySet.setOnClickListener(this);
        energyPowerSetting =new MySharedPerferences(this,MySharedPerferences.DATASETTINGFILE);
        setSupportActionBar(toolbar);
    }


    @Override
    protected void onResume() {
        super.onResume();
        power1.setText(String.valueOf(energyPowerSetting.getFloat(MySharedPerferences.power1, 0)));
        power2.setText(String.valueOf(energyPowerSetting.getFloat(MySharedPerferences.power2, 0)));
        power3.setText(String.valueOf(energyPowerSetting.getFloat(MySharedPerferences.power3, 0)));
        power4.setText(String.valueOf(energyPowerSetting.getFloat(MySharedPerferences.power4, 0)));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.one_key_set:finish(); break;
        }
    }

    @Override
    protected void onDestroy() {
        energyPowerSetting.setFloat(MySharedPerferences.power1,Float.valueOf(power1.getText().toString()));
        energyPowerSetting.setFloat(MySharedPerferences.power2, Float.valueOf(power2.getText().toString()));
        energyPowerSetting.setFloat(MySharedPerferences.power3,Float.valueOf(power3.getText().toString()));
        energyPowerSetting.setFloat(MySharedPerferences.power4,Float.valueOf(power4.getText().toString()));
        super.onDestroy();
    }
}
