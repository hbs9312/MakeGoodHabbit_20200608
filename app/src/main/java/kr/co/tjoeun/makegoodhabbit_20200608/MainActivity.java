package kr.co.tjoeun.makegoodhabbit_20200608;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjoeun.makegoodhabbit_20200608.Adatpers.ProjectAdapter;
import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivityLoginBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivityMainBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Project;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ContextUtil;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;

    List<Project> projectList = new ArrayList<>();
    ProjectAdapter projectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.projectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Project clickedProject = projectList.get(position);

                Intent intent = new Intent(mContext, ProjectDetailActivity.class);
                intent.putExtra("project_id", clickedProject.getId());
                startActivity(intent);

            }
        });
    }

    @Override
    public void setValues() {

        tedPermission();

        projectAdapter = new ProjectAdapter(mContext, R.layout.project_list_item, projectList);
        binding.projectListView.setAdapter(projectAdapter);

        ServerUtil.getRequestProjects(mContext, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                Log.d("프로젝트응답", json.toString());

                try {
                    JSONObject data= json.getJSONObject("data");

                    JSONArray projects = data.getJSONArray("projects");

                    for(int i=0; i < projects.length(); i++) {

                        JSONObject projectObj = projects.getJSONObject(i);
                        Project tempProject =  Project.getProjectFromJson(projectObj);
                        projectList.add(tempProject);

                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            projectAdapter.notifyDataSetChanged();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void tedPermission() {

        PermissionListener pl = new PermissionListener() {
            @Override
            public void onPermissionGranted() {

            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {

            }
        };

        TedPermission.with(mContext)
                .setPermissionListener(pl)
                .setRationaleMessage(getResources().getString(R.string.permission02))
                .setDeniedMessage(getResources().getString(R.string.permission01))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }
}
