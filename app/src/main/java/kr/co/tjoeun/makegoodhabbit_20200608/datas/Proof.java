package kr.co.tjoeun.makegoodhabbit_20200608.datas;

import org.json.JSONException;
import org.json.JSONObject;

public class Proof {

    private int id;
    private String content;
    private String imgUrl;


    public static Proof getProofFromJson(JSONObject jsonObject) {
        Proof pr = new Proof();

        try {
            pr.id = jsonObject.getInt("id");
            pr.content = jsonObject.getString("content");
            pr.imgUrl = jsonObject.getString("img_url");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pr;
    }

    public Proof() {

    }

    public Proof(int id, String content, String imgUrl) {
        this.id = id;
        this.content = content;
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
