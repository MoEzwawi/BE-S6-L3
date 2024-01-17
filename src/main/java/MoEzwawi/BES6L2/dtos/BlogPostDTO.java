package MoEzwawi.BES6L2.dtos;

import MoEzwawi.BES6L2.entities.enums.BlogPostCategory;
import jakarta.persistence.Column;
import lombok.Getter;

import java.util.UUID;
@Getter
public class BlogPostDTO {
    private String title;
    private BlogPostCategory category;
    private String coverUrl;
    private String content;
    private UUID authorId;
}
