package ru.ne1ghost.blog.service;

import ru.ne1ghost.blog.entities.Post;
import ru.ne1ghost.blog.entities.User;
import ru.ne1ghost.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean deletePost(Long postId) {
        Post thePost = postRepository.findOne(postId);
        if (thePost == null)
            return false;
        postRepository.delete(postId);
        return true;
    }

    public Post getPost(Long id) {
        return postRepository.findOne(id);
    }

    public Post find(Long postId) {
        return postRepository.findOne(postId);
    }
}
