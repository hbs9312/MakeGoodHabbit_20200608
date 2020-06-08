package kr.co.tjoeun.makegoodhabbit_20200608;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivityMainBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

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
