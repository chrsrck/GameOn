package com.chrsrck.gameon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.WindowManager;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText email;
    EditText pw;
    Button login;
    ImageView icon;
    public static String username;
    public static String password;
    public static int RequestMade = 0;
    public static int DeleteMade = 0;
    public static GameOnDatabase gameOnDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email_field);
        pw = findViewById(R.id.pw_field);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        icon = findViewById(R.id.icon);
        //Picasso.with(this).load("http://icons.iconarchive.com/icons/graphicloads/100-flat/256/home-icon.png").into(icon);
        //icon.setVisibility(View.VISIBLE);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        gameOnDatabase = new GameOnDatabase();
        gameOnDatabase.mContext = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == login.getId()) {
            username = email.getText().toString().trim();
            password = pw.getText().toString().trim();
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }
}
