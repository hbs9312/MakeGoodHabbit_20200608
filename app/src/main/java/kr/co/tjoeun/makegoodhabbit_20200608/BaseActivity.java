package kr.co.tjoeun.makegoodhabbit_20200608;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    Context mContext = this;

    abstract public void setupEvents();
    abstract public void setValues();

    public TextView activityTitleTxt;
    public ImageView logoImg;
    public ImageView profileIcon;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCustomActionBar();
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);

        activityTitleTxt.setText(title);

        activityTitleTxt.setVisibility(View.VISIBLE);
        logoImg.setVisibility(View.GONE);
    }

    public void setCustomActionBar() {


        if(getSupportActionBar() !=null) {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.custom_action_bar);
            getSupportActionBar().setBackgroundDrawable(null);

            androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) getSupportActionBar().getCustomView().getParent();
            toolbar.setContentInsetsAbsolute(0,0);

            activityTitleTxt = getSupportActionBar().getCustomView().findViewById(R.id.activityTitleTxt);
            logoImg = getSupportActionBar().getCustomView().findViewById(R.id.logoImg);
            profileIcon = getSupportActionBar().getCustomView().findViewById(R.id.profileBtn);

            profileIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent myIntent = new Intent(mContext, ProfileActivity.class);
                    startActivity(myIntent);

                }
            });



        }




    }


}
