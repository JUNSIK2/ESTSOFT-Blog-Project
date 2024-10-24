package com.estsoft.springproject.blog.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    private String title;
    private String body;

    @Override
    public String toString() {
        return "Content{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
