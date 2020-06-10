package kr.co.tjoeun.makegoodhabbit_20200608;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    Calendar cal = Calendar.getInstance();
    int mYear;
    int mMonth;
    int mDay;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_proof);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.selectDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYear = cal.get(Calendar.YEAR);
                mMonth = cal.get(Calendar.MONTH);
                mDay = cal.get(Calendar.DAY_OF_MONTH);
                proofList.clear();
                showDate();
            }
        });
    }

    @Override
    public void setValues() {
        projectId = getIntent().getIntExtra("projectId", -1);


        getDateFromSever(sdf.format(cal.getTime()));

        prAdapter = new ProofAdapter(mContext,R.layout.proof_list_item, proofList);
        binding.proofByDateListView.setAdapter(prAdapter);

    }

    void showDate(){

        DatePickerDialog dialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);
                String dateStr = sdf.format(selectedDate.getTime());

                getDateFromSever(dateStr);

            }
        }, mYear, mMonth, mDay);
        dialog.show();

    }

    void getDateFromSever(String date) {

        ServerUtil.getRequestProjectByDate(mContext, projectId, date, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {

                Log.d("날짜별인증조회", json.toString());

                try {
                    JSONObject data = json.getJSONObject("data");
                    JSONObject project = data.getJSONObject("project");
                    JSONArray proofs = project.getJSONArray("proofs");

                    for(int i=0;i<proofs.length();i++) {

                        JSONObject proofObj = proofs.getJSONObject(i);
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
}
