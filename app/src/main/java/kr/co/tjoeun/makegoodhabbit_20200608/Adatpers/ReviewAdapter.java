package kr.co.tjoeun.makegoodhabbit_20200608.Adatpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import kr.co.tjoeun.makegoodhabbit_20200608.R;
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Review;

public class ReviewAdapter extends ArrayAdapter<Review> {

    Context mContext;
    List<Review> mList;
    LayoutInflater inf;

    public ReviewAdapter(@NonNull Context context, int resource, @NonNull List<Review> objects) {
        super(context, resource, objects);

        mContext = context;
        mList = objects;
        inf= LayoutInflater.from(mContext);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;

        if(row == null) {
            row = inf.inflate(R.layout.review_list_item, null);

        }

        TextView title = row.findViewById(R.id.reviewTitleTxt);
        TextView nickName = row.findViewById(R.id.userNickNameTxt);

        title.setText(mList.get(position).getTitle());
        nickName.setText(mList.get(position).getNickName());

        return row;


    }
}
