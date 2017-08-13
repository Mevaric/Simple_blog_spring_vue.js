package ru.ne1ghost.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ne1ghost.blog.entities.Post;
import ru.ne1ghost.blog.entities.User;
import ru.ne1ghost.blog.repositories.PostRepository;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void insert(Post post) {
        postRepository.save(post);
    }

    public List<Post> findByUser(User user) {
        return postRepository.findByCreatorId(user.getId());
    }
}
