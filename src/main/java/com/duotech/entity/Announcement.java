package com.duotech.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "announcements")
public class Announcement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 2000)
    private String content;
    private LocalDateTime publishedAt = LocalDateTime.now();
    private boolean active = true;

    public Announcement() {}
    public Announcement(String title, String content) {
        this.title = title; this.content = content;
    }
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getPublishedAt() { return publishedAt; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
