package com.example.admin.report;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    public static Person PERON;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            fragmentManager = getSupportFragmentManager();
            fragment =new ProfileActivity();

            switch (item.getItemId()) {

                case R.id.navigation_home:

                    fragment = new ProfileActivity();

                    break;
//
                case R.id.navigation_dashboard:

                    fragment = new StudentsActivity();

                    break;

                case R.id.navigation_notifications:

                    fragment = new FinalActivity();

                    break;




            }
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.content, fragment).commit();
            return true;

//            Fragment selectedFragment = null;
//            switch (item.getItemId()) {
//                case R.id.action_item1:
//
//                case R.id.action_item2:
//                    selectedFragment = ItemTwoFragment.newInstance();
//                    break;
//                case R.id.action_item3:
//                    selectedFragment = ItemThreeFragment.newInstance();
//                    break;
//            }
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.frame_layout, selectedFragment);
//            transaction.commit();
//            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

         PERON = (Person) getIntent().getSerializableExtra("person");


        ProfileActivity f = new ProfileActivity();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.add(R.id.content,f);
        transaction.commit();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
