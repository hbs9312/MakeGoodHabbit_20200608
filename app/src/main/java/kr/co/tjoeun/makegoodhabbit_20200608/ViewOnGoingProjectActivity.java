package kr.co.tjoeun.makegoodhabbit_20200608;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import androidx.databinding.DataBindingUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjoeun.makegoodhabbit_20200608.Adatpers.ProjectAdapter;
import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivityViewOnGoingProjectBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Project;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ContextUtil;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

public class ViewOnGoingProjectActivity extends BaseActivity {

    ActivityViewOnGoingProjectBinding binding;
    List<Project> projectList = new ArrayList<>();
    ProjectAdapter projectAdapter;

    @Override
    public void setupEvents() {

        binding.onGoingProjectListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final int projectId = projectList.get(position).getId();
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("프로젝트 포기");
                builder.setMessage("정말 이 프로젝트를 포기하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ServerUtil.deleteRequestProject(mContext, projectId, new ServerUtil.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {
                                Log.d("삭제요청", json.toString());
                            }
                        });

                    }
                });
                builder.setNegativeButton("아니오", null);
                builder.show();
                return false;
            }
        });

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        projectList.clear();
//
//        setDataOnUi();
//    }

    @Override
    public void setValues() {
        setTitle("진행중인 프로젝트");

        projectAdapter = new ProjectAdapter(mContext,R.layout.project_list_item,projectList);
        binding.onGoingProjectListView.setAdapter(projectAdapter);

        setDataOnUi();
    }

    void setDataOnUi() {
        projectList.clear();
        ServerUtil.getRequestUserInfo(mContext, "ONGOING", new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                Log.d("전체프로젝트목록", json.toString());

                try {
                    JSONObject data = json.getJSONObject("data");
                    JSONObject projects = data.getJSONObject("projects");
                    JSONArray onGoing = projects.getJSONArray("ONGOING");

                    for(int i=0; i < onGoing.length(); i++) {
                        JSONObject projectObj = onGoing.getJSONObject(i);
                        Project tempProject = Project.getProjectFromJson(projectObj);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_on_going_project);
        setupEvents();
        setValues();
    }
}
