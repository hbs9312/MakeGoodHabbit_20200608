package kr.co.tjoeun.makegoodhabbit_20200608;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

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

            ServerUtil.getRequestUserInfo(mContext, new ServerUtil.JsonResponseHandler() {
                @Override
                public void onResponse(JSONObject json) {

                    Log.d("사용자 정보 조회", json.toString());

                    try {
                        JSONObject data = json.getJSONObject("data");
                        JSONObject user = data.getJSONObject("user");

                        JSONArray profileImages = user.getJSONArray("profile_images");
                        JSONObject profileImageObj = profileImages.getJSONObject(0);
                        final String profileimgUrl = profileImageObj.getString("img_url");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Glide.with(mContext).load(profileimgUrl).into(profileIcon);
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });


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
