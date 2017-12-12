package com.chrsrck.gameon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;

import com.squareup.picasso.Picasso;

public class ChooseSportActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton football;
    ImageButton soccer;
    ImageButton softball;
    ImageButton frisbee;
    ImageButton kickball;
    ImageButton general;

    public static final String OPTIONS = "items";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        football = findViewById(R.id.football);
        soccer = findViewById(R.id.soccer);
        softball = findViewById(R.id.softball);
        frisbee = findViewById(R.id.frisbee);
        kickball = findViewById(R.id.kickball);
        general = findViewById(R.id.general);
        football.setOnClickListener(this);
        Picasso.with(this).load("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/American_football_icon_simple.svg/2000px-American_football_icon_simple.svg.png").into(football);
        football.setVisibility(View.VISIBLE);
        soccer.setOnClickListener(this);
        Picasso.with(this).load("https://rocketdock.com/images/screenshots/soccer.png").into(soccer);
        soccer.setVisibility(View.VISIBLE);
        softball.setOnClickListener(this);
        Picasso.with(this).load("https://bloximages.chicago2.vip.townnews.com/jacksonvilleprogress.com/content/tncms/assets/v3/editorial/6/ef/6ef01ef8-cd9e-11e4-93ef-bb88ff3f27e8/5509c742b2da6.image.png").into(softball);
        softball.setVisibility(View.VISIBLE);
        kickball.setOnClickListener(this);
        Picasso.with(this).load("http://pack7.com/wp-content/uploads/2017/07/Kickball.png").into(kickball);
        kickball.setVisibility(View.VISIBLE);
        frisbee.setOnClickListener(this);
        Picasso.with(this).load("http://www.drllz.com/wp-content/uploads/2017/02/BallIcon_UltimateB.png").into(frisbee);
        frisbee.setVisibility(View.VISIBLE);
        general.setOnClickListener(this);
        Picasso.with(this).load("https://university.ffspro.com/media/1213/installation-safety-icon.png").into(general);
        general.setVisibility(View.VISIBLE);
        MainActivity.gameOnDatabase.mContext = this;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, RequestActivity.class);
        if (view.getId() == football.getId()) {
            String[] items = new String[]{"Football - Official Size", "Football - Youth Size", "Penalty Flags",
                    "Flag Belts - White", "Flag Belts - Blue", "Flag Belts - Red",
                    "Flag Belts - Orange", "Flag Belts - Yellow", "Flag Belts - Green",
                    "Flag Football Pucks - Yellow", "Flag Football Pucks - Red/Orange", "Stopwatches",
                    "Down Indicators", "Line Markers - 40 yd", "Line Markers - 20yd", "Pylons"};
            intent.putExtra(OPTIONS, items);
        }
        else if (view.getId() == soccer.getId()) {
            String[] items = new String[]{"Soccer Balls", "Soccer Cards - Yellow", "Soccer Cards - Red",
                    "Soccer AR Flags - Yellow", "Soccer AR Flags - Red", "Corner Flags and Bases - Ground Spike Flags",
                    "Corner Flags and Bases - Black Bases"};
            intent.putExtra(OPTIONS, items);
        }
        else if (view.getId() == softball.getId()) {
            String[] items = new String[]{"Womens Game Balls", "Womens Practice Balls", "Mens Game Balls",
                    "Mens Practice Balls", "Bats - Orange", "Bats - Blue", "Bats - Green",
                    "Batting Helmets", "Catcher's Masks", "Bases - 2 inch", "Bases - 1/2 inch",
                    "Home Plates - 1/2 inch", "Ump - Ball/Strike/Out Counters", "Ump - Homeplate Brushes",
                    "Ump - Gray Game Bags"};
            intent.putExtra(OPTIONS, items);
        }
        else if (view.getId() == frisbee.getId()) {
            String[] items = new String[]{"", "Frisbee Discs"};
            intent.putExtra(OPTIONS, items);
        }
        else if (view.getId() == kickball.getId()) {
            String[] items = new String[]{"Kickballs - Yellow", "Kickballs = Red", "Discs - Orange Bases"};
            intent.putExtra(OPTIONS, items);
        }
        else if (view.getId() == general.getId()) {
            String[] items = new String[]{"Tables - Large", "Tables - Half-width", "Chairs - Plastic Brown",
                    "Chairs - Metal Blue", "Chairs - Metal Beige", "Flip Scorecards",
                    "iPad Bags - Blue", "Scorecard Containers - Green", "Scorecard Containers - Red",
                    "First Aid Kits", "Jackets - Officials", "Jackets - Scorekeepers",
                    "Cones - Small Plastic", "Cones - Plastic Pucks", "Cones - Small Rubber",
                    "Cones - Medium Rubber", "Cones - Large Rubber", "Pinnies - Green", "Pinnies - Maroon",
                    "Pinnies - Purple", "Pinnies - Yellow", "Pinnies - Red", "Pinnies - Salmon",
                    "Pinnies - Orange", "Cornhole Set w/Bags", "Printer", "Coffeemakers", "Net",
                    "Electric Ball Pumps", "American Flag", "Umbrellas", "Megaphone", "Sportsmanship Banners"};
            intent.putExtra(OPTIONS, items);
        }
        startActivity(intent);
    }
}
