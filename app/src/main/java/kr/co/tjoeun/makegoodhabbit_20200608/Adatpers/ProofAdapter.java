package kr.co.tjoeun.makegoodhabbit_20200608.Adatpers;

import android.content.Context;
import android.text.Layout;
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
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Proof;

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

        Proof data = mList.get(position);
        if(!data.getImgList().isEmpty()){
            Glide.with(mContext).load(data.getImgList().get(0).getImgUrl()).into(proofImg);
        }

        proofContentTxt.setText(data.getContent());

        return row;

    }
}
