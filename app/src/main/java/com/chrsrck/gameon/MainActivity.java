package com.chrsrck.gameon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/*
 *  This activity is the Login activity for our application. It stores the user name and password for the
 *  user. If you want to login as a scorekeeper, use "scorekeeper@vt.edu" in the email. If you want to login
 *  as a supervisor, use "supervisor@vt.edu" in the email. If you want all options then login with everything blank.
 *  A password is not required and is not tested.
 */
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
        gameOnDatabase = new GameOnDatabase();
        gameOnDatabase.mContext = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    /*
     *  Handles all onClick events for the activity
     */
    @Override
    public void onClick(View view) {
        if (view.getId() == login.getId()) {
            username = email.getText().toString().trim();
            password = pw.getText().toString().trim();
            if (username.equals("supervisor@vt.edu")) {
                isSupervisor = true;
            }
            if (username.equals("scorekeeper@vt.edu") || username.equals("supervisor@vt.edu") || username.equals("")) {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
        }
    }
}
