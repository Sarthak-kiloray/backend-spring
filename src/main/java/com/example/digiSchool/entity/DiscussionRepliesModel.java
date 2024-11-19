package com.example.digiSchool.entity;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name = "discussion_replies")
public class DiscussionRepliesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Integer replyId;

    // Many replies can belong to one discussion
    @ManyToOne
    @JoinColumn(name = "discussion_id", nullable = false)
    private DiscussionModel discussion;

    // Many replies can be made by one user
    @ManyToOne
    @JoinColumn(name = "replied_by", nullable = false)
    private UserModel repliedBy;

    // Text of the reply, stored as TEXT for larger content
    @Column(name = "reply_text", columnDefinition = "TEXT", nullable = false)
    private String replyText;

    // Getters and Setters

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public DiscussionModel getDiscussion() {
        return discussion;
    }

    public void setDiscussion(DiscussionModel discussion) {
        this.discussion = discussion;
    }

    public UserModel getRepliedBy() {
        return repliedBy;
    }

    public void setRepliedBy(UserModel repliedBy) {
        this.repliedBy = repliedBy;
    }

    public String getReplyText() {
        return replyText;
    }

    public void setReplyText(String replyText) {
        this.replyText = replyText;
    }
}