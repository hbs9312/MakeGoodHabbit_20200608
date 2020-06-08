package kr.co.tjoeun.makegoodhabbit_20200608;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    Context mContext = this;

    abstract public void setupEvents();
    abstract public void setValues();

}
