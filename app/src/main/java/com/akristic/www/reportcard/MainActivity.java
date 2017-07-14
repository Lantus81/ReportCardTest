package com.akristic.www.reportcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView gradesTextView = (TextView) findViewById(R.id.main_text_grades);
        TextView grades1TextView = (TextView) findViewById(R.id.main_text_left);
        TextView grades2TextView = (TextView) findViewById(R.id.main_text_grades_2);
        TextView grades3TextView = (TextView) findViewById(R.id.main_text_right);

        String history = "History";
        int historyGrade = 3;
        ReportCard myReportCard = new ReportCard(history, historyGrade);
        String math = "Math";
        int mathGrade = 5;
        myReportCard.addNewSubjectGrade(math, mathGrade);
        String allGrades = myReportCard.toString();
        gradesTextView.setText(allGrades);
        ReportCard student1ReportCard = new ReportCard("Biology", 5);
        int grade = student1ReportCard.getSubjectGrade("biology");
        grades2TextView.setText(String.valueOf(grade));

        ReportCard reportCard3 = new ReportCard("Math", 6, 1, 10);
        reportCard3.addNewSubjectGrade("Biology", 6);
        reportCard3.addNewSubjectGrade("Fizika", 10);
        reportCard3.addNewSubjectGrade("Tjelesni",8);
        reportCard3.addNewSubjectGrade("Vjeronauk", 10);
        reportCard3.addNewSubjectGrade("Glazbeni", 12);
        reportCard3.addNewSubjectGrade("Engleski", -5);

        ArrayList<String> subjects = reportCard3.getSubjects();
        String name1 = subjects.get(0);
        grades1TextView.setText(name1);
        int arraySize = subjects.size();
        grades3TextView.setText(String.valueOf(arraySize));
        ArrayList<Integer> grades = reportCard3.getGrades();
        int gradeInteger = grades.get(0);
        grades2TextView.setText(String.valueOf(gradeInteger));
        int gradeArraySize = grades.size();
        gradesTextView.setText(String.valueOf(gradeArraySize));

        reportCard3.setSubjectGrade(3, "Math", 6);

        int i = reportCard3.deleteSubject(8);
        if (i==-1){
            Toast.makeText(this, "Index out of bounds", Toast.LENGTH_LONG).show();
        }else if (i==1) {
            Toast.makeText(this, "All is good", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "Something is terribly wrong", Toast.LENGTH_LONG).show();
        }
        gradesTextView.setText(reportCard3.toString());


    }
}
