package com.chrsrck.gameon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

public class ViewRequests extends AppCompatActivity implements View.OnClickListener{
    TextView t1;
    Button b1;
    Button b2;
    TableLayout l1;
    TableLayout l2;
    int count = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (MainActivity.username.equals("1")) {
            if (MainActivity.RequestMade == 0) {
                setContentView(R.layout.activity_view_requests);
            }
            else {
                setContentView(R.layout.activity_view_requests2);
                MainActivity.RequestMade = 0;
            }
        }
        else {
            setContentView(R.layout.activity_view_requests3);
            b1 = findViewById(R.id.complete1);
            b2 = findViewById(R.id.complete2);
            b1.setOnClickListener(this);
            b2.setOnClickListener(this);
            l1 = findViewById(R.id.layout1);
            l2 = findViewById(R.id.layout2);
            t1 = findViewById(R.id.numRequests);
            if (MainActivity.DeleteMade == 1) {
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                t1.setText("There are 0 pending requests.");
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == b1.getId()) {
            l1.setVisibility(View.GONE);
            count--;
            t1.setText("There are " + count + " pending requests.");
            MainActivity.DeleteMade = 1;
        }
        else {
            l2.setVisibility(View.GONE);
            count--;
            t1.setText("There are " + count + " pending requests.");
            MainActivity.DeleteMade = 1;
        }
    }
}
