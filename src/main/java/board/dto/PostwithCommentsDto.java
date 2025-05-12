package board.dto;

import board.entity.Post;
import board.entity.constant.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@ToString
public class PostwithCommentsDto {
    private Long id;
    private String title;
    private String content;
    private CategoryType categoryType;
    private UserDto userDto;
    private Set<PostCommentDto> postComments;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime modifiedDate;
    private String modifiedBy;


    public static PostwithCommentsDto of(Long id, String title, String content, CategoryType categoryType, UserDto userDto, Set<PostCommentDto> postComments, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy) {
        return PostwithCommentsDto.of(
                id, title, content, categoryType, userDto, postComments, createdDate, createdBy, modifiedDate, modifiedBy);
    }

    public static PostwithCommentsDto from(Post post) {
        return PostwithCommentsDto.of(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCategoryType(),
                UserDto.from(post.getUser()),
                post.getPostComments().stream().map(PostCommentDto::from).collect(Collectors.toCollection(LinkedHashSet::new)),
                post.getCreatedDate(),
                post.getCreatedBy(),
                post.getModifiedDate(),
                post.getModifiedBy()
        );
    }
}
