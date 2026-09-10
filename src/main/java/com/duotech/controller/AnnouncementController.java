package com.duotech.controller;

import com.duotech.entity.Announcement;
import com.duotech.repository.AnnouncementRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {
    private final AnnouncementRepository repository;

    public AnnouncementController(AnnouncementRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Announcement> all() {
        return repository.findByActiveTrueOrderByPublishedAtDesc();
    }
}
