package com.example.admin.report;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ReportActivity extends AppCompatActivity {

    private EditText etSubjcet,etMark,etTotal,etComment,etTerm,etId;
    private TextView tvTotalMark,tComment;
    private Button btnSave;
    private  Intent i;
    private  Person p;
    private  List<Report> r;
    private Spinner spSubject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        etComment = (EditText) findViewById(R.id.etComment);
        etMark = (EditText) findViewById(R.id.etMarks);

        tvTotalMark = (TextView) findViewById(R.id.tvFinalTotal);
        tComment = (TextView) findViewById(R.id.tvFinalcomment);

        tvTotalMark.setText(ReportAdapter.AVERAGE+"%");
        tComment.setText(" "+ReportAdapter.COMMENT);



        ListView lvView =(ListView) findViewById(R.id.lvReport);
         i = getIntent();

         p = (Person)i.getSerializableExtra("personid");

        PersonDatabase pd = new PersonDatabase(this);
        ReportAdapter.AVERAGE =0;
       if(p != null) {
           r = pd.getAllSudentSubject(p.getId());
       }else {
           r = pd.getAllSudentSubject(MainMenuActivity.PERON.getId());
      }


        if(r != null)
        {
            ReportAdapter adapter = new ReportAdapter(this,r);

            lvView.setAdapter(adapter);
            Toast.makeText(getApplicationContext(),"good ",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"Null ",Toast.LENGTH_SHORT).show();
        }




    }
    private void diplayInputDialog() {
        Dialog d = new Dialog(this);
        d.setTitle("Save To FireBase");
        d.setContentView(R.layout.input_dialog);
        final String[] sub={"Accounting","Agricultural ","Economics","Agricultural Technology","Geography","History","Physical Science","Life Sciences","Hospitality Studies"};
        final Report r = new Report();
        etComment = (EditText) d.findViewById(R.id.etComment);

        etMark = (EditText) d.findViewById(R.id.etMarks);
        etTotal = d.findViewById(R.id.etTotal);



        etTerm = (EditText) d.findViewById(R.id.etTerm);
        spSubject= d.findViewById(R.id.spSubject);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,sub);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spSubject.setAdapter(aa);
        spSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                r.setSubject(sub[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        Button btnSaved =(Button)d.findViewById(R.id.btnSaveds);
        btnSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String comment = etComment.getText().toString().trim();

                int term = Integer.parseInt(etTerm.getText().toString().trim());
                int total =  Integer.parseInt(etTotal.getText().toString().trim());
                int marks =  Integer.parseInt(etMark.getText().toString().trim());
                Intent i = getIntent();
                Person p = (Person)i.getSerializableExtra("personid");



              //  r.setSubject(subject);
                r.setComment(comment);
                r.setTerm(term);
                r.setTotalMarks(total);
                r.setMarks(marks);
                r.setRef(p.getId());
                PersonDatabase pd = new PersonDatabase(getApplicationContext());
                Toast.makeText(getApplicationContext(),"Subject Added"+p.getId(),Toast.LENGTH_SHORT).show();
                int number=pd.addSubject(r);
                if(number>1)
                {
                    Toast.makeText(getApplicationContext(),"Subject Added",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Subject not Added",Toast.LENGTH_SHORT).show();
                }




            }
        });



        Window window = d.getWindow();
        window.setLayout(AppBarLayout.LayoutParams.FILL_PARENT, AppBarLayout.LayoutParams.WRAP_CONTENT);

        d.show();


    }

    public void delete()
    {
        Dialog d = new Dialog(this);
        d.setTitle("Save To FireBase");
        d.setContentView(R.layout.delet_subject);



        Button btnDel = (Button) d.findViewById(R.id.btnDel);
        etId =  d.findViewById(R.id.etId);


        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id =  Integer.parseInt(etId.getText().toString());
                PersonDatabase personDatabase = new PersonDatabase(getApplicationContext());
                personDatabase.deletSubjectAll(id);

            }
        });
        Window window = d.getWindow();
        window.setLayout(AppBarLayout.LayoutParams.FILL_PARENT, AppBarLayout.LayoutParams.WRAP_CONTENT);

        d.show();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

            inflater.inflate(R.menu.menu, menu);


        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (p != null) {
            switch (item.getItemId()) {
                case R.id.menu_add:
                    diplayInputDialog();
                    return true;
                case R.id.menu_update:

                    return true;
                case R.id.menu_delete:
                    delete();

                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }else {
            return super.onOptionsItemSelected(item);
        }
    }


}
