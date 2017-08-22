package com.example.admin.report;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Admin on 8/15/2017.
 */

public class StudentAdapter extends BaseAdapter {
    private Context c;

    private int resource;
    private List<Person> people;
    private LayoutInflater inflater;

    public static String STORE = "";


    public StudentAdapter(Context c, List<Person> people) {
        this.c = c;
        this.people = people;
    }


    @Override
    public int getCount() {
        return people.size();
    }

    @Override
    public Object getItem(int position) {
        return people.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View rootView, ViewGroup parent) {

        if (inflater == null) {

            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        }
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.model, parent, false);
        }


        Person person = people.get(position);
        TextView tvName = (TextView) rootView.findViewById(R.id.tvNames);
        TextView tvCompletName = (TextView) rootView.findViewById(R.id.tvCompletName);
        TextView tvId = (TextView) rootView.findViewById(R.id.tvId);
        TextView tvGrade =rootView.findViewById(R.id.tvGradeview);

        String name = person.getName();


        tvId.setText(""+person.getId());
        tvId.setVisibility(View.GONE);

        String letter = Character.toString(name.charAt(0));
        tvName.setText(letter);
        tvName.setGravity(View.TEXT_ALIGNMENT_CENTER);
        tvGrade.setText("Grade: "+person.getGrade());
        tvCompletName.setText(name);
        return rootView;
    }
}
