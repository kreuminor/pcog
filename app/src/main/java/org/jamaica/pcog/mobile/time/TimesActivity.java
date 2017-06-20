package org.jamaica.pcog.mobile.time;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import org.jamaica.pcog.mobile.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class TimesActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageButton img;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(org.jamaica.pcog.mobile.R.layout.activity_times);

        Toolbar toolbar = (Toolbar) findViewById(org.jamaica.pcog.mobile.R.id.toolbar);
        toolbar.setTitle("Order of Service");
        setSupportActionBar(toolbar);

        ViewPager viewPager = (ViewPager) findViewById(org.jamaica.pcog.mobile.R.id.viewpager);
        if (viewPager != null) {
            setupViewPager(viewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(org.jamaica.pcog.mobile.R.id.tabs);
        if (viewPager != null) {
            tabLayout.setupWithViewPager(viewPager);
        }

        img = (ImageButton) findViewById(org.jamaica.pcog.mobile.R.id.img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                overridePendingTransition(org.jamaica.pcog.mobile.R.anim.fade_in, org.jamaica.pcog.mobile.R.anim.fade_out);
                finish();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new TimesFull(), getString(org.jamaica.pcog.mobile.R.string.fullday));
        adapter.addFragment(new TimesHalf(), getString(org.jamaica.pcog.mobile.R.string.halfday));
        adapter.addFragment(new TimesLate(), getString(org.jamaica.pcog.mobile.R.string.latestart));
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        overridePendingTransition(org.jamaica.pcog.mobile.R.anim.fade_in, org.jamaica.pcog.mobile.R.anim.fade_out);

        this.finish();
    }
}
