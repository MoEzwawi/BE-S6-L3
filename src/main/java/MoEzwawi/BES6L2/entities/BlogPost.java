package MoEzwawi.BES6L2.entities;

import MoEzwawi.BES6L2.entities.enums.BlogPostCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "blog_posts")
public class BlogPost {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    private UUID id;
    private String title;
    @Enumerated(EnumType.STRING)
    private BlogPostCategory category;
    @Column(name = "cover_url")
    private String coverUrl;
    @Column(length = 1000)
    private String content;
    @Column(name = "reading_time")
    private int readingTime;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id",nullable = false)
    private User author;

    @Override
    public String toString() {
        return "BlogPost{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", coverUrl='" + coverUrl + '\'' +
                ", content='" + content + '\'' +
                ", readingTime=" + readingTime +
                ", author=" + author.getSurname() +
                '}';
    }
}
