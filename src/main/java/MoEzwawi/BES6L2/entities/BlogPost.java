package MoEzwawi.BES6L2.entities;

import MoEzwawi.BES6L2.entities.enums.BlogPostCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BlogPost {
    private int id;
    private String title;
    private BlogPostCategory category;
    private String coverUrl;
    private String content;
    private int readingTime;

}
