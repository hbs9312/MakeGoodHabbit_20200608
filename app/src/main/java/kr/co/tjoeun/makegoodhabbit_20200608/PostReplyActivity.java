package kr.co.tjoeun.makegoodhabbit_20200608;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivityPostReplyBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

public class PostReplyActivity extends BaseActivity {

    ActivityPostReplyBinding binding;
    int proofId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_post_reply);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        binding.postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String contentTxt =binding.replyContentEdt.getText().toString();

                ServerUtil.postRequestReply(mContext, contentTxt, proofId, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        Log.d("댓글남기기응답", json.toString());

                        try {
                            int code = json.getInt("code");
                            if(code == 200) {
                                finish();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

    }

    @Override
    public void setValues() {
        proofId = getIntent().getIntExtra("proofId", -1 );
    }
}
