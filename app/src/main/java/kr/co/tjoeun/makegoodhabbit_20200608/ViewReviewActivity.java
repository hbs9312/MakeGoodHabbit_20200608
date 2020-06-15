package kr.co.tjoeun.makegoodhabbit_20200608;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjoeun.makegoodhabbit_20200608.Adatpers.ReviewAdapter;
import kr.co.tjoeun.makegoodhabbit_20200608.databinding.ActivityViewReviewBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Review;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

public class ViewReviewActivity extends BaseActivity {

    ActivityViewReviewBinding binding;
    List<Review> reviewList = new ArrayList<>();
    ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_view_review);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.writeReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(mContext, WriteReviewActivity.class);
                startActivity(myIntent);

            }
        });

        binding.reviewListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Review clickedReview = reviewList.get(position);

                Intent myIntent = new Intent(mContext, ViewReviewDetailActivity.class);
                myIntent.putExtra("reviewData", clickedReview);
                startActivity(myIntent);
            }
        });
    }

    @Override
    public void setValues() {

        reviewAdapter = new ReviewAdapter(mContext,R.layout.review_list_item, reviewList);
        binding.reviewListView.setAdapter(reviewAdapter);

        ServerUtil.getRequestReview(mContext, 1, new ServerUtil.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                Log.d("리뷰목록응답", json.toString());

                try {
                    JSONObject data = json.getJSONObject("data");
                    JSONArray reviews = data.getJSONArray("reviews");

                    for(int i=0;i<reviews.length();i++) {

                        JSONObject reviewObj = reviews.getJSONObject(i);
                        Review tempReview = Review.getReviewFromJson(reviewObj);
                        reviewList.add(tempReview);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                reviewAdapter.notifyDataSetChanged();
                            }
                        });

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
