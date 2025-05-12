package board.repository;

import board.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import board.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

    Post findPostById(Long id);


//    @Modifying
//    @Query("DELETE FROM Post p WHERE p.id = :pid AND p.user.uid = :uid")
//    void deleteByIdAndUser_Uid(@Param("pid") Long id, @Param("uid") String uid);


    void deleteByIdAndUser_Uid(Long id, String userUid);

    Post findFirstByIdGreaterThanOrderByIdAsc(Long idIsGreaterThan);

    Post findFirstByIdLessThanOrderByIdDesc(Long idIsLessThan);

    Long id(Long id);

    Page<Post> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Post> findByContentContainingIgnoreCase(String content, Pageable pageable);

    Page<Post> findByUser_Uid(String userUid, Pageable pageable);
}
