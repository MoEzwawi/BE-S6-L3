package MoEzwawi.BES6L2.services;

import MoEzwawi.BES6L2.entities.BlogPost;
import MoEzwawi.BES6L2.entities.enums.BlogPostCategory;
import MoEzwawi.BES6L2.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BlogPostsService {
    private List<BlogPost> blogPosts = new ArrayList<>();

    public List<BlogPost> getBlogPosts() {
        return this.blogPosts;
    }
    public BlogPost findById(int id){
        BlogPost found = null;
        for (BlogPost user: blogPosts){
            if(user.getId()==id){
                found = user;
            }
        }
        if(found==null) throw new NotFoundException();
        return found;
    }
    public BlogPost save(BlogPost body){
        System.out.println("--------------SAVE------------");
        System.out.println(body);
        Random rndm = new Random();
        body.setId(rndm.nextInt(100,999));
        this.blogPosts.add(body);
        return body;
    }
    public BlogPost findByIdAndUpdate(int id, BlogPost body){
        BlogPost found = this.findById(id);
        found.setTitle(body.getTitle());
        found.setContent(body.getContent());
        found.setReadingTime(body.getReadingTime());
        return found;
    }
    public BlogPost patchBlogPost(int id, BlogPost partialBlogPost){
        BlogPost found = this.findById(id);
        if(partialBlogPost.getCategory()!=null) found.setCategory(partialBlogPost.getCategory());
        if(partialBlogPost.getTitle()!=null) found.setTitle(partialBlogPost.getTitle());
        if(partialBlogPost.getContent()!=null) found.setContent(partialBlogPost.getContent());
        if(partialBlogPost.getReadingTime()!=0) found.setReadingTime(partialBlogPost.getReadingTime());
        if(partialBlogPost.getCoverUrl()!=null) found.setCoverUrl(partialBlogPost.getCoverUrl());
        return found;
    }
    public void findByIdAndDelete(int id){
        this.blogPosts.removeIf(current -> current.getId() == id);
    }
    public List<BlogPost> filterByCategory(String category){
        return this.blogPosts.stream()
                .filter(post->post.getCategory().equals(BlogPostCategory.valueOf(category)))
                .toList();
    }
}
