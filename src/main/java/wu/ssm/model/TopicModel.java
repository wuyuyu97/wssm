package wu.ssm.model;

import java.util.ArrayList;

public class TopicModel {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private String createdTime ;
    private String updatedTime ;
    private Integer boardId;

    private ArrayList<TopicCommentModel> commentList;
    private ArrayList<TopicModel> titleList;

    public void setCommentList(ArrayList<TopicCommentModel> commentList) {
        this.commentList = commentList;
    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public ArrayList<TopicCommentModel> getCommentList() {
        return commentList;
    }

    public ArrayList<TopicModel> getTitleList() {
        return titleList;
    }

    public void setTitleList(ArrayList<TopicModel> titleList) {
        this.titleList = titleList;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }
}
