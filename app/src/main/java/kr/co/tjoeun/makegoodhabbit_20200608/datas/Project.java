package kr.co.tjoeun.makegoodhabbit_20200608.datas;

import org.json.JSONException;
import org.json.JSONObject;

public class Project {

    private int id;
    private String title;
    private String imgUrl;
    private String description;
    private boolean isJoin;

    public static Project getProjectFromJson(JSONObject jsonObject) {
        Project project = new Project();

        try {
            project.id = jsonObject.getInt("id");
            project.title = jsonObject.getString("title");
            project.imgUrl = jsonObject.getString("img_url");
            project.description = jsonObject.getString("description");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return project;
    }

    public Project() {

    }

    public Project(int id, String title, String imgUrl) {
        this.id = id;
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isJoin() {
        return isJoin;
    }

    public void setJoin(boolean join) {
        isJoin = join;
    }
}
