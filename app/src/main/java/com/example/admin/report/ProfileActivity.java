package com.example.admin.report;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends Fragment {
    public ProfileActivity() {
    }


    private TextView tvName,tvSurname,tvContact,tvDay,tvMonth,tvYear,tvEmail,tvViewStudent,tvGrade,tvDays,tEmail;
    private ImageView ivImages,drop_down_option_menu;
    private Button btnLoginout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_profile, container, false);
        tvName =(TextView)view.findViewById(R.id.tvNames);
        tvContact =(TextView)view.findViewById(R.id.tvContact);
        tvDays = (TextView)view.findViewById(R.id.tvDays);
        tEmail = (TextView)view.findViewById(R.id.tEmail);
        tvGrade= (TextView)view.findViewById(R.id.tvGrade);
        drop_down_option_menu=view.findViewById(R.id.drop_down_option_menu);
//        tvMonth =(TextView)findViewById(R.id.edMonth);
//        tvYear =(TextView)findViewById(R.id.edYear);
//        tvEmail =(TextView)findViewById(R.id.edEmail);
        ivImages = (ImageView)view.findViewById(R.id.ivImages) ;



//        Person person = (Person) getActivity().getIntent().getSerializableExtra("person");

        Person person = MainMenuActivity.PERON;

        tvName.setText(""+person.getName()+"  "+person.getSurname());
        tvContact.setText(""+person.getContact());
        tvGrade.setText("Grade :         "+person.getGrade());
        tvDays.setText("Date of Birth :  "+person.getDay()+"/"+person.getMonth()+"/"+person.getYear());
        tEmail.setText("Email Address :  "+person.getEmail());

        drop_down_option_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diplayInputDialog();
            }
        });

try {
    Bitmap bmp = BitmapFactory.decodeByteArray(person.getPhoto(), 0,person.getPhoto().length);

//        byte[] imgbytes = Base64.decode(person.getPhoto(), Base64.DEFAULT);
//        Bitmap bitmap = BitmapFactory.decodeByteArray(imgbytes, 0,
//                imgbytes.length);
//        ivImages.setImageBitmap(bitmap);
//        ivImages.setImageBitmap(Bitmap.createScaledBitmap(bmp, ivImages.getWidth(),
//                ivImages.getHeight(), false));
    Bitmap bitmap = BitmapFactory.decodeByteArray(person.getPhoto() , 0, person.getPhoto().length);
    ivImages.setImageBitmap(bitmap );
}catch (Exception e)
{
    e.printStackTrace();
}

//        tvViewStudent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(),StudentsActivity.class);
//                startActivity(intent);
//            }
//        });

        return view;
    }
    private void diplayInputDialog() {
        Dialog d = new Dialog(getActivity());
        d.setTitle("Save To FireBase");
        d.setContentView(R.layout.logout_dialog);

        btnLoginout = (Button) d.findViewById(R.id.btnLogout);

        btnLoginout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });


        Window window = d.getWindow();
        window.setLayout(AppBarLayout.LayoutParams.WRAP_CONTENT, AppBarLayout.LayoutParams.WRAP_CONTENT);

        d.show();
    }
}
