package kr.co.tjoeun.makegoodhabbit_20200608.utils;

import android.content.Context;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import kr.co.tjoeun.makegoodhabbit_20200608.MainActivity;
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Project;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServerUtil {



    private static final String BASE_URL = "http://15.164.153.174";

    public interface JsonResponseHandler {

        void onResponse(JSONObject json);

    }

    public static void getRequestProjectById (Context context, int id, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + "/project/" + id).newBuilder();

        String completeUrl = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(completeUrl)
                .header("X-Http-token", ContextUtil.getUserToken(context))
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();

                try {
                    JSONObject json = new JSONObject(body);

                    if(handler != null) {
                        handler.onResponse(json);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    public static void getRequestProjects(Context context, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + "/project").newBuilder();

        String completeUrl = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(completeUrl)
                .header("X-Http-Token", ContextUtil.getUserToken(context))
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();

                try {
                    JSONObject json = new JSONObject(body);

                    if(handler != null) {
                        handler.onResponse(json);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });

    }

    public static void postRequestLogin(Context context, String email, String pw, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/user")
                .post(requestBody)
//                .header()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();

                try {
                    JSONObject jsonObject = new JSONObject(body);

                    if (handler != null) {

                        handler.onResponse(jsonObject);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public static void putRequestSignUp(Context context, String email, String pw, String nickName, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .add("password", pw)
                .add("nick_name", nickName)
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/user")
                .put(requestBody)
//                .header()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                String body = response.body().string();

                Log.d("서버연결성공", body);

                try {
                    JSONObject jsonObject = new JSONObject(body);

                    if(handler != null) {
                        handler.onResponse(jsonObject);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }


}
