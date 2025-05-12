package board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import board.entity.PostComment;
import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long>{


    List<PostComment> findAllByPost_Id(Long postId);

    List<PostComment> findAllByPost_IdOrderByCreatedDateDesc(Long postId);

    void deleteByIdAndUser_Uid(Long id, String userUid);
}
