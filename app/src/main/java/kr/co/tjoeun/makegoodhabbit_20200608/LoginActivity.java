package kr.co.tjoeun.makegoodhabbit_20200608;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivityLoginBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ContextUtil;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

public class LoginActivity extends BaseActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.autoLoginCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ContextUtil.setIsAutoLogin(mContext, isChecked);
            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = binding.emailEdt.getText().toString();
                String pw = binding.pwEdt.getText().toString();

                ServerUtil.postRequestLogin(mContext, email, pw, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {

                        Log.d("로그인응답", json.toString());

                        try {
                            int code = json.getInt("code");

                            if(code == 200) {

                                JSONObject data = json.getJSONObject("data");
                                String token = data.getString("token");

                                ContextUtil.setUserToken(mContext, token);

                                Intent intent = new Intent(mContext, MainActivity.class);
                                startActivity(intent);
                                finish();


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                });

            }
        });

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, SignUpActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void setValues() {

    }
}
