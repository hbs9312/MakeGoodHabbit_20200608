package kr.co.tjoeun.makegoodhabbit_20200608;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivityViewReviewDetailBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Review;

public class ViewReviewDetailActivity extends BaseActivity {

    ActivityViewReviewDetailBinding binding;
    Review mReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_review_detail);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {


    }

    @Override
    public void setValues() {

        mReview = (Review) getIntent().getSerializableExtra("reviewData");
        Log.d("클래스 확인", mReview.toString());

        binding.reviewTitleTxt.setText(mReview.getTitle());
        binding.reviewContentTxt.setText(mReview.getContent());
        binding.userNickNameTxt.setText(mReview.getNickName());


    }


}
