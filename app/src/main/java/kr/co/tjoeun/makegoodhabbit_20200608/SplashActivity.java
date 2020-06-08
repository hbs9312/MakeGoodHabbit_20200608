package kr.co.tjoeun.makegoodhabbit_20200608;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);

            }
        },2000);


    }
}
