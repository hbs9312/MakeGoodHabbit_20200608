package kr.co.tjoeun.makegoodhabbit_20200608;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivityProfileBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

public class ProfileActivity extends BaseActivity {


    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        ServerUtil.getRequestUserInfo(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                Log.d("사용자 정보 조회", json.toString());

                try {
                    JSONObject data = json.getJSONObject("data");
                    JSONObject user = data.getJSONObject("user");

                    final String userNickName = user.getString("nick_name");
                    final String userEmail = user.getString("email");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.userNickNameTxt.setText(userNickName);
                            binding.userEmailTxt.setText(userEmail);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }


}
