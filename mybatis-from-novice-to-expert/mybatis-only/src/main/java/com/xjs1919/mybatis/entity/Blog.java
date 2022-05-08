package com.xjs1919.mybatis.entity;

import java.util.List;

/**
 * @author jiashuai.xujs
 * @date 2022/2/28 19:00
 */
public class Blog {

    private Integer id;
    private String title;

    private List<Article> articles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", articles=" + articles +
                '}';
    }
}
