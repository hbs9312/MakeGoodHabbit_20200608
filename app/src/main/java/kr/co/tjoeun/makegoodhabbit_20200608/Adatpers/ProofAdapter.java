package kr.co.tjoeun.makegoodhabbit_20200608.Adatpers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import kr.co.tjoeun.makegoodhabbit_20200608.R;
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Proof;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ContextUtil;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

public class ProofAdapter extends ArrayAdapter<Proof> {

    Context mContext;
    List<Proof> mList;
    LayoutInflater inf;

    public ProofAdapter(@NonNull Context context, int resource, @NonNull List<Proof> objects) {
        super(context, resource, objects);

        mContext = context;
        mList = objects;
        inf = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;

        if(row == null) {
            row = inf.inflate(R.layout.proof_list_item, null);
        }

        TextView proofContentTxt = row.findViewById(R.id.proofContentTxt);
        ImageView proofImg = row.findViewById(R.id.proofImg);
        Button likeBtn = row.findViewById(R.id.likeBtn);

        final Proof data = mList.get(position);
        final int proofId = data.getId();

        if(!data.getImgList().isEmpty()){
            Glide.with(mContext).load(data.getImgList().get(0).getImgUrl()).into(proofImg);
        }

        proofContentTxt.setText(data.getContent());

        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerUtil.postRequestLikeProof(mContext, ContextUtil.getUserToken(mContext), proofId, new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        Log.d("좋아요응답", json.toString());

                        try {
                            JSONObject dataObj = json.getJSONObject("data");
                            JSONObject like = dataObj.getJSONObject("like");

                            data.setMyLike(like.getBoolean("my_like"));
                            data.setLikeCount(like.getInt("like_count"));

                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    notifyDataSetChanged();
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

//        replyBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(mContext, ViewReplyActivity.class);
//                intent.putExtra("proofId", proofId);
//                mContext.startActivity(intent);
//
//            }
//        });


        likeBtn.setText("좋아요 " + data.getLikeCount());
        if(data.isMyLike()) {
            likeBtn.setBackgroundResource(R.drawable.red_border_box);
            likeBtn.setTextColor(Color.RED);
        }
        else {
            likeBtn.setBackgroundResource(R.drawable.blue_border_box);
            likeBtn.setTextColor(Color.BLUE);
        }

        return row;

    }
}
