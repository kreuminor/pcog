package org.jamaica.pcog.mobile.mpage;


import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.jamaica.pcog.mobile.ContactActivity;
import org.jamaica.pcog.mobile.more.MapsActivity;
import org.jamaica.pcog.mobile.R;
import org.jamaica.pcog.mobile.profile.ProfileModelHome;
import org.jamaica.pcog.mobile.resources.Resources;
import org.jamaica.pcog.mobile.social.Facebook;
import org.jamaica.pcog.mobile.social.Twitter;
import org.jamaica.pcog.mobile.social.TwitterFragment;
import org.jamaica.pcog.mobile.social.Youtube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {

    private GridView lvProfilesm;
    private MyAppAdapter myAppAdapter;
    private ArrayList<ProfileModelHome> profileModelArrayList;

    FragmentTransaction fragmentTransaction;

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(org.jamaica.pcog.mobile.R.layout.fragment_more, container, false);

        profileModelArrayList = new ArrayList<>();
        profileModelArrayList.add(new ProfileModelHome("FACEBOOK", "", R.drawable.logo));
        profileModelArrayList.add(new ProfileModelHome("TWITTER", "", R.drawable.logo));
        profileModelArrayList.add(new ProfileModelHome("YOUTUBE", "", R.drawable.logo));
        profileModelArrayList.add(new ProfileModelHome("INSTAGRAM", "", R.drawable.logo));

        lvProfilesm = (GridView) v.findViewById(R.id.grid);
        myAppAdapter = new MyAppAdapter(profileModelArrayList, getActivity());
        lvProfilesm.setAdapter(myAppAdapter);
        lvProfilesm.setOnItemClickListener(new ItemList());

        WebView htmlWebView = (WebView) v.findViewById(R.id.web);
        WebSettings webSetting = htmlWebView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDisplayZoomControls(true);

        String htmlFilename = "mission.html";
        AssetManager mgr = getContext().getAssets();
        try {
            InputStream in = mgr.open(htmlFilename, AssetManager.ACCESS_BUFFER);
            String htmlContentInStringFormat = StreamToString(in);
            in.close();
            htmlWebView.loadDataWithBaseURL(null, htmlContentInStringFormat, "text/html", "utf-8", null);

        } catch (IOException e) {
            e.printStackTrace();
        }

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
                rowView = inflater.inflate(R.layout.grid_single, parent, false);

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
            Glide.with(getActivity()).load(profileList.get(position).getProfilePic()).into(viewHolder.profilePic);

            return rowView;
        }

    }

    private class ItemList implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            if (i == 0) {

                Intent intent = new Intent(getActivity(), Facebook.class);
                startActivity(intent);
                getActivity().finish();

            }
            else if (i == 1) {

                Intent intent = new Intent(getActivity(), Twitter.class);
                startActivity(intent);
                getActivity().finish();
            }

            else if (i == 2) {

                Intent intent = new Intent(getActivity(), Youtube.class);
                startActivity(intent);
                getActivity().finish();
            }

            else if (i == 3) {

                Toast.makeText(getContext(), "Instagram", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public static String StreamToString(InputStream in) throws IOException {
        if (in == null) {
            return "";
        }
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } finally {
        }
        return writer.toString();
    }

}
