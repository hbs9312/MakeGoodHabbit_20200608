package kr.co.tjoeun.makegoodhabbit_20200608;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivityProjectDetailBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Project;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

public class ProjectDetailActivity extends BaseActivity {

    int projectId;
    Project mProject;

    ActivityProjectDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_project_detail);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.showProofBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, ViewProofActivity.class);
                intent.putExtra("projectId", mProject.getId());
                startActivity(intent);

            }
        });

        binding.goToProofBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProofActivity.class);
                intent.putExtra("project_id", mProject.getId());
                startActivity(intent);
            }
        });

        binding.applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ServerUtil.postRequestJoin(mContext, projectId, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {

                        Log.d("프로젝트신청", json.toString());
                        try {
                            JSONObject data = json.getJSONObject("data");
                            String message = json.getString("message");

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

        projectId = getIntent().getIntExtra("project_id", -1);

        if(projectId == -1) {
            Toast.makeText(mContext, "잘못된 접급입니다.", Toast.LENGTH_SHORT).show();
            finish();
        }

        getProjectFromSever();


    }

    void getProjectFromSever() {

        ServerUtil.getRequestProjectById(mContext, projectId, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                Log.d("상세화면", json.toString());

                try {
                    JSONObject data = json.getJSONObject("data");
                    JSONObject project = data.getJSONObject("project");

                    mProject = Project.getProjectFromJson(project);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            setProjectValueOnUi();
                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    void setProjectValueOnUi() {

        binding.projectTitleTxt.setText(mProject.getTitle());
        Glide.with(mContext).load(mProject.getImgUrl()).into(binding.projectImg);

        binding.contentTxt.setText(mProject.getDescription());

    }
}
