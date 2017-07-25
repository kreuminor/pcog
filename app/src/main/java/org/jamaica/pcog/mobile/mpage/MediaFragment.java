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

import org.jamaica.pcog.mobile.R;
import org.jamaica.pcog.mobile.podcast.PodcastActivity;
import org.jamaica.pcog.mobile.podcast.Sermon1;
import org.jamaica.pcog.mobile.podcast.Sermon2;
import org.jamaica.pcog.mobile.podcast.Sermon3;
import org.jamaica.pcog.mobile.profile.ProfileModelHome;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MediaFragment extends Fragment {

    private ListView lvProfilesm;
    private MyAppAdapter myAppAdapter;
    private ArrayList<ProfileModelHome> profileModelArrayList;

    public MediaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_media, container, false);

        profileModelArrayList = new ArrayList<>();
        profileModelArrayList.add(new ProfileModelHome("God's Word: Foundation for the Family", "11th June 2017", R.drawable.pod1));
        profileModelArrayList.add(new ProfileModelHome("For Every Blessing, Bless the Lord", "7th May, 2017", R.drawable.pod1));


        lvProfilesm = (ListView) v.findViewById(R.id.lvProfilesm);
        myAppAdapter = new MyAppAdapter(profileModelArrayList, getContext());
        lvProfilesm.setAdapter(myAppAdapter);
        lvProfilesm.setOnItemClickListener(new ItemList());



        return v;
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
                LayoutInflater inflater = getLayoutInflater(null);
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
            Glide.with(getContext()).load(profileList.get(position).getProfilePic()).into(viewHolder.profilePic);

            return rowView;
        }

    }

    private class ItemList implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            if (i == 0) {

                Intent intent = new Intent(getContext(), Sermon1.class);
                startActivity(intent);
                getActivity().finish();

            } else if (i == 1) {

                Intent intent = new Intent(getContext(), Sermon2.class);
                startActivity(intent);
                getActivity().finish();


            }
        }
    }

}
