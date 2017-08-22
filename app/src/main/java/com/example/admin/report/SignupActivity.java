package com.example.admin.report;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class SignupActivity extends AppCompatActivity {

    private EditText edPassword, edName, edSurname, edContact, edDay, edMonth, edYear, edEmail, edReenterPassword;
    private RadioGroup rgGrade, rgRole;
    private RadioButton rdTeacher, rdStudent, rbG8, rbG9, rbG10, rbG11, rbG12;
    private Person contact;
    private Button btnSubmit;
    private LinearLayout llGrade, llDate;
    ImageView ivPhoto;
    int PICK_IMAGE_REQUEST = 111;
    Boolean result;
    String imgDecodableString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edName = (EditText) findViewById(R.id.edName);
        edSurname = (EditText) findViewById(R.id.edSurname);
        edContact = (EditText) findViewById(R.id.edContact);
        edDay = (EditText) findViewById(R.id.edDay);
        edMonth = (EditText) findViewById(R.id.edMonth);
        edYear = (EditText) findViewById(R.id.edYear);
        edEmail = (EditText) findViewById(R.id.edEmail);
        ivPhoto = (ImageView) findViewById(R.id.ivPhoto);

        edReenterPassword = (EditText) findViewById(R.id.edReenterPassword);
        edPassword = (EditText) findViewById(R.id.edPassword);

        rgGrade = (RadioGroup) findViewById(R.id.rgGrade);
        rgRole = (RadioGroup) findViewById(R.id.rgRole);

        rdStudent = (RadioButton) findViewById(R.id.rdStudent);
        rdTeacher = (RadioButton) findViewById(R.id.rdTeacher);
        rbG8 = (RadioButton) findViewById(R.id.rbG8);
        rbG9 = (RadioButton) findViewById(R.id.rbG9);
        rbG10 = (RadioButton) findViewById(R.id.rbG10);
        rbG11 = (RadioButton) findViewById(R.id.rbG11);
        rbG12 = (RadioButton) findViewById(R.id.rbG12);

        llDate = (LinearLayout) findViewById(R.id.llDate);
        llGrade = (LinearLayout) findViewById(R.id.llGrade);

        int checked = rgRole.getCheckedRadioButtonId();

        rgRole.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup,  int checkedId) {
                if(checkedId==R.id.rdTeacher) {
                    llGrade.setVisibility(View.GONE);
                    llDate.setVisibility(View.GONE);
                }else {
                    llGrade.setVisibility(View.VISIBLE);
                    llDate.setVisibility(View.VISIBLE);

                }




            }
        });


        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);

            }
        });

         result = false;
        switch (checked) {
            case R.id.rdStudent:
                rdTeacher.setEnabled(true);
                result = false;
                break;
            case R.id.rdTeacher:
                result = true;

                break;

        }



        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString();
                String surname = edSurname.getText().toString();
                int contact = Integer.parseInt(edContact.getText().toString());
                Bitmap bitmap = ((BitmapDrawable) ivPhoto.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                Person c = new Person();

                int checked = rgRole.getCheckedRadioButtonId();
                String role = "student";
                switch (checked) {
                    case R.id.rdStudent:

                        role = "student";

                        break;
                    case R.id.rdTeacher:
                        role = "teacher";
                        break;
                }


                int check = rgGrade.getCheckedRadioButtonId();
                int grade = 8;
                switch (check) {
                    case R.id.rbG8:
                        grade = 8;
                        break;
                    case R.id.rbG9:
                        grade = 9;
                        break;
                    case R.id.rbG10:
                        grade = 10;
                        break;
                    case R.id.rbG11:
                        break;
                    case R.id.rbG12:
                        grade = 12;
                        break;
                }
                if(edYear.getText().toString().isEmpty()&&edDay.getText().toString().isEmpty()&&edMonth.toString().isEmpty()&&rgGrade.equals(null))
                {
                    c.setDay(0);
                    c.setMonth(0);
                    c.setYear(0);
                    c.setGrade(0);

                }else if (edYear.getText().toString()!= ""&&edDay.getText().toString() != ""&&edYear.getText().toString() != ""&&rgGrade.equals(null))
                {
                    int year = Integer.parseInt(edYear.getText().toString());
                    int month = Integer.parseInt(edMonth.getText().toString());
                    int day = Integer.parseInt(edDay.getText().toString());
                    c.setDay(day);
                    c.setMonth(month);
                    c.setYear(year);

                }

                c.setGrade(grade);

                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();

                PersonDatabase contactDatabase = new PersonDatabase(SignupActivity.this);


                c.setContact(contact);
                c.setPhoto(byteArray);
                c.setRole(role);
                c.setName(name);
                c.setPassword(password);
                c.setSurname(surname);
                c.setEmail(email);

                int i = contactDatabase.addContact(c);

                c.setId(i);
                if (i > 0) {

                    Toast.makeText(getApplicationContext(), "Successfully Registered "+c.getRole(), Toast.LENGTH_SHORT).show();
                    display("Here is ur Username"," Ur User-name: " +i);
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Unsuccessfully Registered", Toast.LENGTH_SHORT).show();
                }




            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            // When an Image is picked
            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                InputStream image_stream = getContentResolver().openInputStream(selectedImage);
                Bitmap bitmap = BitmapFactory.decodeStream(image_stream);
                ivPhoto.setImageBitmap(bitmap);
                //ivPhoto.setImageURI(selectedImage);
                // Set the Image in ImageView after decoding the String
//                ivPhoto.setImageBitmap(BitmapFactory
//                        .decodeFile(imgDecodableString));
                Toast.makeText(this, "You picked Image",
                        Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void display(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setTitle(message);
        builder.show();

    }
}


