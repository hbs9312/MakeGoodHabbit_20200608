package kr.co.tjoeun.makegoodhabbit_20200608;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import org.w3c.dom.Text;

import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivitySignUpBinding;

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

        binding.pwEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String pw = binding.emailEdt.getText().toString();

                if(pw.length() < 8) {

                    binding.pwResultTxt.setText("비밀번호를 8자 이상 입력해주세요.");

                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void setValues() {

    }
}
