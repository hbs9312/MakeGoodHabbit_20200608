package kr.co.tjoeun.makegoodhabbit_20200608.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ContextUtil {

    private static final String preftName = "MakeGoodHabitPref";
    private static final String LOGIN_USER_TOKEN = "LOGIN_USER_TOKEN";
    private static final String IS_AUTO_LOGIN = "IS_AUTO_LOGIN";

    public static void setUserToken (Context context, String token) {
        SharedPreferences pref = context.getSharedPreferences(preftName, Context.MODE_PRIVATE);
        pref.edit().putString(LOGIN_USER_TOKEN, token).apply();
    }

    public static String getUserToken (Context context) {
        SharedPreferences pref = context.getSharedPreferences(preftName, Context.MODE_PRIVATE);
        return pref.getString(LOGIN_USER_TOKEN, "");
    }

    public static void deleteUserToken(Context context) {
        SharedPreferences pref = context.getSharedPreferences(preftName,Context.MODE_PRIVATE);
        pref.edit().putString(LOGIN_USER_TOKEN, "").apply();
    }

    public static void setIsAutoLogin (Context context, boolean isAutoLogin) {
        SharedPreferences pref = context.getSharedPreferences(preftName, Context.MODE_PRIVATE);
        pref.edit().putBoolean(IS_AUTO_LOGIN, isAutoLogin).apply();
    }

    public static boolean getIsAutoLogin (Context context) {
        SharedPreferences pref = context.getSharedPreferences(preftName, Context.MODE_PRIVATE);
        return pref.getBoolean(IS_AUTO_LOGIN, false);
    }

}
