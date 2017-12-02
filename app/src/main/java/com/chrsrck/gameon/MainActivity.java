package com.chrsrck.gameon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeScreens(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.Check:
                intent = new Intent(this, CheckActivity.class);
                break;
            case R.id.Request:
                intent = new Intent(this, RequestActivity.class);
                break;
            case R.id.Damage:
                intent = new Intent(this, DamageActivity.class);
                break;
            case R.id.Options:
                intent = new Intent(this, OptionsActivity.class);
                break;
            default:
                intent = null;
        }
        startActivity(intent);
    }
}
