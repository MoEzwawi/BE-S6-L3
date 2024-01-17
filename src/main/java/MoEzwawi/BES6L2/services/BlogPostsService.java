package MoEzwawi.BES6L2.services;

import MoEzwawi.BES6L2.dtos.BlogPostDTO;
import MoEzwawi.BES6L2.entities.BlogPost;
import MoEzwawi.BES6L2.entities.User;
import MoEzwawi.BES6L2.exceptions.NotFoundException;
import MoEzwawi.BES6L2.repositories.BlogPostsRepository;
import MoEzwawi.BES6L2.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogPostsService {
    @Autowired
    private BlogPostsRepository blogPostsRepository;
    @Autowired
    private UsersService usersService;
    public List<BlogPost> getBlogPosts() {
        return this.blogPostsRepository.findAll();
    }
    public BlogPost findById(UUID id){
        return this.blogPostsRepository.findById(id).orElseThrow(NotFoundException::new);
    }
    public BlogPost save(BlogPostDTO body){
        UUID userId = body.getAuthorId();
        User author = this.usersService.findById(userId);
        BlogPost newBlogPost = new BlogPost();
        newBlogPost.setAuthor(author);
        String title = body.getTitle() == null ? "New blog post" : body.getTitle();
        newBlogPost.setTitle(title);
        newBlogPost.setCategory(body.getCategory());
        String content = body.getContent() == null ? "" : body.getContent();
        newBlogPost.setContent(content);
        String cUrl = body.getCoverUrl() == null ? "https://picsum.photos/200/300" : body.getCoverUrl();
        newBlogPost.setCoverUrl(cUrl);
        int readingMinutes = content.length()/60;
        newBlogPost.setReadingTime(readingMinutes);
        blogPostsRepository.save(newBlogPost);
        return newBlogPost;
    }
    public BlogPost findByIdAndUpdateContent(UUID id, BlogPost body){
        BlogPost found = this.findById(id);
        found.setContent(body.getContent());
        return found;
    }
    public BlogPost patchBlogPost(UUID id, BlogPost partialBlogPost){
        BlogPost found = this.findById(id);
        if(partialBlogPost.getCategory()!=null) found.setCategory(partialBlogPost.getCategory());
        if(partialBlogPost.getTitle()!=null) found.setTitle(partialBlogPost.getTitle());
        if(partialBlogPost.getContent()!=null) found.setContent(partialBlogPost.getContent());
        if(partialBlogPost.getReadingTime()!=0) found.setReadingTime(partialBlogPost.getReadingTime());
        if(partialBlogPost.getCoverUrl()!=null) found.setCoverUrl(partialBlogPost.getCoverUrl());
        return found;
    }
    public void findByIdAndDelete(UUID id){
        BlogPost found = this.findById(id);
        this.blogPostsRepository.delete(found);
    }
/*    public List<BlogPost> filterByCategory(String category){

    }*/
}
