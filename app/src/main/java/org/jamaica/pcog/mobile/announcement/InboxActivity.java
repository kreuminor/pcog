package org.jamaica.pcog.mobile.announcement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.jamaica.pcog.mobile.MainActivity;
import org.jamaica.pcog.mobile.R;
import org.jamaica.pcog.mobile.announcement.model.InboxModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class InboxActivity extends ActionBarActivity {

    private final String URL_TO_HIT = "https://drive.google.com/uc?export=download&id=0B1VzuZrVMPLNclg5OWQ3OUdTV2s";
    private TextView tvData;
    private ListView lvInbox;
    private ProgressDialog dialog;
    ImageButton img;
    FloatingActionButton fab_email;

    // Git error fix - http://stackoverflow.com/questions/16614410/android-studio-checkout-github-error-createprocess-2-windows

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        getSupportActionBar().setTitle("Announcements");

        // Floating Action Button
        fab_email = (FloatingActionButton)findViewById(R.id.fab_email);

        fab_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Email = new Intent(Intent.ACTION_SEND);
                Email.setType("text/email");
                Email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"portmorechurchofgod@gmail.com"});  //PCOG's email
                Email.putExtra(Intent.EXTRA_SUBJECT,
                        "Add your Subject"); // Email 's Subject
                Email.putExtra(Intent.EXTRA_TEXT, "Dear PCOG PR," + "");  //Email 's Greeting text
                startActivity(Intent.createChooser(Email, "Send Request:"));
            }
        });


        dialog = new ProgressDialog(this);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.setMessage("Loading. Please wait..."); // showing a dialog for loading the data
        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
        .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .defaultDisplayImageOptions(defaultOptions)
        .build();
        ImageLoader.getInstance().init(config); // Do it on Application start

        lvInbox = (ListView)findViewById(R.id.lvInbox);


        // To start fetching the data when app start, uncomment below line to start the async task.
                new JSONTask().execute(URL_TO_HIT);
    }

    public class JSONTask extends AsyncTask<String,String, List<InboxModel> > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
        }

        @Override
        protected List<InboxModel> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line ="";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                String finalJson = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJson);
                JSONArray parentArray = parentObject.getJSONArray("programmes");

                List<InboxModel> inboxModelList = new ArrayList<>();

                Gson gson = new Gson();
                for(int i=0; i<parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);
                    /**
                     * below single line of code from Gson saves you from writing the json parsing yourself
                     * which is commented below
                      */
                    InboxModel InboxModel = gson.fromJson(finalObject.toString(), InboxModel.class); // a single line json parsing using Gson
//                    InboxModel.setMovie(finalObject.getString("movie"));
//                    InboxModel.setYear(finalObject.getInt("year"));
//                    InboxModel.setRating((float) finalObject.getDouble("rating"));
//                    InboxModel.setDirector(finalObject.getString("director"));
//
//                    InboxModel.setDuration(finalObject.getString("duration"));
//                    InboxModel.setTagline(finalObject.getString("tagline"));
//                    InboxModel.setImage(finalObject.getString("image"));
//                    InboxModel.setStory(finalObject.getString("story"));
//
//                    List<InboxModel.Cast> castList = new ArrayList<>();
//                    for(int j=0; j<finalObject.getJSONArray("cast").length(); j++){
//                        InboxModel.Cast cast = new InboxModel.Cast();
//                        cast.setName(finalObject.getJSONArray("cast").getJSONObject(j).getString("name"));
//                        castList.add(cast);
//                    }
//                    InboxModel.setCastList(castList);
                    // adding the final object in the list
                    inboxModelList.add(InboxModel);
                }
                return inboxModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection != null) {
                    connection.disconnect();
                }
                try {
                    if(reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return  null;
        }

        @Override
        protected void onPostExecute(final List<InboxModel> result) {
            super.onPostExecute(result);
            dialog.dismiss();
            if(result != null) {
                MovieAdapter adapter = new MovieAdapter(getApplicationContext(), R.layout.row, result);
                lvInbox.setAdapter(adapter);
                lvInbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {  // list item click opens a new detailed activity
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        InboxModel InboxModel = result.get(position); // getting the model
                        Intent intent = new Intent(InboxActivity.this, DetailActivity.class);
                        intent.putExtra("InboxModel", new Gson().toJson(InboxModel)); // converting model json into string type and sending it via intent
                        startActivity(intent);
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
            }
        }
    }



    public class MovieAdapter extends ArrayAdapter {

        private List<InboxModel> inboxModelList;
        private int resource;
        private LayoutInflater inflater;
        public MovieAdapter(Context context, int resource, List<InboxModel> objects) {
            super(context, resource, objects);
            inboxModelList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if(convertView == null){
                holder = new ViewHolder();
                convertView = inflater.inflate(resource, null);

                holder.inboxTitle = (TextView)convertView.findViewById(R.id.pTitle);
                holder.subject = (TextView)convertView.findViewById(R.id.pSubject);
                holder.date = (TextView)convertView.findViewById(R.id.pDate);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }



            // Then later, when you want to display image
            final ViewHolder finalHolder = holder;



            holder.inboxTitle.setText(inboxModelList.get(position).getTitle());
            holder.subject.setText(inboxModelList.get(position).getSubject());
            holder.date.setText(inboxModelList.get(position).getDate());

            // rating bar
            //holder.rbMovieRating.setRating(inboxModelList.get(position).getRating()/2);

            StringBuffer stringBuffer = new StringBuffer();


            return convertView;
        }


        class ViewHolder{
            private TextView inboxTitle;
            private TextView subject;
            private TextView date;

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_course, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            new JSONTask().execute(URL_TO_HIT);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.finish();
    }
}
