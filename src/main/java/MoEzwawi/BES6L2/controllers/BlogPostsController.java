package MoEzwawi.BES6L2.controllers;

import MoEzwawi.BES6L2.dtos.BlogPostDTO;
import MoEzwawi.BES6L2.entities.BlogPost;
import MoEzwawi.BES6L2.entities.enums.BlogPostCategory;
import MoEzwawi.BES6L2.services.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogposts")
public class BlogPostsController {
    @Autowired
    private BlogPostsService blogPostsService;
    @GetMapping
    public List<BlogPost> getBlogPosts(@RequestParam(required = false) BlogPostCategory category, @RequestParam(required = false) UUID authorId){
        if(category==null){
            if(authorId==null){
                return this.blogPostsService.getBlogPosts();
            }else{
                return this.blogPostsService.filterByAuthor(authorId);
            }
        }else{
            if(authorId==null){
                return this.blogPostsService.filterByCategory(category);
            }else{
                return this.blogPostsService.filterByAuthorAndCategory(category,authorId);
            }
        }
    }
    @GetMapping("/{id}")
    public BlogPost getById(@PathVariable UUID id){
        return this.blogPostsService.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // status code 201
    public BlogPost save(@RequestBody BlogPostDTO body){
        return this.blogPostsService.save(body);
    }
    @PutMapping("/{id}")
    public BlogPost update(@PathVariable UUID id, @RequestBody BlogPost body){
        return this.blogPostsService.findByIdAndUpdateContent(id, body);
    }
    @PatchMapping("/{id}")
    public BlogPost patchBlogPost(@PathVariable UUID id, @RequestBody BlogPost partialBlogPost){
        return this.blogPostsService.patchBlogPost(id, partialBlogPost);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // status code 204
    public void findByIdAndDelete(@PathVariable UUID id){
        this.blogPostsService.findByIdAndDelete(id);
    }
}
