package MoEzwawi.BES6L2.controllers;

import MoEzwawi.BES6L2.entities.BlogPost;
import MoEzwawi.BES6L2.services.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogposts")
public class BlogPostsController {
    @Autowired
    private BlogPostsService blogPostsService;
    @GetMapping
    public List<BlogPost> getBlogPosts(@RequestParam(required = false) String category){
        if(category!=null){
            return this.blogPostsService.filterByCategory(category);
        }else {
            return this.blogPostsService.getBlogPosts();
        }
    }
    @GetMapping("/{id}")
    public BlogPost getById(@PathVariable int id){
        return this.blogPostsService.findById(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // status code 201
    public BlogPost save(@RequestBody BlogPost body){
        return this.blogPostsService.save(body);
    }
    @PutMapping("/{id}")
    public BlogPost update(@PathVariable int id, @RequestBody BlogPost body){
        return this.blogPostsService.findByIdAndUpdate(id, body);
    }
    @PatchMapping("/{id}")
    public BlogPost patchBlogPost(@PathVariable int id, @RequestBody BlogPost partialBlogPost){
        return this.blogPostsService.patchBlogPost(id, partialBlogPost);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // status code 204
    public void findByIdAndDelete(@PathVariable int id){
        this.blogPostsService.findByIdAndDelete(id);
    }
}
