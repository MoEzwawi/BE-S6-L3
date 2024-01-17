package MoEzwawi.BES6L2.repositories;

import MoEzwawi.BES6L2.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BlogPostsRepository extends JpaRepository<BlogPost, UUID> {
}
