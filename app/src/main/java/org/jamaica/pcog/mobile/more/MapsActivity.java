package org.jamaica.pcog.mobile.more;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.jamaica.pcog.mobile.ContactActivity;
import org.jamaica.pcog.mobile.MainActivity;
import org.jamaica.pcog.mobile.R;
import org.jamaica.pcog.mobile.about.AboutActivity;
import org.jamaica.pcog.mobile.profile.ProfileModelHome;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    ImageButton img;
    private ListView lvProfilesm;
    private MyAppAdapter myAppAdapter;
    private ArrayList<ProfileModelHome> profileModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Contact Us");

        // add back arrow to toolbar
        img = (ImageButton) findViewById(R.id.backbtn);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        });


        profileModelArrayList = new ArrayList<>();
        profileModelArrayList.add(new ProfileModelHome("(876) 988-1857 ", "Main Office", R.drawable.phoneee));
        profileModelArrayList.add(new ProfileModelHome("www.google.com ", "Website", R.drawable.webicon));
        profileModelArrayList.add(new ProfileModelHome("Email Us ", "portmorechurchogod@gmail.com", R.drawable.emaile));

        lvProfilesm = (ListView) findViewById(R.id.lblist);
        myAppAdapter = new MyAppAdapter(profileModelArrayList, getApplicationContext());
        lvProfilesm.setAdapter(myAppAdapter);
        lvProfilesm.setOnItemClickListener(new ItemList());
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng pcog = new LatLng(17.956939, -76.874540);
        mMap.addMarker(new MarkerOptions().position(pcog).title("Church of God in Jamaica, Portmore").snippet("58A Barbara Ave., Edgewater, Portmore"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pcog));
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(16.0f));

        CameraPosition me = CameraPosition.builder().target(new LatLng(17.956939, -76.874540)).zoom(17).build();
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(me));

        googleMap.getUiSettings().setScrollGesturesEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        googleMap.setPadding(0, 0, 0, 0);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.finish();
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
                rowView = inflater.inflate(R.layout.list_single, parent, false);

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

                ViewGroup vg = (ViewGroup) view;
                TextView tv = (TextView) vg.findViewById(R.id.txtUsername);
                Toast.makeText(getApplicationContext(), tv.getText().toString(), Toast.LENGTH_SHORT).show();


                try{
                    Intent phoneDialerIntent= new Intent(Intent.ACTION_DIAL);
                    phoneDialerIntent.setData(Uri.parse("tel:" + tv.getText().toString()));
                    startActivity(phoneDialerIntent);
                }
                catch (Exception e)
                {
                }
            }
            else if (i == 1) {
                ViewGroup vg = (ViewGroup) view;
                TextView tv = (TextView) vg.findViewById(R.id.txtUsername);
                Toast.makeText(getApplicationContext(), tv.getText().toString(), Toast.LENGTH_SHORT).show();


                try{
                    Intent phoneDialerIntent= new Intent(Intent.ACTION_DIAL);
                    phoneDialerIntent.setData(Uri.parse("tel:" + tv.getText().toString()));
                    startActivity(phoneDialerIntent);
                }
                catch (Exception e)
                {
                }

            }
            else if (i == 2) {
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"portmorechurchofgod@gmail.com"});  //Heart Trust 's email
                Email.putExtra(Intent.EXTRA_SUBJECT,
                        "Add your Subject"); // Email 's Subject
                Email.putExtra(Intent.EXTRA_TEXT, "Dear PCOG," + "");  //Email 's Greeting text
                startActivity(Intent.createChooser(Email, "Send Feedback/Query:"));
            }

        }
    }
}
