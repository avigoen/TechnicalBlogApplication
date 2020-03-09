package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import technicalblog.model.Post;
import technicalblog.model.UserProfile;
import technicalblog.model.Users;
import technicalblog.service.PostService;
import technicalblog.service.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @RequestMapping("users/login")
    public String login() {
        return "users/login";
    }

    @RequestMapping("users/registration")
    public String registration(Model model) {
        Users users = new Users();
        UserProfile profile = new UserProfile();
        users.setProfile(profile);

        model.addAttribute("Users", users);
        return "users/registration";
    }

    @RequestMapping(value = "users/login", method = RequestMethod.POST)
    public String loginUser(Users users) {
        if (userService.login(users)) {
            return "redirect:/posts";
        } else {
            return "users/login";
        }
    }

    @RequestMapping(value = "users/logout", method = RequestMethod.POST)
    public String logout(Model model) {

        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);

        return "index";
    }

    @RequestMapping(value = "users/registration", method = RequestMethod.POST)
    public String registerUser(Users users) {
        userService.registerUser(users);
        return "users/login";
    }
}
