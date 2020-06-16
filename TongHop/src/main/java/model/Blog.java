package model;

import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;

@Entity
@Table()
public class Blog{

    @Id
    private Long id;

    private String title;

    @Column(length = 100000)
    private String content;

    @OneToOne(targetEntity = Author.class)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    private Category category;

    private String avatar;

    public Blog() {
    }

    public Blog(Long id, String title, String content, Author author, Category category, String avatar) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.avatar = avatar;
    }

    public Blog(String title, String content, Author author, Category category, String avatar) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
