package org.jamaica.pcog.mobile.about;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import org.jamaica.pcog.mobile.ContactActivity;
import org.jamaica.pcog.mobile.MainActivity;
import org.jamaica.pcog.mobile.R;
import org.jamaica.pcog.mobile.about.activity.HistoryActivity;
import org.jamaica.pcog.mobile.about.activity.MissionActivity;
import org.jamaica.pcog.mobile.about.activity.VisionActivity;
import org.jamaica.pcog.mobile.leader.LeadershipActivity;
import org.jamaica.pcog.mobile.more.MapsActivity;
import org.jamaica.pcog.mobile.resources.Affiliates;

import java.util.ArrayList;
import java.util.List;



public class AboutActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<AboutItem> mAboutItems = new ArrayList<AboutItem>();

    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        img = (ImageButton)findViewById(R.id.backbtn);

        if (mToolbar != null) {

            setSupportActionBar(mToolbar);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        }
        getSupportActionBar().setTitle("About Us");

        ListView mListView = (ListView) findViewById(R.id.listView);
        mListView.setOnItemClickListener(this);
        generateList();
        AboutAdapter mAdapter = new AboutAdapter(this, 0x00, mAboutItems);
        mListView.setAdapter(mAdapter);
    }

    private void generateList() {
        mAboutItems.clear();
        mAboutItems.add(new AboutItem(getString(R.string.aff),
                getString(R.string.aff_text),
                R.drawable.logo1));
        mAboutItems.add(new AboutItem(getString(R.string.history),
                getString(R.string.history_text),
                R.drawable.logo1));
        mAboutItems.add(new AboutItem(getString(R.string.leadership),
                getString(R.string.leadership_text),
                R.drawable.logo1));
        mAboutItems.add(new AboutItem(getString(R.string.mission),
                getString(R.string.mission_text),
                R.drawable.logo1));
        mAboutItems.add(new AboutItem(getString(R.string.vision),
                getString(R.string.vision_text),
                R.drawable.logo1));
        mAboutItems.add(new AboutItem(getString(R.string.contact),
                getString(R.string.contact_text),
                R.drawable.logo1));


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0: //Launch Navigation to the conference site
                Intent i = new Intent(getApplicationContext(),Affiliates.class);
                finish();
                startActivity(i);
                break;
            case 1: // Display the Open Source projects used for this application
                Intent i1 = new Intent(getApplicationContext(),HistoryActivity.class);
                finish();
                startActivity(i1);
                break;
            case 2: // Display the Open Source projects used for this application
                Intent i2 = new Intent(getApplicationContext(),LeadershipActivity.class);
                finish();
                startActivity(i2);
                break;
            case 3: // Display the Open Source projects used for this application
                Intent i3 = new Intent(getApplicationContext(),MissionActivity.class);
                finish();
                startActivity(i3);
                break;
            case 4: // Display the Open Source projects used for this application
                Intent i4 = new Intent(getApplicationContext(),VisionActivity.class);
                finish();
                startActivity(i4);
                break;
            case 5: // Display the Open Source projects used for this application
                Intent i5 = new Intent(getApplicationContext(),MapsActivity.class);
                finish();
                startActivity(i5);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.finish();
    }
}
