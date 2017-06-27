package org.jamaica.pcog.mobile.mpage;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jamaica.pcog.mobile.R;
import org.jamaica.pcog.mobile.adapter.Events;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    ArrayList<Events> al;
    MyAdapter adp;
    ListView lv;
    Button btn;
    Myfragment myfragment;

    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(org.jamaica.pcog.mobile.R.layout.fragment_events, container, false);

        lv= (ListView) v.findViewById(R.id.listView);
        al=new ArrayList<Events>();
        adp=new MyAdapter();
        lv.setAdapter(adp);

        Myservice m = new Myservice();
        m.execute();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //GET DATA BASED ON POSITION
                Events obj= al.get(position);

                //FIND IF FRAGMENT IS AVAIABLE OR NOT
                myfragment=(Myfragment)getFragmentManager().findFragmentById(R.id.fragment1);
                //IF AVAILABLE PASS DATA TO FRAGMENT

                if(myfragment !=null){

                    myfragment.myMethod(obj);
                }
            }
        });
        return v;
    }
    private class Myservice extends AsyncTask<Void,Void,String >{


        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection con;
            //sTEP 3 CREATE URL CONNECCTION AND IMPEETS TRY CATCH ....AS..
            try{
                URL url  = new URL("https://quarkbackend.com/getfile/colin-holness/event");
                con=(HttpURLConnection)url.openConnection();

                // lIKE THS ALSO WE CAN READ BUT ONELINE CODE IS SO..

                /*InputStream is=con.getInputStream();
                InputStreamReader isr=new InputStreamReader(is);
                BufferedReader br=new BufferedReader(isr);*/

                BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb=new StringBuilder();
                String s=br.readLine();
                while(s!= null){
                    sb.append(s);
                    s=br.readLine();
                }
                return sb.toString();

            }catch(MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject obj= new JSONObject(s);
                JSONArray events=obj.getJSONArray("events");
                for(int i=1;i<events.length();i++){
                    JSONObject event=events.getJSONObject(i);
                    String name=event.getString("name");
                    String month=event.getString("month");
                    String day =event.getString("day");
                    String date =event.getString("date");
                    String description=event.getString("description");
                    String email=event.getString("email");

                    Events obj1=new Events();
                    obj1.setName(name);
                    obj1.setMonth(month);
                    obj1.setDay(day);
                    obj1.setDate(date);
                    obj1.setDescription(description);
                    obj1.setEmail(email);

                    al.add(obj1);
                }
                adp.notifyDataSetChanged();

            }catch(JSONException e){
                e.printStackTrace();
            }

            super.onPostExecute(s);
        }
    }
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return al.size();
        }

        @Override
        public Object getItem(int position) {
            return al.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            View v= getLayoutInflater(null).inflate(R.layout.rowa,null);
            TextView Ltxt= (TextView) v.findViewById(R.id.textView);
            TextView mtxt= (TextView) v.findViewById(R.id.textView2);
            TextView stxt= (TextView) v.findViewById(R.id.textView3);
//STEP 6 AFTER THE INTILIZING CREATE AN VARAIBLE  FOR CONTACT CLASS AND ASSIGN IT WITH THE ARRAYLIST OF POSITION
            // GET ALL THE DATA FROMTHE CONACTS BCZ WE HAVE ALL READY IMPLEDMEMTED GETTERS AND SETTERS  CHECK IT OK
            Events contac=al.get(position);
            String name=contac.getName();
            String month=contac.getMonth();
            String day=contac.getDay();

            Ltxt.setText(name);
            mtxt.setText(month);
            stxt.setText(day);

            return v;

        }

    }

}
