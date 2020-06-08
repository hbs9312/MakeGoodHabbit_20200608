package kr.co.tjoeun.makegoodhabbit_20200608.Adatpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.co.tjoeun.makegoodhabbit_20200608.R;
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Project;

public class ProjectAdapter extends ArrayAdapter<Project> {

    Context mContext;
    List<Project> mList;
    LayoutInflater inf;

    public ProjectAdapter(@NonNull Context context, int resource, @NonNull List objects) {

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
            row = inf.inflate(R.layout.project_list_item,null);
        }


        TextView titleTxt = row.findViewById(R.id.projectTitleTxt);
        ImageView imageView = row.findViewById(R.id.projectImg);

        Project data = mList.get(position);

        titleTxt.setText(data.getTitle());
        Glide.with(mContext).load(data.getImgUrl()).into(imageView);

        return row;

    }
}
