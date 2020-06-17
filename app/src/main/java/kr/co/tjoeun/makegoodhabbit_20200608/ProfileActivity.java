package kr.co.tjoeun.makegoodhabbit_20200608;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivityProfileBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ContextUtil;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

public class ProfileActivity extends BaseActivity {


    ActivityProfileBinding binding;

    private final static int PICK_FROM_ALBUM = 2000;
    File selectedFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_profile);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.showOnGoingProjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(mContext, ViewOnGoingProjectActivity.class);
                startActivity(myIntent);

            }
        });

        binding.patchProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToAlbum();

            }
        });

    }

    @Override
    public void setValues() {

        profileIcon.setVisibility(View.INVISIBLE);

        ServerUtil.getRequestUserInfo(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                Log.d("사용자 정보 조회", json.toString());

                try {
                    JSONObject data = json.getJSONObject("data");
                    JSONObject user = data.getJSONObject("user");

                    final String userNickName = user.getString("nick_name");
                    final String userEmail = user.getString("email");

                    JSONArray profileImages = user.getJSONArray("profile_images");
                    JSONObject profileImageObj = profileImages.getJSONObject(0);
                    final String profileimgUrl = profileImageObj.getString("img_url");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            binding.userNickNameTxt.setText(userNickName);
                            binding.userEmailTxt.setText(userEmail);
                            Glide.with(mContext).load(profileimgUrl).into(binding.profileImg);
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
        selectedFile = new File(getRealPathFromURI(imageUri));

        ServerUtil.putRequestUserPoflieImage(mContext, ContextUtil.getUserToken(mContext), selectedFile, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                Log.d("프로필등록", json.toString());

            }
        });
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx); cursor.close();
        }
        return result;

    }

}
