package com.github.fil1n.models;

import javax.persistence.*;

@Entity
@Table(name = "communication_type")
public class CommunicationType {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "chat_id")
    private String chatId;

    @ManyToOne
    @JoinColumn(name = "knot_id", referencedColumnName = "id")
    private Group knot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return knot;
    }

    public void setGroup(Group group) {
        this.knot = group;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public CommunicationType() {}
}
