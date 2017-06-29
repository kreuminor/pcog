package org.jamaica.pcog.mobile.resources;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import org.jamaica.pcog.mobile.ContactActivity;
import org.jamaica.pcog.mobile.MainActivity;
import org.jamaica.pcog.mobile.R;
import org.jamaica.pcog.mobile.about.AboutActivity;
import org.jamaica.pcog.mobile.profile.ProfileModelHome;

import java.util.ArrayList;

public class Resources extends AppCompatActivity {

    private ListView lvResources;
    private MyAppAdapter myAppAdapter;
    private ArrayList<ProfileModelHome> profileModelArrayList;

    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        profileModelArrayList = new ArrayList<>();
        profileModelArrayList.add(new ProfileModelHome("Church of God (International)", "http://www.jesusisthesubject.org/", R.drawable.phoneee));
        profileModelArrayList.add(new ProfileModelHome("Church of God (National)", "http://churchofgodinjamaica.org/", R.drawable.phoneee));
        profileModelArrayList.add(new ProfileModelHome("Centennial Annual General Assembly Highlights", "http://scog-assembly100.weebly.com/", R.drawable.phoneee));
        profileModelArrayList.add(new ProfileModelHome("COG Constitution and by Laws", "http://churchofgodinjamaica.org/constitution.pdf/", R.drawable.webicon));
        profileModelArrayList.add(new ProfileModelHome("Ardenne High School", "http://www.ardennehigh.com/", R.drawable.emaile));
        profileModelArrayList.add(new ProfileModelHome("Ardenne Preparatory and Ext High School", "http://www.ardenneprepextension.com/", R.drawable.emaile));

        lvResources = (ListView) findViewById(R.id.lblist);
        myAppAdapter = new MyAppAdapter(profileModelArrayList, getApplicationContext());
        lvResources.setAdapter(myAppAdapter);
        lvResources.setOnItemClickListener(new ItemList());

        // Toolbar
        // add back arrow to toolbar
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

        getSupportActionBar().setTitle("Resources");
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
                rowView = inflater.inflate(R.layout.list_single1, parent, false);

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

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.jesusisthesubject.org/"));
                startActivity(intent);
            }

            else if (i == 1) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://churchofgodinjamaica.org/"));
                startActivity(intent);

            }
            else if (i == 2) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://scog-assembly100.weebly.com/"));
                startActivity(intent);



            }
            else if (i == 3) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://churchofgodinjamaica.org/app/webroot/files/Constitution%20&%20By-Laws_%20CoG.pdf"));
                startActivity(intent);


            }
            else if (i == 4) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.ardennehigh.com/"));
                startActivity(intent);

            }
            else if (i == 5) {

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.ardenneprepextension.com/LkgaZ/home"));
                startActivity(intent);

            }
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
