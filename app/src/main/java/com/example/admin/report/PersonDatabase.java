package com.example.admin.report;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 8/14/2017.
 */

public class PersonDatabase extends SQLiteOpenHelper {
    private static final String TABLE_PERSON="table_people";
    private static final String KEY_ID ="id";
    private static final String KEY_NAME="name";
    private static final String KEY_SURNAME ="surname";
    private static final String KEY_PASSWORD ="password";
    private static final String KEY_EMAIL ="email";
    private static final String KEY_ROLE ="role";
    private static final String KEY_CONTACT ="contact";
    private static final String KEY_GRADE ="grade";
    private static final String KEY_YEAR ="year";
    private static final String KEY_MONTH ="month";
    private static final String KEY_DAY ="day";
    private static final String KEY_IMAGE ="image";
    ///REport
    private static final String TABLE_REPORT="table_reports";
    private static final String KEY_RPORTID ="id";
    private static final String KEY_REF ="ref";
    private static final String KEY_SUBJECT="subject";
    private static final String KEY_MARKS ="marks";
    private static final String KEY_TERM ="term";
    private static final String KEY_COMMENT ="comment";
    private static final String KEY_TOTAL_MARK ="totalmarks";




    private static final String DATABASE_NAME="people.db";
    private static final int DATABASE_VERSION=1;

    private final String CREATE_TABLE_CONTACT ="Create table " +TABLE_PERSON + " ( "
            + KEY_ID + "  integer primary key autoincrement  , "
            + KEY_NAME + " text not null , "
            + KEY_PASSWORD + " text not null , "
            + KEY_EMAIL + " text not null , "
            + KEY_ROLE + " text not null , "
            + KEY_CONTACT + " integer not null , "
            + KEY_GRADE + " integer  , "
            + KEY_YEAR + " integer  , "
            + KEY_MONTH + " integer  , "
            + KEY_DAY + " integer , "
            + KEY_SURNAME + " text not null, "
            + KEY_IMAGE + " BLOB );";

    private final String CREATE_TABLE_REPORT ="create table "
            + TABLE_REPORT + " ("
            + KEY_RPORTID + " integer primary key autoincrement, "
            + KEY_SUBJECT + " text not null, "
            + KEY_COMMENT + " text ,"
            + KEY_MARKS + " integer,"
            + KEY_TERM + " integer,"
            + KEY_TOTAL_MARK + " integer,"
            + KEY_REF + " integer);";

    public PersonDatabase(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACT);
        db.execSQL(CREATE_TABLE_REPORT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF TABLE EXIST" +TABLE_PERSON);
        db.execSQL(CREATE_TABLE_CONTACT);
        db.execSQL("DROP TABLE IF TABLE EXIST" +TABLE_REPORT);
        db.execSQL(CREATE_TABLE_REPORT);
    }
    public int addContact(Person person)
    {
        int id =0;

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues cv = new ContentValues();

        cv.put(KEY_NAME, person.getName());
        cv.put(KEY_SURNAME, person.getSurname());
        cv.put(KEY_PASSWORD, person.getPassword());
        cv.put(KEY_EMAIL, person.getEmail());
        cv.put(KEY_ROLE, person.getRole());
        cv.put(KEY_CONTACT, person.getContact());
        cv.put(KEY_GRADE, person.getGrade());
        cv.put(KEY_YEAR, person.getYear());
        cv.put(KEY_MONTH, person.getMonth());
        cv.put(KEY_DAY, person.getDay());
        cv.put(KEY_IMAGE, person.getPhoto());

        id =(int)db.insert(TABLE_PERSON, null,cv);

        return id;

    }

    public List<Person> getAllPeople()
    {
        String role = "student";
        List<Person> contacts = new ArrayList<Person>();
        SQLiteDatabase db =this.getWritableDatabase();
        String selectQuery ="SELECT * FROM " + TABLE_PERSON +" WHERE "+KEY_ROLE +" =?";
        String args[] = {role};

        Cursor cursor =db.rawQuery(selectQuery, args);

        if(cursor.moveToFirst())
        {
            do
            {
                Person person = new Person();
                person.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                person.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                person.setSurname(cursor.getString(cursor.getColumnIndex(KEY_SURNAME)));
                person.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
                person.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                person.setRole(cursor.getString(cursor.getColumnIndex(KEY_ROLE)));
                person.setGrade(cursor.getInt(cursor.getColumnIndex(KEY_GRADE)));
                person.setContact(cursor.getInt(cursor.getColumnIndex(KEY_CONTACT)));
                person.setYear(cursor.getInt(cursor.getColumnIndex(KEY_YEAR)));
                person.setMonth(cursor.getInt(cursor.getColumnIndex(KEY_MONTH)));
                person.setDay(cursor.getInt(cursor.getColumnIndex(KEY_DAY)));
                person.setPhoto(cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE)));




                contacts.add(person);

            }while(cursor.moveToNext());
        }
        return contacts;

    }
    public List<Person> getAllFishForACompetitions(long compID)
    {
        ArrayList<Person> people = new ArrayList<Person>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "select * from " + TABLE_PERSON + " where " + KEY_ID + " =?";
        String args[] = {String.valueOf(compID)};


        Cursor cursor = db.rawQuery(select, args);
        if(cursor.moveToFirst())
        {
            do
            {  Person person = new Person();
                person.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                person.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                person.setSurname(cursor.getString(cursor.getColumnIndex(KEY_SURNAME)));
                person.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
                person.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                person.setRole(cursor.getString(cursor.getColumnIndex(KEY_ROLE)));
                person.setGrade(cursor.getInt(cursor.getColumnIndex(KEY_GRADE)));
                person.setContact(cursor.getInt(cursor.getColumnIndex(KEY_CONTACT)));
                person.setYear(cursor.getInt(cursor.getColumnIndex(KEY_YEAR)));
                person.setMonth(cursor.getInt(cursor.getColumnIndex(KEY_MONTH)));
                person.setDay(cursor.getInt(cursor.getColumnIndex(KEY_DAY)));
                person.setPhoto(cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE)));


                people.add(person);
            }while(cursor.moveToNext());
        }

        return people;
    }
    public void update(Person person)
    {
        String args[] = { String.valueOf(person.getId())};

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(KEY_NAME, person.getName());
        cv.put(KEY_SURNAME, person.getSurname());
        cv.put(KEY_PASSWORD, person.getPassword());
        cv.put(KEY_EMAIL, person.getEmail());
        cv.put(KEY_ROLE, person.getRole());
        cv.put(KEY_CONTACT, person.getContact());
        cv.put(KEY_GRADE, person.getGrade());
        cv.put(KEY_YEAR, person.getYear());
        cv.put(KEY_MONTH, person.getMonth());
        cv.put(KEY_DAY, person.getDay());
        cv.put(KEY_IMAGE, person.getPhoto());
        db.update(TABLE_PERSON, cv, KEY_ID+" = ? ", args);
        db.close();

    }
    public void delets(long id)
    {
        String args[] = {String.valueOf(id)};

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PERSON, KEY_ID + " = ? ", args);
        db.close();

    }
    public Person getLogin(String pass,String username)
    {
        String name ="xxx";

        ArrayList<Person> contacts = new ArrayList<Person>();

        Person person = new Person();;
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "select * from " + TABLE_PERSON + " where "+KEY_PASSWORD+" =? and "+KEY_EMAIL+" =?";
        String args[] = {pass,username};

        Cursor cursor = db.rawQuery(select, args);


        if(cursor.moveToFirst())
        {
            do {

                name = ""+cursor.getInt(cursor.getColumnIndex(KEY_ID));
                person.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                person.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                person.setSurname(cursor.getString(cursor.getColumnIndex(KEY_SURNAME)));
                person.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
                person.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                person.setRole(cursor.getString(cursor.getColumnIndex(KEY_ROLE)));
                person.setGrade(cursor.getInt(cursor.getColumnIndex(KEY_GRADE)));
                person.setContact(cursor.getInt(cursor.getColumnIndex(KEY_CONTACT)));
                person.setYear(cursor.getInt(cursor.getColumnIndex(KEY_YEAR)));
                person.setMonth(cursor.getInt(cursor.getColumnIndex(KEY_MONTH)));
                person.setDay(cursor.getInt(cursor.getColumnIndex(KEY_DAY)));
                person.setPhoto(cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE)));
                //name = "right";
            }while(cursor.moveToNext());
        }


        return person;
    }
    public Person getAll(Integer compID)
    {
        ArrayList<Person> contacts = new ArrayList<Person>();
        SQLiteDatabase db = this.getReadableDatabase();

        String select = "select * from " + TABLE_PERSON + " where " + KEY_ID + " =?";
        String args[] = {String.valueOf(compID)};

        Person contact = new Person();
        Cursor cursor = db.rawQuery(select, args);
        if(cursor.moveToFirst())
        {
            do
            {
                contact.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                contact.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
                contact.setSurname(cursor.getString(cursor.getColumnIndex(KEY_SURNAME)));
                contact.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASSWORD)));
                contact.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
                contact.setRole(cursor.getString(cursor.getColumnIndex(KEY_ROLE)));
                contact.setGrade(cursor.getInt(cursor.getColumnIndex(KEY_GRADE)));
                contact.setContact(cursor.getInt(cursor.getColumnIndex(KEY_CONTACT)));
                contact.setYear(cursor.getInt(cursor.getColumnIndex(KEY_YEAR)));
                contact.setMonth(cursor.getInt(cursor.getColumnIndex(KEY_MONTH)));
                contact.setDay(cursor.getInt(cursor.getColumnIndex(KEY_DAY)));
                contact.setPhoto(cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE)));


                contacts.add(contact);
            }while(cursor.moveToNext());
        }

        return contact;
    }

    public  int addSubject(Report report) {
        int id = 0;

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues cv = new ContentValues();

        cv.put(KEY_MARKS, report.getMarks());
        cv.put(KEY_COMMENT, report.getComment());
        cv.put(KEY_SUBJECT, report.getSubject());
        cv.put(KEY_TERM, report.getTerm());
        cv.put(KEY_TOTAL_MARK, report.getTotalMarks());
        cv.put(KEY_REF, report.getRef());



        id = (int) db.insert(TABLE_REPORT, null, cv);

        return id;
    }
    public List<Report> getAllSubject()
    {
        List<Report> reports = new ArrayList<Report>();
        SQLiteDatabase db =this.getWritableDatabase();
        String selectQuery ="SELECT * FROM " + TABLE_REPORT;

        Cursor cursor =db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst())
        {
            do
            {
                Report report = new Report();
                report.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                report.setSubject(cursor.getString(cursor.getColumnIndex(KEY_SUBJECT)));
                report.setComment(cursor.getString(cursor.getColumnIndex(KEY_COMMENT)));
                report.setMarks(cursor.getInt(cursor.getColumnIndex(KEY_MARKS)));
                report.setTerm(cursor.getInt(cursor.getColumnIndex(KEY_TERM)));
                report.setTotalMarks(cursor.getInt(cursor.getColumnIndex(KEY_TOTAL_MARK)));
                report.setRef(cursor.getInt(cursor.getColumnIndex(KEY_TOTAL_MARK)));






                reports.add(report);

            }while(cursor.moveToNext());
        }
        return reports;

    }


    public List<Report> getAllSudentSubject(int compID)
    {
        List<Report> reports = new ArrayList<Report>();
        SQLiteDatabase db =this.getWritableDatabase();
        String selectQuery ="SELECT * FROM " + TABLE_REPORT +" WHERE "+KEY_REF+"=?";

        String select[] = {String.valueOf(compID)};
        Cursor cursor =db.rawQuery(selectQuery, select);

        if(cursor.moveToFirst())
        {
            do
            {
                Report report = new Report();
                //report.setId(cursor.getInt(cursor.getColumnIndex()));
                report.setSubject(cursor.getString(cursor.getColumnIndex(KEY_SUBJECT)));
                report.setComment(cursor.getString(cursor.getColumnIndex(KEY_COMMENT)));
                report.setMarks(cursor.getInt(cursor.getColumnIndex(KEY_MARKS)));
                report.setTerm(cursor.getInt(cursor.getColumnIndex(KEY_TERM)));
                report.setTotalMarks(cursor.getInt(cursor.getColumnIndex(KEY_TOTAL_MARK)));
                report.setRef(cursor.getInt(cursor.getColumnIndex(KEY_TOTAL_MARK)));






                reports.add(report);

            }while(cursor.moveToNext());
        }
        return reports;

    }

    public void deletSubjectAll(long id)
    {
        String args[] = {String.valueOf(id)};

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_REPORT, KEY_REF + " = ? ", args);
        db.close();

    }

}
