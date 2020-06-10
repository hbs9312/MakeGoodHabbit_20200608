package kr.co.tjoeun.makegoodhabbit_20200608;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivityProofBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Project;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ContextUtil;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

public class ProofActivity extends BaseActivity {

    ActivityProofBinding binding;
    int projectId;
    Project mProject;

    private final static int PICK_FROM_ALBUM = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_proof);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.uploadImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToAlbum();

            }
        });

        binding.proofBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("프로젝트id", String.valueOf(projectId));

                String content = binding.contentTxt.getText().toString();

                ServerUtil.postRequestProjectProof(mContext, ContextUtil.getUserToken(mContext), projectId, content, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {

                        Log.d("프로젝트인증", json.toString());

                        try {
                            final String message = json.getString("message");
                            int code = json.getInt("code");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                }
                            });
                            if (code == 200) {
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

        projectId = getIntent().getIntExtra("project_id", -1);

        getProjectFromServer();

    }

    void getProjectFromServer() {

        ServerUtil.getRequestProjectById(mContext, projectId, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                Log.d("현재프로젝트", json.toString());

                try {
                    JSONObject data = json.getJSONObject("data");
                    JSONObject projectObj = data.getJSONObject("project");

                    mProject = Project.getProjectFromJson(projectObj);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.projectTitleTxt.setText(mProject.getTitle());
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    void goToAlbum() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri imageUri = data.getData();
        binding.proofImg.setImageURI(imageUri);
    }
}
