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
    public static String username;
    public static String password;
    public static boolean isSupervisor = false;
    public static GameOnDatabase gameOnDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email_field);
        pw = findViewById(R.id.pw_field);
        login = findViewById(R.id.login);
        login.setOnClickListener(this);
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
            if (username.equals("supervisor@vt.edu") || username.equals("")) {
                isSupervisor = true;
            }
            //if (username.equals("scorekeeper@vt.edu") || username.equals("supervisor@vt.edu")) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            //}
        }
    }
}
