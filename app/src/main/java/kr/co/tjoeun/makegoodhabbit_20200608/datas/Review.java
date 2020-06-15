package kr.co.tjoeun.makegoodhabbit_20200608.datas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Review implements Serializable {

    private int id;
    private int projectApplyId;
    private String title;
    private String content;
    private String nickName;

    public static Review getReviewFromJson(JSONObject jsonObject) {

        Review review = new Review();
        try {
            review.id = jsonObject.getInt("id");
            review.title = jsonObject.getString("title");
            review.content = jsonObject.getString("content");
            review.projectApplyId = jsonObject.getInt("project_apply_id");

            if(!jsonObject.isNull("writer")) {

                JSONObject writer = jsonObject.getJSONObject("writer");
                review.nickName = writer.getString("nick_name");

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return review;

    }

    public void Review() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProject_apply_id() {
        return projectApplyId;
    }

    public void setProject_apply_id(int project_apply_id) {
        this.projectApplyId = project_apply_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getProjectApplyId() {
        return projectApplyId;
    }

    public void setProjectApplyId(int projectApplyId) {
        this.projectApplyId = projectApplyId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
