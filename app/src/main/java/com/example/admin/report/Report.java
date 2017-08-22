package com.example.admin.report;

/**
 * Created by Admin on 8/16/2017.
 */

public class Report {
    private String subject,comment;
    private int term,marks,totalMarks;
    private int ref;
    private  int id;

    public Report() {
    }

    public Report(String subject, String comment, int term, int marks, int totalMarks) {
        this.subject = subject;
        this.comment = comment;
        this.term = term;
        this.marks = marks;
        this.totalMarks = totalMarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRef() {
        return ref;
    }

    public void setRef(int ref) {
        this.ref = ref;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }
}
