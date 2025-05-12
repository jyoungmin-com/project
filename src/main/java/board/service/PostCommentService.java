package board.service;

import board.entity.Post;
import board.entity.PostComment;
import board.entity.User;
import board.repository.PostCommentRepository;
import board.repository.PostRepository;
import board.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import board.dto.PostCommentDto;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostCommentService {
    private final PostCommentRepository postCommentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void registerPostComment(PostCommentDto postCommentDto) {
        Post post = postRepository.findPostById(postCommentDto.getPid());
        PostComment postComment = PostComment.of(postCommentDto.getContent(), post, postCommentDto.getUserDto().toEntity());
        postCommentRepository.save(postComment);

//        User user = userRepository.getReferenceById(postCommentDto.getUserDto().getUid());
//        Post post = postRepository.findPostById(postCommentDto.getPid());
//        PostComment postComment = postCommentDto.toEntity(post, user);
//        postCommentRepository.save(postComment);
    }

    @Transactional
    public void deletePostComment(Long pcid, String uid) {
        postCommentRepository.deleteByIdAndUser_Uid(pcid, uid);

    }

    public List<PostCommentDto> getCommentByPid(Long pid) {
//		PostDto post = PostDto.from(postRepository.findPostById(pid));
//		List<PostCommentDto> postCommentDto = PostCommentDto.from(postCommentRepository.findAllByPost_Id(pid));
//		return postCommentDto;
        return PostCommentDto.from(postCommentRepository.findAllByPost_IdOrderByCreatedDateDesc(pid));
    }

}
