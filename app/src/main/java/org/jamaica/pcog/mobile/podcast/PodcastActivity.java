package org.jamaica.pcog.mobile.podcast;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.jamaica.pcog.mobile.*;

import org.jamaica.pcog.mobile.bible.activities.SearchActivity;
import org.jamaica.pcog.mobile.profile.ProfileModelHome;


import java.util.ArrayList;

public class PodcastActivity extends AppCompatActivity {

    private ListView lvProfilesm;
    private MyAppAdapter myAppAdapter;
    private ArrayList<ProfileModelHome> profileModelArrayList;

    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_podcast);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profileModelArrayList = new ArrayList<>();
        profileModelArrayList.add(new ProfileModelHome("God's Word: Foundation for the Family", "11th June 2017", R.drawable.pod1));
        profileModelArrayList.add(new ProfileModelHome("For Every Blessing, Bless the Lord", "7th May, 2017", R.drawable.pod1));
        profileModelArrayList.add(new ProfileModelHome("Sermon 3", "11th June, 017", R.drawable.pod1));
        profileModelArrayList.add(new ProfileModelHome("Sermon 4", "12th December, 2017", R.drawable.pod1));

        lvProfilesm = (ListView) findViewById(R.id.lvProfilesm);
        myAppAdapter = new MyAppAdapter(profileModelArrayList, getApplicationContext());
        lvProfilesm.setAdapter(myAppAdapter);
        lvProfilesm.setOnItemClickListener(new ItemList());

        //Toolbar

        img = (ImageButton) findViewById(R.id.backbtn);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });

        getSupportActionBar().setTitle("Podcasts");
    }

    public class MyAppAdapter extends BaseAdapter {

        public class ViewHolder {
            TextView username, country;
            ImageView profilePic;

        }

        public ArrayList<ProfileModelHome> profileList;

        public Context context;


        private MyAppAdapter(ArrayList<ProfileModelHome> apps, Context context) {
            this.profileList = apps;
            this.context = context;

        }

        @Override
        public int getCount() {
            return profileList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;
            MyAppAdapter.ViewHolder viewHolder;

            if (rowView == null) {
                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.item_profile, parent, false);

                viewHolder = new MyAppAdapter.ViewHolder();
                viewHolder.profilePic = (ImageView) rowView.findViewById(R.id.imgProfile);
                viewHolder.username = (TextView) rowView.findViewById(R.id.txtUsername);
                viewHolder.country = (TextView) rowView.findViewById(R.id.txtCountry);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (MyAppAdapter.ViewHolder) convertView.getTag();
            }

            viewHolder.username.setText(profileList.get(position).getUsername() + "");
            viewHolder.country.setText(profileList.get(position).getCountry() + "");
            Glide.with(getApplicationContext()).load(profileList.get(position).getProfilePic()).into(viewHolder.profilePic);

            return rowView;
        }

    }

    private class ItemList implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            if (i == 0) {

                Intent intent = new Intent(getApplicationContext(), Sermon1.class);
                startActivity(intent);
                finish();

            } else if (i == 1) {

                Intent intent = new Intent(getApplicationContext(), Sermon2.class);
                startActivity(intent);
                finish();

            } else if (i == 2) {


                Intent intent = new Intent(getApplicationContext(), Sermon3.class);
                startActivity(intent);
                finish();

            }
            else if (i == 3) {

                Intent intent = new Intent(getApplicationContext(), Sermon3.class);
                startActivity(intent);
                finish();

            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), org.jamaica.pcog.mobile.MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.finish();
    }

}
