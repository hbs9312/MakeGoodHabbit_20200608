package kr.co.tjoeun.makegoodhabbit_20200608;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjoeun.makegoodhabbit_20200608.Adatpers.ReplyAdapter;
import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivityViewReplyBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Reply;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

public class ViewReplyActivity extends BaseActivity{

    ActivityViewReplyBinding binding;
    int proofId;
    List<Reply> replyList = new ArrayList<>();
    ReplyAdapter replyAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_reply);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.postReplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, PostReplyActivity.class);
                intent.putExtra("proofId", proofId);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        replyList.clear();
        getProofDetailFromServer();

    }

    @Override
    public void setValues() {
        proofId = getIntent().getIntExtra("proofId", -1);

        replyAdapter = new ReplyAdapter(mContext,R.layout.reply_list_item, replyList);
        binding.replyListView.setAdapter(replyAdapter);


    }

    void getProofDetailFromServer() {

        ServerUtil.getRequestProofDetail(mContext, proofId, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                Log.d("인증글상세조회", json.toString());

                try {
                    JSONObject data = json.getJSONObject("data");
                    JSONObject project = data.getJSONObject("project");
                    JSONArray replies = project.getJSONArray("replies");

                    Log.d("댓글목록확인", replies.toString());

                    for(int i=0;i<replies.length();i++) {
                        JSONObject replyObj = replies.getJSONObject(i);
                        Reply tempReply = Reply.getReplyFromJson(replyObj);
                        replyList.add(tempReply);

                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            replyAdapter.notifyDataSetChanged();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
