package kr.co.tjoeun.makegoodhabbit_20200608.datas;

import org.json.JSONException;
import org.json.JSONObject;

public class Image {
    private int id;
    private int proof_id;
    private String imgUrl;

    public static Image getImageFromJson(JSONObject jsonObject) {

        Image img = new Image();

        try {
            img.id = jsonObject.getInt("id");
            img.proof_id = jsonObject.getInt("proof_id");
            img.imgUrl = jsonObject.getString("img_url");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return img;
    }

    public Image() {

    }

    public Image(int id, int proof_id, String img_url) {
        this.id = id;
        this.proof_id = proof_id;
        this.imgUrl = img_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProof_id() {
        return proof_id;
    }

    public void setProof_id(int proof_id) {
        this.proof_id = proof_id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
