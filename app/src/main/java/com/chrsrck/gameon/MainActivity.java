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
    public static final String USERNAME = "name";
    public static final String PASSWORD = "pw";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email_field);
        pw = findViewById(R.id.pw_field);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        icon = findViewById(R.id.icon);
        Picasso.with(this).load("http://icons.iconarchive.com/icons/graphicloads/100-flat/256/home-icon.png").into(icon);
        icon.setVisibility(View.VISIBLE);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == login.getId()) {
            String emailText = email.getText().toString().trim();
            String pwText = pw.getText().toString().trim();
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(USERNAME, emailText);
            intent.putExtra(PASSWORD, pwText);
            startActivity(intent);
        }
    }
}
