package com.example.balvinder.bookYourShow.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.balvinder.bookYourShow.R;
import com.example.balvinder.bookYourShow.util.MyTextView;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    MyTextView titletext,castdata,directordata,plotdata;
    ImageView pic,back;
    Context context;
    TextView likeCount;

    public CheckBox likeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        context=this;
        Bundle b=getIntent().getExtras();
        String name=b.getString("name");
        String url=b.getString("url");
        back=(ImageView) findViewById(R.id.backimage123);
        castdata=(MyTextView) findViewById(R.id.castdata);
        directordata=(MyTextView) findViewById(R.id.directordata);
        plotdata=(MyTextView) findViewById(R.id.plotdata);
        titletext=(MyTextView) findViewById(R.id.titletext);
        likeCount=(TextView) findViewById(R.id.likeCount);
        likeImage=(CheckBox) findViewById(R.id.likeImage);

        titletext.setText(name);
        pic=(ImageView) findViewById(R.id.pic);
        if(!url.isEmpty()) {
            Picasso.with(context).load(url).into(pic);
            likeCount.setText("534");
        }
        else{

        }


        likeImage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    int count = Integer.parseInt(likeCount.getText().toString()) + 1;
                    likeCount.setText(Integer.toString(count));
                } else {

                    int count = Integer.parseInt(likeCount.getText().toString())-1;
                    likeCount.setText(Integer.toString(count));
                }
              }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        if(b.getString("name").equals("STAR TREK BEYOND")){
            directordata.setText("Justin Lin");
            likeCount.setText("270");
            plotdata.setText("Three years into its five year mission, the USS Enterprise arrives at Starbase Yorktown, a massive space station, for resupply and shore leave for her crew. Struggling to find continued meaning in the endless nature of their mission of exploration, Captain James T. Kirk has applied for a promotion to Vice Admiral and commanding officer of Yorktown. He recommends Spock as the new captain of the Enterprise. Meanwhile, Hikaru Sulu reunites with his family, Montgomery Scott works to keep the ship operational, and Spock and Nyota Uhura amicably end their relationship; Spock also receives word from New Vulcan that Ambassador Spock (Spock's future self from the original timeline) has died.");
            castdata.setText("Chris Pine, Zachary Quinto, Karl Urban aer Leonard McCoy, Simon Pegg, Zoe Saldana, John Cho, Anton Yelchin,Sofia Boutella, Idris Elba, Joe Taslim, Jeff Bezos");
        }
    }
}