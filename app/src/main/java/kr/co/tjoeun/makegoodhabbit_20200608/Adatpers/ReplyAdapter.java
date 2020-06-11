package kr.co.tjoeun.makegoodhabbit_20200608.Adatpers;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.strictmode.ServiceConnectionLeakedViolation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.List;

import kr.co.tjoeun.makegoodhabbit_20200608.R;
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Reply;
import kr.co.tjoeun.makegoodhabbit_20200608.utils.ServerUtil;

public class ReplyAdapter extends ArrayAdapter<Reply> {

    Context mContext;
    List<Reply> mList;
    LayoutInflater inf;


    public ReplyAdapter(@NonNull Context context, int resource, @NonNull List<Reply> objects) {
        super(context, resource, objects);

        mContext = context;
        mList = objects;
        inf = LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;

        if(row == null) {
            row = inf.inflate(R.layout.reply_list_item,null);
        }

        TextView replyUserNickTxt = row.findViewById(R.id.replyUserNickTxt);
        TextView replyContentTxt = row.findViewById(R.id.replyContent);
        Button likeReplyBtn = row.findViewById(R.id.likeReplyBtn);

        final Reply data = mList.get(position);

        replyUserNickTxt.setText(data.getNickName());
        replyContentTxt.setText(data.getContent());

        Log.d("좋아요 수 조회", data.getLikeCount()+"");
        likeReplyBtn.setText(String.format("좋아요 %d", data.getLikeCount()));

        likeReplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ServerUtil.postRequestLikeReply(mContext, data.getId(), new ServerUtil.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {

                        Log.d("댓글 좋아요 응답", json.toString());

                        try {
                            JSONObject dataObj = json.getJSONObject("data");
                            JSONObject like = dataObj.getJSONObject("like");
                            int likeCount = like.getInt("like_count");
                            boolean myLike = like.getBoolean("my_like");

                            data.setMyLike(myLike);
                            data.setLikeCount(likeCount);

                            Log.d("좋아요 수 조회", data.getLikeCount()+"");


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

        return row;
    }
}
