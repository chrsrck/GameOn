package com.chrsrck.gameon;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.graphics.Paint;

import java.util.LinkedList;

public class ViewDamagedActivity extends AppCompatActivity {
    TextView[] strings;
    TextView text;
    TableLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_damage);
        MainActivity.gameOnDatabase.mContext = this;
        text = findViewById(R.id.totalRequest);
        layout = findViewById(R.id.tablelayout);
        LinkedList<Equipment> brokenReports = MainActivity.gameOnDatabase.getAllBrokenReports();
        int length = brokenReports.size();
        strings = new TextView[length*4];

        text.setText("There are " + length + " damaged equipment reports.");
        int paddingPixel = 10;
        float density = this.getResources().getDisplayMetrics().density;
        int paddingDp = (int)(paddingPixel * density);

        int paddingPixel2 = 2;
        float density2 = this.getResources().getDisplayMetrics().density;
        int paddingDp2 = (int)(paddingPixel2 * density2);

        for (int i = 0; i < length; i++) {
            TableRow row = new TableRow(this);
            strings[i*4] = new TextView(this);
            strings[i*4].setLayoutParams(new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            strings[i*4].setText(brokenReports.get(i).getItemName());
            strings[i*4].setTextColor(Color.WHITE);
            strings[i*4].setTextSize(18);
            strings[i*4].setGravity(Gravity.CENTER);
            strings[i*4].setPadding(0, paddingDp, 0, 0);
            strings[i*4 + 1] = new TextView(this);
            strings[i*4 + 1].setLayoutParams(new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            strings[i*4 + 1].setText("Reporter");
            strings[i*4 + 1].setTextColor(Color.WHITE);
            strings[i*4 + 1].setTextSize(18);
            strings[i*4 + 1].setPaintFlags(strings[i*4 + 1].getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
            strings[i*4 + 1].setGravity(Gravity.CENTER);
            strings[i*4 + 1].setPadding(0, paddingDp, 0, 0);
            row.addView(strings[i*4]);
            row.addView(strings[i*4 + 1]);
            TableRow row2 = new TableRow(this);
            strings[i*4 + 2] = new TextView(this);
            strings[i*4 + 2].setLayoutParams(new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            strings[i*4 + 2].setText("Item ID: " + brokenReports.get(i).getIdNum());
            strings[i*4 + 2].setTextColor(Color.WHITE);
            strings[i*4 + 2].setTextSize(18);
            strings[i*4 + 2].setGravity(Gravity.CENTER);
            strings[i*4 + 2].setPadding(0, paddingDp, 0, paddingDp);
            strings[i*4 + 3] = new TextView(this);
            strings[i*4 + 3].setLayoutParams(new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            strings[i*4 + 3].setText(brokenReports.get(i).getReporter());
            strings[i*4 + 3].setTextColor(Color.WHITE);
            strings[i*4 + 3].setTextSize(18);
            strings[i*4 + 3].setGravity(Gravity.CENTER);
            strings[i*4 + 3].setPadding(0, paddingDp, 0, paddingDp);
            row2.addView(strings[i*4 + 2]);
            row2.addView(strings[i*4 + 3]);
            layout.addView(row,i*4);
            layout.addView(row2, i*4 + 1);

            TableRow row3 = new TableRow(this);
            TextView t1 = new TextView(this);
            t1.setLayoutParams(new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            t1.setText("Description: " + brokenReports.get(i).getDescription());
            t1.setTextColor(Color.WHITE);
            t1.setTextSize(18);
            t1.setGravity(Gravity.CENTER);
            t1.setPadding(0, 0, 0, paddingDp);
            row3.addView(t1);
            layout.addView(row3, i*4 + 2);

            View view = new View(this);
            view.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, paddingDp2));
            view.setBackgroundColor(Color.WHITE);
            layout.addView(view, i*4 + 3);
        }
    }
}
