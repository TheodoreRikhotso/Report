package com.example.admin.report;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class StudentsActivity extends Fragment {
private GridView grStudent;
    private List<Report> r;
    private ArrayList<String> student = new ArrayList();
    private ProgressDialog progressDialog;

    public StudentsActivity() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_student, container, false);
        grStudent =(GridView)view.findViewById(R.id.grStudent);
        progressDialog = new ProgressDialog(getActivity());


        PersonDatabase pd =new PersonDatabase(getActivity());

        List<Person> p =pd.getAllPeople();
         student.add("Waiting.. ");
        if(MainMenuActivity.PERON.getRole().equals("teacher")) {
            StudentAdapter adapter = new StudentAdapter(getActivity(), p);
            grStudent.setAdapter(adapter);
        }
        if(MainMenuActivity.PERON.getRole().equals("student")) {
            ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,student);
           //grStudent.setAdapter(arrayAdapter);

            progressDialog.setMessage("Student Waiting to report...");
            progressDialog.show();

                Intent intent = new Intent(getActivity(), ReportActivity.class);
                int id = (MainMenuActivity.PERON.getId());

                intent.putExtra("pid",id);
                startActivity(intent);
        }
//

//            }

        //}

        grStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ReportActivity.class);
                Person person = (Person) grStudent.getItemAtPosition(i);
                intent.putExtra("personid",person);
                startActivity(intent);
            }
        });

        return view;




    }
}
