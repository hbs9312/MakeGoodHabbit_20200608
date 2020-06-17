package kr.co.tjoeun.makegoodhabbit_20200608.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import kr.co.tjoeun.makegoodhabbit_20200608.MainActivity;
import kr.co.tjoeun.makegoodhabbit_20200608.datas.Project;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
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


    public static void getRequestReview (Context context, int pageNum, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + "/review").newBuilder();
        urlBuilder.addEncodedQueryParameter("page_num", pageNum + "");

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

    public static void getRequestProjectByDate (Context context, int projectId, String date, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + "/project/" + projectId).newBuilder();
        urlBuilder.addEncodedQueryParameter("proof_date", date);


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

    public static void getRequestProofDetail (Context context, int proofId, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + "/project_proof/" + proofId).newBuilder();

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

    public static void getRequestUserInfo (Context context, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + "/my_info").newBuilder();

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

    public static void getRequestUserInfo (Context context, String status, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + "/my_info").newBuilder();
        urlBuilder.addEncodedQueryParameter("project_status", status);

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

    public static void deleteRequestProject(Context context, int projectId, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + "/project").newBuilder();
        urlBuilder.addEncodedQueryParameter("project_id", projectId+"");

        String completeUrl = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(completeUrl)
                .header("X-Http-Token", ContextUtil.getUserToken(context))
                .delete()
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

    public static void postRequestReply(Context context, String content, int proofId, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("content", content)
                .add("proof_id", proofId+ "")
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/proof_reply")
                .post(requestBody)
                .header("X-Http-Token", ContextUtil.getUserToken(context))
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

    public static void postRequestLikeReply(Context context, int proofId, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("proof_reply_id", proofId+ "")
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/like_proof_reply")
                .post(requestBody)
                .header("X-Http-Token", ContextUtil.getUserToken(context))
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


    public static void postRequestJoin(Context context, int projectId, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("project_id", projectId+"")
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/project")
                .post(requestBody)
                .header("X-Http-Token", ContextUtil.getUserToken(context))
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

    public static void postRequestProjectProof(Context context, String token, int projectId, String content, File proofImg, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("proof_images", proofImg.getName(), RequestBody.create(MediaType.parse("image/jpg"),proofImg))
                .addFormDataPart("project_id", projectId+"")
                .addFormDataPart("content", content)
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/project_proof")
                .post(requestBody)
                .header("X-Http-Token", token)
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

    public static void putRequestUserPoflieImage (Context context, String token, File profileImage, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("profile_images",profileImage.getName(), RequestBody.create(MediaType.parse("image/jpg"),profileImage))
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/user_profile_image")
                .put(requestBody)
                .header("X-Http-Token", token)
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

    public static void postRequestLikeProof(Context context, String token, int proofId, final JsonResponseHandler handler) {

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("proof_id", proofId + "")
                .build();

        Request request = new Request.Builder()
                .url(BASE_URL + "/like_proof")
                .post(requestBody)
                .header("X-Http-Token", token)
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
