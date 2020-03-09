package technicalblog.service;

import org.springframework.stereotype.Service;
import technicalblog.model.Post;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @PersistenceUnit(unitName = "techblog")
    private EntityManagerFactory emf;

    public PostService() {
        System.out.println("*** PostService ***");
    }

    public List<Post> getAllPosts() {
        ArrayList<Post> posts = new ArrayList<>();

        EntityManager em = emf.createEntityManager();
        TypedQuery<Post> query = em.createQuery("select p from Post p", Post.class);
        List<Post> resultList = query.getResultList();

//        Connection connection = null;
//        try {
//            Class.forName("org.postgresql.Driver");
//
//            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/technicalBlog", "avinashgoen", "avinashgoen");
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * from posts");
//
//            while (resultSet.next()) {
//                Post post = new Post();
//                post.setTitle(resultSet.getString("title"));
//                post.setBody(resultSet.getString("body"));
//                posts.add(post);
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

        return resultList;

    }

    public ArrayList<Post> getOnePost() {
        ArrayList<Post> posts = new ArrayList<>();

//        Post post1 = new Post();
//        post1.setTitle("This is your Post");
//        post1.setBody("This is your Post");
//        post1.setDate(new Date());

        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/technicalBlog", "avinashgoen", "avinashgoen");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from posts where id = 4");

            while (resultSet.next()) {
                Post post = new Post();
                post.setTitle(resultSet.getString("title"));
                post.setBody(resultSet.getString("body"));
                posts.add(post);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return posts;
    }

    public void createPost(Post newPost) {
    }
}
