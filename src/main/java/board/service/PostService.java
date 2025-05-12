package board.service;

import board.dto.PostwithCommentsDto;
import board.dto.response.PostResponse;
import board.entity.Post;
import board.entity.User;
import board.repository.PostRepository;
import board.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import board.dto.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostDto getPost(Long pid) {
        PostDto post = PostDto.from(postRepository.findPostById(pid));
        return post;
    }

    public Map<String, Long> hasPrevNextPost(Long pid) {

        Map<String, Long> result = new HashMap<>();

        Post prev = postRepository.findFirstByIdLessThanOrderByIdDesc(pid);
        Post next = postRepository.findFirstByIdGreaterThanOrderByIdAsc(pid);

        result.put("prev", prev != null ? prev.getId() : null);
        result.put("next", next != null ? next.getId() : null);

        return result;
    }

    public List<PostDto> getAllPost() {

        List<PostDto> postList = postRepository.findAll().stream()
                .map(PostDto::from)
                .toList();
//        System.out.println(postList);
        return postList;
    }


    @Transactional
    public void registerPost(PostDto postDto) {

//        User user = userRepository.findById(postDto.getUserDto().getUid()).orElse(null);
        User user = userRepository.getReferenceById(postDto.getUserDto().getUid()); // lazy
        Post post = postDto.toEntity(user);
        postRepository.save(post);
    }

    public void updatePost(Long pid, PostDto postDto) {

        Post post = postRepository.findPostById(pid);
        if (post != null && postDto != null && postDto.getUserDto().getUid() == post.getUser().getUid()) {
            post.setTitle(postDto.getTitle());
            post.setContent(postDto.getContent());
            post.setCategoryType(postDto.getCategoryType());
            postRepository.save(post);
        }

    }

    @Transactional
    public void deletePost(long pid, String uid) {

        postRepository.deleteByIdAndUser_Uid(pid, uid);
//        if (postRepository.findPostById(pid).getId() == Long.valueOf(uid)) {
//            postRepository.deleteById(pid);
//        }

    }

    public Page<PostDto> pagingPosts(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(PostDto::from);
    }

    @Transactional
    public PostwithCommentsDto getPostWithComments(Long pid) {
        return PostwithCommentsDto.from(postRepository.findPostById(pid));
    }


    public Page<PostDto> searchPost(String searchType, String searchValue, Pageable pageable) {
        Page<PostDto> postList = null;

//        if (searchType == "title") {
//            postList = postRepository.findPostsByTitleIsLikeIgnoreCase(searchValue).stream().map(PostDto::from).toList();
//        } else if (searchType == "content") {
//            postList = postRepository.findPostsByContentIsLikeIgnoreCase(searchValue).stream().map(PostDto::from).toList();
//        } else if (searchType=="uid") {
//            postList = postRepository.findPostsByUser_Uid(searchValue).stream().map(PostDto::from).toList();
//        }


        switch (searchType) {
            case "title" -> {
                postList = postRepository.findByTitleContainingIgnoreCase(searchValue, pageable).map(PostDto::from);
            }
            case "content" -> {
                postList = postRepository.findByContentContainingIgnoreCase(searchValue, pageable).map(PostDto::from);
            }
            case "uid" -> {
                postList = postRepository.findByUser_Uid(searchValue, pageable).map(PostDto::from);
            }
            default -> {
                postList = postRepository.findAll(pageable).map(PostDto::from);
            }

        }


        return postList;
    }


}
