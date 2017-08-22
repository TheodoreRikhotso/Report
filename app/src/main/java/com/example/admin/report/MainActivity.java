package com.example.admin.report;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText tvEmail,tvPassword;
    private Button btnLogin;
    private TextView tvSignup;

    private ProgressDialog progressDialog;
    public  static  Person PERSON = new Person();
    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvEmail =(EditText)findViewById(R.id.tvEmail);
        tvPassword =(EditText)findViewById(R.id.tvPassword);
        tvSignup =(TextView)findViewById(R.id.tvSignup);
        btnLogin = (Button)findViewById(R.id.btnLogin);

        progressDialog = new ProgressDialog(this);
        btnLogin.setOnClickListener(this);
        tvSignup.setOnClickListener(this);
        progressDialog.cancel();
    }


    private void checkLogin() {
        String email= tvEmail.getText().toString().trim();
        String password = tvPassword.getText().toString().trim();
        PersonDatabase pd = new PersonDatabase(this);
        if(TextUtils.isEmpty(email))
        {

            Toast.makeText(this,"Please enter email", Toast.LENGTH_SHORT).show();


            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(this,"Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }




        //if validtion are ok

        //
        if(!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password))
        {
            String em = email;
            String password1 = password;
            Person person = pd.getLogin(password1,email);

            if(person.equals(null))
            {
                Toast.makeText(this,"Incorrent Password" +person, Toast.LENGTH_SHORT).show();



            }else if(person!=null) {
//                progressDialog.setMessage("Login User...");
//                progressDialog.show();
                if(person.getId()!=0) {
                    Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
                    PERSON = person;
                    Toast.makeText(this, "Corrent Password " + person.getRole(), Toast.LENGTH_SHORT).show();
                    intent.putExtra("person", PERSON);
                    startActivity(intent);
                }
                Toast.makeText(this,"Incorrent Password" , Toast.LENGTH_SHORT).show();


            }
        }
    }

    @Override
    public void onClick(View view) {

        if(view==btnLogin)
        {
            checkLogin();
        }
        if(view==tvSignup)
        {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);

        }


    }

}
