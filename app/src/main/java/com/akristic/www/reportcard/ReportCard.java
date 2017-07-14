package com.akristic.www.reportcard;

import java.util.ArrayList;

/**
 * Created by Toni on 29.4.2017..
 */

public class ReportCard {
    //
    //*  Not sure if this is right way because with my code we must be sure that both subject
    //*  and subject grade are set in same time so that our sizes of ArrayLists are always the same.
    //*  We can set grade to some default value like 0 if we want to make Subject without grade, but that is not implemented here,
    //*  So it is not possible to insert new subject without grade.
    //
    private ArrayList<String> mSubjects;
    private ArrayList<Integer> mGrades;
    private int MIN_GRADE = 1; //set to default values, grades in Croatia are from 1 to 5
    private int MAX_GRADE = 5;

    /**
     * Constructor with first subject and its grade
     *
     * @param subject name of subject like Biology
     * @param grade   value of subject grade
     */
    public ReportCard(String subject, int grade) {  // WHAT IS WITH THE WARNINGS: Access can be package-private?
        mSubjects = new ArrayList<>();
        mSubjects.add(subject);
        mGrades = new ArrayList<>();
        grade = checkAndSetGrade(grade);
        mGrades.add(grade);
    }

    /**
     * Constructor with new min and max values
     *
     * @param subject Name of a subject
     * @param grade   grade of a subject
     * @param min     the lowest grade
     * @param max     the greatest grade
     */
    public ReportCard(String subject, int grade, int min, int max) {
        mSubjects = new ArrayList<>();
        mSubjects.add(subject);
        mGrades = new ArrayList<>();
        setMinAndMaxGrade(min, max);
        grade = checkAndSetGrade(grade);
        mGrades.add(grade);
    }

    /**
     * Set grade that is greater or lower than allowed to max or min
     *
     * @param grade subject grade
     */
    private int checkAndSetGrade(int grade) {
        if (grade < MIN_GRADE) {
            grade = MIN_GRADE;
        }
        if (grade > MAX_GRADE) {
            grade = MAX_GRADE;
        }
        return grade;
    }

    /**
     * @return integer value of maximum allowed grade
     */
    public int getMaxGrade() {
        return MAX_GRADE;
    }

    /**
     * @return integer value of minimum allowed grade
     */
    public int getMinGrade() {
        return MIN_GRADE;
    }

    /**
     * @param min integer value of minimum allowed grade
     * @param max integer value of maximum allowed grade
     */
    public void setMinAndMaxGrade(int min, int max) {
        //check if min and max are greater than 0, and set it to min value if it is
        if (min <= 0) {
            min = 1;
        }
        if (max <= 0) {
            max = 1;
        }
        // switch min and max in case user accidentally mix order, so everything will work fine in our code
        if (min < max) {
            MIN_GRADE = min;
            MAX_GRADE = max;
        } else {
            MIN_GRADE = max;
            MAX_GRADE = min;
        }
    }

    public void setMinGrade(int min) {

    }

    /**
     * Adding new subject with grade for a student reportCard
     *
     * @param subject name of a new subject in school
     * @param grade   current grade for that subject
     */
    public void addNewSubjectGrade(String subject, int grade) {
        mSubjects.add(subject);
        grade = checkAndSetGrade(grade);
        mGrades.add(grade);
    }

    /**
     * @return all Subjects in form of ArrayList
     */
    public ArrayList<String> getSubjects() {
        return mSubjects;
    }

    /**
     * @return all grades in form of ArrayList
     */
    public ArrayList<Integer> getGrades() {
        return mGrades;
    }

    /**
     * Set subject name and grade for existing subject
     *
     * @param i       index in ArrayList
     * @param subject new name for a subject
     * @param grade   new grade for a subject
     * @return return 1 if all is good or -1 if index is out of bounds
     */
    public int setSubjectGrade(int i, String subject, int grade) {
        if (i < mSubjects.size() && i >= 0) {
            mSubjects.set(i, subject);
            grade = checkAndSetGrade(grade);
            mGrades.set(i, grade);
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Finding index of ArrayList where is given subject
     *
     * @param subject name of a subject like Biology
     * @return index of arrayList or -1 if there is no match
     */
    public int getSubjectIndex(String subject) {

        subject = subject.toUpperCase(); // set string to upper Case because search is case sensitive and we don't want that
        for (int i = 0; i < mSubjects.size(); i++) {
            String currentSubject = mSubjects.get(i).toUpperCase(); // and now both string are all in Upper Case
            if (currentSubject.equals(subject)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param i is index of arrayList where subject is located
     * @return subject name or message if index is out of bounds
     */
    public String getSubjectName(int i) {
        if (i >= 0 && i < mSubjects.size()) {
            return mSubjects.get(i);
        }
        return "Index out of bounds";
    }

    /**
     * @param i is index of arrayList where subject is located
     * @return integer number of grade or -1 if index is out of bounds
     */
    public int getSubjectGrade(int i) {
        if (i >= 0 && i < mSubjects.size()) {
            return mGrades.get(i);
        }
        return -1;
    }

    /**
     * getter method for grade of subject example: int grade = myRepoCard.getSubjectGrade("History");
     *
     * @param subject is a name of subject like Math or Biology
     * @return Int number of grade for that subject or -1 if there is no match for specific subject
     */
    public int getSubjectGrade(String subject) {
        int grade;
        subject = subject.toUpperCase(); // set string to upper Case because search is case sensitive
        // mSubject and mGrades are always the same size but just to be sure we check index i for both (IS THIS GOOD PRACTICE?)
        for (int i = 0; i < mSubjects.size() && i < mGrades.size(); i++) {
            String currentSubject = mSubjects.get(i).toUpperCase(); // and now both string are all in Upper Case
            if (currentSubject.equals(subject)) {
                grade = mGrades.get(i);
                return grade;
            }
        }
        return -1;
    }

    /**
     * @param i index in arrayList to remove
     * @return integer number 1 if all is ok or -1 if index is out of bounds
     */
    public int deleteSubject(int i) {
        if (i >= 0 && i < mSubjects.size() && i < mGrades.size()) {
            mSubjects.remove(i);
            mGrades.remove(i);
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Returns all Subjects and all grades in ArrayList
     *
     * @return String of all Subjects and all grades in ArrayList or Empty String
     */
    @Override
    public String toString() {
        String readAllSubjectsAndGrades = "";
        for (int i = 0; i < mSubjects.size() && i < mGrades.size(); i++) {
            String subject = mSubjects.get(i);
            String grade = mGrades.get(i).toString();
            readAllSubjectsAndGrades += subject + ": " + grade + "\n";
        }
        return readAllSubjectsAndGrades;
    }
}
