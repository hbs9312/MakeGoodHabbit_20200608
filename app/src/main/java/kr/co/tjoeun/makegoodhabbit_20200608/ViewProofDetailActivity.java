package kr.co.tjoeun.makegoodhabbit_20200608;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjoeun.makegoodhabbit_20200608.Adatpers.ReplyAdapter;
import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivityViewProofDetailBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Reply;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

public class ViewProofDetailActivity extends BaseActivity {

    ActivityViewProofDetailBinding binding;
    int proofId;
    String projectTitle;
    List<Reply> replyList = new ArrayList<>();
    ReplyAdapter replyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_proof_detail);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    protected void onResume() {
        super.onResume();

        getProofDetailFromServer();
    }

    @Override
    public void setValues() {

        proofId = getIntent().getIntExtra("proofId",-1);
        projectTitle = getIntent().getStringExtra("projectTitle");
        Log.d("proofid 확인", String.valueOf(proofId));

        setTitle(projectTitle);

        String projectTitle = getIntent().getStringExtra("projectTitle");
//        binding.projectTitleTxt.setText(projectTitle);

        replyAdapter = new ReplyAdapter(mContext, R.layout.reply_list_item, replyList);
        binding.proofReplyListView.setAdapter(replyAdapter);

    }

    void getProofDetailFromServer() {

        ServerUtil.getRequestProofDetail(mContext, proofId, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                Log.d("인증상세목록조회", json.toString());
                replyList.clear();

                try {
                    JSONObject data = json.getJSONObject("data");
                    JSONObject project = data.getJSONObject("project");
                    JSONArray replies = project.getJSONArray("replies");
                    JSONArray images = project.getJSONArray("images");
                    final String proofContent = project.getString("content");

                    Log.d("댓글목록확인", replies.toString());

                    for(int i=0; i<replies.length();i++) {

                        JSONObject replyObj = replies.getJSONObject(i);
                        Reply tempReply = Reply.getReplyFromJson(replyObj);
                        replyList.add(tempReply);

                    }
                    JSONObject firstImage = images.getJSONObject(0);
                    final String imgUrl = firstImage.getString("img_url");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.proofContentTxt.setText(proofContent);
                            Glide.with(mContext).load(imgUrl).into(binding.proofImg);
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
