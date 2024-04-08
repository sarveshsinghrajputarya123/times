/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.times.controller;

/**
 *
 * @author acer
 */
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/getTimeStories")
    public List<Story> getTimeStories() {
        List<Story> stories = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://time.com/").get();
            Elements storyElements = doc.select("a.headline");
            for (Element element : storyElements.subList(0, Math.min(6, storyElements.size()))) {
                String title = element.text();
                String link = element.attr("href");
                stories.add(new Story(title, link));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stories;
    }

    static class Story {
        private String title;
        private String link;

        public Story(String title, String link) {
            this.title = title;
            this.link = link;
        }

        // Getters and setters
    }
}