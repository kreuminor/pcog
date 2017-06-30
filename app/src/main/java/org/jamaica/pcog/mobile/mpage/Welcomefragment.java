package org.jamaica.pcog.mobile.mpage;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.jamaica.pcog.mobile.R;
import org.jamaica.pcog.mobile.announcement.InboxActivity;
import org.jamaica.pcog.mobile.podcast.PodcastActivity;
import org.jamaica.pcog.mobile.profile.ProfileModelHome;
import org.jamaica.pcog.mobile.resources.Resources;
import org.jamaica.pcog.mobile.time.TimesActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Welcomefragment extends Fragment {

    private ListView lvProfilesm;
    private MyAppAdapter myAppAdapter;
    private ArrayList<ProfileModelHome> profileModelArrayList;

    CarouselView carouselView;

    int[] sampleImages = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3, R.drawable.image_4, R.drawable.image_5};


    public Welcomefragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(org.jamaica.pcog.mobile.R.layout.fragment_welcomefragment, container, false);

        carouselView = (CarouselView) v.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);


        profileModelArrayList = new ArrayList<>();
        profileModelArrayList.add(new ProfileModelHome("Announcements", "?", R.drawable.logo1));
        profileModelArrayList.add(new ProfileModelHome("Order of Service", "", R.drawable.logo1));
        profileModelArrayList.add(new ProfileModelHome("Podcast", "", R.drawable.logo1));
        profileModelArrayList.add(new ProfileModelHome("Resources", "", R.drawable.logo1));


        lvProfilesm = (ListView) v.findViewById(R.id.lvProfilesm);
        myAppAdapter = new MyAppAdapter(profileModelArrayList, getContext());
        lvProfilesm.setAdapter(myAppAdapter);
        lvProfilesm.setOnItemClickListener(new ItemList());


        return v;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };


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
                LayoutInflater inflater = getLayoutInflater(null);
                rowView = inflater.inflate(R.layout.item_profile3, parent, false);

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
            Glide.with(getContext()).load(profileList.get(position).getProfilePic()).into(viewHolder.profilePic);

            return rowView;
        }

    }

    private class ItemList implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            if (i == 0) {

                Intent intent = new Intent(getActivity(), InboxActivity.class);
                startActivity(intent);
                getActivity().finish();

            } else if (i == 1) {

                Intent intent = new Intent(getActivity(), TimesActivity.class);
                startActivity(intent);
                getActivity().finish();

            } else if (i == 2) {

                Intent intent = new Intent(getActivity(), PodcastActivity.class);
                startActivity(intent);
                getActivity().finish();

            }
            else if (i == 3) {

                Intent intent = new Intent(getActivity(), Resources.class);
                startActivity(intent);
                getActivity().finish();

            }
        }
    }
}