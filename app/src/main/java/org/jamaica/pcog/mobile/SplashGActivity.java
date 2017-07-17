package org.jamaica.pcog.mobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import org.jamaica.pcog.mobile.members.MainPage;


public class SplashGActivity extends AwesomeSplash {

    private static final String TAG1 = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_splash);

    }
    @Override
    public void initSplash(ConfigSplash configSplash) {

       // ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set Background Animation

        configSplash.setBackgroundColor(R.color.bg_splash);
        configSplash.setAnimCircularRevealDuration(300);
        configSplash.setRevealFlagX(Flags.WITH_LOGO);
        configSplash.setRevealFlagX(Flags.REVEAL_TOP);

        //Logo

        configSplash.setLogoSplash(R.drawable.locklogo);
        configSplash.setAnimLogoSplashDuration(1300);
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeIn);

        //Title on Splash

        configSplash.setTitleSplash("LOADING...");
        configSplash.setTitleTextColor(R.color.text);
        configSplash.setTitleTextSize(2f);
        configSplash.setAnimTitleDuration(0);
        configSplash.setAnimTitleTechnique(Techniques.FadeIn);

    }

    @Override
    public void animationsFinished() {
    startActivity(new Intent(SplashGActivity.this, MainPage.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

        SplashGActivity.this.finish();

    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

}

