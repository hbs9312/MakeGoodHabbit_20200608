package kr.co.tjoeun.makegoodhabbit_20200608;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;
import org.w3c.dom.Text;

import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivitySignUpBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

public class SignUpActivity extends BaseActivity {

    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {


        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = binding.emailEdt.getText().toString();
                String pw = binding.pwEdt.getText().toString();
                String nickName = binding.nickEdt.getText().toString();

                ServerUtil.putRequestSignUp(mContext, email, pw ,nickName,new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {

                        Log.d("회원가입응답", json.toString());

                    }
                });
            }
        });

        binding.pwEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkSignUpEnable();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.pwRepeatEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkSignUpEnable();
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public void setValues() {

    }

    boolean checkPasswords() {

        boolean isPwOk = false;


        String pw = binding.pwEdt.getText().toString();

        if(pw.length() == 0) {

            binding.pwResultTxt.setText("비밀번호를 입력해주세요.");

        }
        else if(pw.length() < 8) {
            binding.pwResultTxt.setText("비밀번호를 8자 이상 입력해주세요.");
        }
        else {
            binding.pwResultTxt.setText("사용해도 좋은 비밀번호 입니다.");
            isPwOk = true;
        }

        boolean isPwRepeatOk = false;

        String pwRepeat = binding.pwRepeatEdt.getText().toString();

        if(pwRepeat.length() == 0) {

            binding.pwRepeatResultTxt.setText("비밀번호를 한번 더 입력해주세요.");

        }
        else if (pwRepeat.equals(pw)) {

            binding.pwRepeatResultTxt.setText("비밀번호가 확인되었습니다.");
            isPwRepeatOk = true;
        }
        else {
            binding.pwRepeatResultTxt.setText("비밀번호가 서로 다릅니다.");
        }

        return isPwOk && isPwRepeatOk;

    }

    void checkSignUpEnable() {

        boolean isAllPasswordOk = checkPasswords();

        boolean isIdDuplCheckOk = true;


        binding.signUpBtn.setEnabled(isIdDuplCheckOk && isAllPasswordOk);

    }


}
