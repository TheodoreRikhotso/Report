package com.example.admin.report;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FinalActivity extends Fragment {
    private TextView tvFinalMarks,tvComment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_final, container, false);
        tvFinalMarks = view.findViewById(R.id.tvFinalTotalMark);
        tvComment = view.findViewById(R.id.tvCommentFinal);
        tvFinalMarks.setText(ReportAdapter.AVERAGE+"%");
        tvComment.setText(ReportAdapter.COMMENT);


        return view;



    }
}
