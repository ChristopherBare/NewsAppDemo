package com.christopherbare.newdemothingnews;

public class Article {
    String authorName, releaseDate, title, imageURL, articleURL, description;

    public Article(String authorName, String releaseDate, String title, String imageURL, String articleURL, String description) {
        this.authorName = authorName;
        this.releaseDate = releaseDate;
        this.title = title;
        this.imageURL = imageURL;
        this.articleURL = articleURL;
        this.description = description;
    }

    public Article(String authorName, String releaseDate, String title, String imageURL, String articleURL) {
        this.authorName = authorName;
        this.releaseDate = releaseDate;
        this.title = title;
        this.imageURL = imageURL;
        this.articleURL = articleURL;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getArticleURL() {
        return articleURL;
    }

    public void setArticleURL(String articleURL) {
        this.articleURL = articleURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Article{" +
                "authorName='" + authorName + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", title='" + title + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", articleURL='" + articleURL + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
