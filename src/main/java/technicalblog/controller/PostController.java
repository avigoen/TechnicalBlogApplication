package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import technicalblog.model.Category;
import technicalblog.model.Post;
import technicalblog.model.Users;
import technicalblog.service.PostService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("posts")
    public String getUserPosts(Model model) {

        List<Post> posts = postService.getAllPosts();
//        Post latestPost = postService.getOnePost();
//        posts.add(latestPost);
        model.addAttribute("posts", posts);
        return "posts";
    }

    @RequestMapping("/posts/newpost")
    public String newPost() {
        return "posts/create";
    }

    @RequestMapping(value = "/posts/create", method = RequestMethod.POST)
    public String createPost(Post newPost, HttpSession session) {
        Users users = (Users) session.getAttribute("loggedUser");
        newPost.setUsers(users);

        getCategories(newPost);

        postService.createPost(newPost);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/editPost", method = RequestMethod.GET)
    public String editPost(@RequestParam(name = "postId") Integer postId, Model model) {
        Post post = postService.getPost(postId);
        model.addAttribute("post", post);
        return "/posts/edit";
    }

    @RequestMapping(value = "/editPost", method = RequestMethod.PUT)
    public String editPostSubmit(@RequestParam(name = "postId") Integer postId, Post updatedPost, HttpSession session) {
        Users users = (Users) session.getAttribute("loggedUser");
        updatedPost.setUsers(users);
        updatedPost.setId(postId);

        getCategories(updatedPost);

        postService.updatePost(updatedPost);
        return "redirect:/posts";
    }

    private void getCategories(Post post) {
        if (post.getSpringBlog() != null) {
            Category springBlogCategory = new Category();
            springBlogCategory.setCategory(post.getSpringBlog());
            post.getCategories().add(springBlogCategory);
        }
        if (post.getJavaBlog() != null) {
            Category javaBlogCategory = new Category();
            javaBlogCategory.setCategory(post.getJavaBlog());
            post.getCategories().add(javaBlogCategory);
        }
    }

    @RequestMapping(value = "/deletePost", method = RequestMethod.DELETE)
    public String deletePostSubmit(@RequestParam(name = "postId") Integer postId) {
        postService.deletePost(postId);
        return "redirect:/posts";
    }
}
