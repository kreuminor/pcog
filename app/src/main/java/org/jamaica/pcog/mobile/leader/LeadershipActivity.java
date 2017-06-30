package org.jamaica.pcog.mobile.leader;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.jamaica.pcog.mobile.MainActivity;
import org.jamaica.pcog.mobile.R;


public class LeadershipActivity extends AppCompatActivity {

    ImageButton img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leadership);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Our Leadership");

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


        ProgressBar prog = (ProgressBar) findViewById(R.id.prog);
        ListView lst_admin = (ListView) findViewById(R.id.lst_admin);

        String[] names = getResources().getStringArray(R.array.admin_names);

        ListAdapter adminAdapter = new OfficeAdapter
                (LeadershipActivity.this, 0, names);

        lst_admin.setAdapter(adminAdapter);

        FadeAnimation f = new FadeAnimation();
        f.start(lst_admin, prog);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        this.finish();
    }
}
