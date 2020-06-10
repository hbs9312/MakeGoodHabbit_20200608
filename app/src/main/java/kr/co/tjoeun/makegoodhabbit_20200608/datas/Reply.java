package kr.co.tjoeun.makegoodhabbit_20200608.datas;

import org.json.JSONException;
import org.json.JSONObject;

public class Reply {

    private int id;
    private int userId;
    private int proofId;
    private String content;
    private String nickName;
    private int likeCount;
    private boolean myLike;
    private int replyCount;


    public static Reply getReplyFromJson(JSONObject jsonObject) {

        Reply rp = new Reply();

        try {
            rp.id = jsonObject.getInt("id");
            rp.userId = jsonObject.getInt("user_id");
            rp.content = jsonObject.getString("content");
            rp.replyCount = jsonObject.getInt("reply_count");
            rp.proofId = jsonObject.getInt("proof_id");
            rp.likeCount = jsonObject.getInt("like_count");
            rp.myLike = jsonObject.getBoolean("my_like");
            if(!jsonObject.isNull("user")) {
                JSONObject user = jsonObject.getJSONObject("user");
                rp.nickName = user.getString("nick_name");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rp;
    }

    public Reply() {
    }

    public Reply(int id, int userId, String content, String nickName) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.nickName = nickName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getProofId() {
        return proofId;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public boolean isMyLike() {
        return myLike;
    }

    public void setProofId(int proofId) {
        this.proofId = proofId;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setMyLike(boolean myLike) {
        this.myLike = myLike;
    }
}
