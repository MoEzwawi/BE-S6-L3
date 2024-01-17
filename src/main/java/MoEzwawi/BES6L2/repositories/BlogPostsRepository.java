package MoEzwawi.BES6L2.repositories;

import MoEzwawi.BES6L2.entities.BlogPost;
import MoEzwawi.BES6L2.entities.User;
import MoEzwawi.BES6L2.entities.enums.BlogPostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface BlogPostsRepository extends JpaRepository<BlogPost, UUID> {
    List<BlogPost> findByAuthor (User author);
    List<BlogPost> findByCategory (BlogPostCategory category);
    List<BlogPost> findByCategoryAndAuthor (BlogPostCategory category, User Author);
}
