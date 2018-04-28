package com.example.saadiqbal.ht_studentmodule;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Saad Iqbal on 3/3/2018.

 */

public class StudentCurrentTuitionsModel {
    public String TutName, TutPhone, TutQual, CourseName, TutorRating;
    public Integer ReqID;

    public Integer getReqID() {
        return ReqID;
    }

    public void setReqID(Integer reqID) {
        ReqID = reqID;
    }

    public String getStudentID() {
        return TutName;
    }

    public void setStudentID(String StudentID) {
        this.TutName = StudentID;
    }

    public String getStudentName() {
        return TutPhone;
    }

    public void setStudentName(String StudentName) {
        this.TutPhone = StudentName;
    }

    public String getStudentPhone() {
        return TutQual;
    }

    public void setStudentPhone(String StudentPhone) {
        this.TutQual = StudentPhone;
    }

    public String getStudentStatus() {
        return CourseName;
    }

    public void setStudentStatus(String StudentStatus) {
        this.CourseName = StudentStatus;
    }

    public String getStduentRequesTime() {
        return TutorRating;
    }

    public void setStduentRequesTime(String StduentRequesTime) {
        this.TutorRating = StduentRequesTime;
    }

    public StudentCurrentTuitionsModel(String StudentID, String StudentName, String StudentPhone, String StudentStatus, String StduentRequesTime) {
        this.TutName = StudentID;
        this.TutPhone = StudentName;
        this.TutQual = StudentPhone;
        this.CourseName = StudentStatus;
        this.TutorRating = StduentRequesTime;
    }
    public StudentCurrentTuitionsModel(JSONObject jsonObject)
    {
        try {
            this.TutName = jsonObject.getString("TutName");
            this.TutPhone = jsonObject.getString("TutPhone");
            this.TutQual = jsonObject.getString("TutQual");
            this.CourseName = jsonObject.getString("CourseName");
            this.TutorRating = jsonObject.getString("TutorRating");
            this.ReqID = jsonObject.getInt("idRequest");

        }
        catch (JSONException e) {
            e.printStackTrace();
            Log.v("CurrentTuition "," error "+ e.getLocalizedMessage());
        }
    }
}
