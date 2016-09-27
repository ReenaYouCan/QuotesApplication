package com.reamu.yourquotes.models;

/**
 * Created by Reena on 08-06-2016.
 */
public class QuotesModel {
    int srNo;
    String QuoteTxt;
    String AuthorName;
    String Topic;

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public void setQuoteTxt(String quoteTxt) {
        QuoteTxt = quoteTxt;
    }

    public String getQuoteTxt() {
        return QuoteTxt;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getTopic() {
        return Topic;
    }
}
