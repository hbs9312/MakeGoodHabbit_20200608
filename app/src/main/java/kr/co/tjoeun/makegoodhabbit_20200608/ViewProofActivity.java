package kr.co.tjoeun.makegoodhabbit_20200608;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.co.tjoeun.makegoodhabbit_20200608.Adatpers.ProofAdapter;
import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivityViewProofBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Proof;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

public class ViewProofActivity extends BaseActivity {

    ActivityViewProofBinding binding;
    int projectId;
    List<Proof> proofList = new ArrayList<>();
    ProofAdapter prAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_proof);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            binding.datePick.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    proofList.clear();
                    String dateStr = String.format("%d-%d-%d", year,monthOfYear+1,dayOfMonth);

                    Log.d("날짜str", dateStr);

                    ServerUtil.getRequestProjectByDate(mContext, projectId, dateStr, new ServerUtil.JsonResponseHandler() {
                        @Override
                        public void onResponse(JSONObject json) {

                            Log.d("날짜별인증내역", json.toString());

                            try {
                                JSONObject data = json.getJSONObject("data");
                                JSONObject project = data.getJSONObject("project");
                                JSONArray proofArr = project.getJSONArray("proofs");

                                for(int i=0; i<proofArr.length();i++) {
                                    JSONObject proofObj = proofArr.getJSONObject(i);
                                    Proof tempProof = Proof.getProofFromJson(proofObj);
                                    proofList.add(tempProof);
                                }
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        prAdapter.notifyDataSetChanged();
                                    }
                                });

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    });

                }
            });
        }

    }

    @Override
    public void setValues() {

        projectId = getIntent().getIntExtra("projectId", -1);

        prAdapter = new ProofAdapter(mContext,R.layout.proof_list_item, proofList);
        binding.proofByDateListView.setAdapter(prAdapter);

    }
}
