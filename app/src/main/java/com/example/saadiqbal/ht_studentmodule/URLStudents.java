package com.example.saadiqbal.ht_studentmodule;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Saad Iqbal on 12/28/2017.
 */

public class URLStudents {
    public static final  String URL_LOGIN = "http://hometuition.ahmedgraf.com/HomeTuition/Test1/login";
    public static final  String URL_Registration = "http://hometuition.ahmedgraf.com/HomeTuition/Test1/register";
    public static final String URL_Settings = "http://hometuition.ahmedgraf.com/HomeTuition/Test1/studentProfile";
    public static final String URL_StudentLangLat = "http://hometuition.ahmedgraf.com/HomeTuition/Test1/studentlonglat";
    public static final String URL_SendRequest2Tutor = "http://hometuition.ahmedgraf.com/HomeTuition/Test1/sendRequestTutor";
    public static final String URL_StudentFCMupdate ="http://hometuition.ahmedgraf.com/HomeTuition/Test1/updateStudent_FCMKey";
    public static final String URL_TutorGetInfo ="http://hometuition.ahmedgraf.com/HomeTuition/Test1/getNearestTutor";
    public static final String URL_AutoFillCourses ="http://hometuition.ahmedgraf.com/HomeTuition/Test1/CoursesAutoFill";
    public static final String URL_StrudnetRequestCencel ="http://hometuition.ahmedgraf.com/HomeTuition/Test1/cencelRequestStudent";
    public static final String URL_StudentCourses ="http://hometuition.ahmedgraf.com/HomeTuition/Test1/StudentCourse";
    public static final String URL_FinishTuition ="http://hometuition.ahmedgraf.com/HomeTuition/Test1/finishTuition";
    public static final String URL_CustomizeRequestTutor ="http://hometuition.ahmedgraf.com/HomeTuition/Test1/customizeSendRequestTutor";
    public static final String URL_PasswordUpdate ="http://hometuition.ahmedgraf.com/HomeTuition/Test1/updateStudent_Password";

    //http://hometuition.ahmedgraf.com/HomeTuition/Test1/customizeSendRequestTutor




    public  static  String getPhoneNo (Context context)
    {
        SharedPreferences shared = context.getSharedPreferences(Login.PREFS_NAME , MODE_PRIVATE);
        String number = (shared.getString(Login.PREF_UNAME, ""));
        return number;
    }
}
