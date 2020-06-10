package kr.co.tjoeun.makegoodhabbit_20200608.datas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Proof {

    private int id;
    private String content;
    private int likeCount;
    private boolean myLike;
    private List<Image> imgList = new ArrayList<>();


    public static Proof getProofFromJson(JSONObject jsonObject) {
        Proof pr = new Proof();

        try {
            pr.id = jsonObject.getInt("id");
            pr.content = jsonObject.getString("content");
            pr.likeCount = jsonObject.getInt("like_count");
            pr.myLike = jsonObject.getBoolean("my_like");

            if (!jsonObject.isNull("images")) {

                JSONArray images = jsonObject.getJSONArray("images");

                for(int i=0; i< images.length();i++) {
                    JSONObject imageObj = images.getJSONObject(i);
                    Image tempImg = Image.getImageFromJson(imageObj);
                    pr.imgList.add(tempImg);
                }

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pr;
    }

    public Proof() {

    }

    public Proof(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Image> getImgList() {
        return imgList;
    }

    public void setImgList(List<Image> imgList) {
        this.imgList = imgList;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public boolean isMyLike() {
        return myLike;
    }

    public void setMyLike(boolean myLike) {
        this.myLike = myLike;
    }
}
