package kr.co.tjoeun.makegoodhabbit_20200608.Adatpers;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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

        Proof data = mList.get(position);

        proofContentTxt.setText(data.getContent());

        return row;

    }
}
