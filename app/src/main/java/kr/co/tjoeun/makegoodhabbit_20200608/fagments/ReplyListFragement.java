package kr.co.tjoeun.makegoodhabbit_20200608.fagments;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import kr.co.tjoeun.makegoodhabbit_20200608.Adatpers.ReplyAdapter;
import kr.co.tjoeun.makegoodhabbit_20200608.R;
import kr.co.tjoeun.makegoodhabbit_20200608.databinding.FragmentReplyListBinding;
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Reply;

public class ReplyListFragement extends Fragment {

    ReplyListFragement mContext = this;
    FragmentReplyListBinding binding;
    int proofId;
    private ReplyAdapter replyAdapter;
    List<Reply> replyList = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_reply_list, container, false);

        return binding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.proofReplyListView.setAdapter(replyAdapter);

    }
}

