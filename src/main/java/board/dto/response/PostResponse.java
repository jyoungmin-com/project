package board.dto.response;

import board.dto.PostDto;
import board.entity.constant.CategoryType;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private CategoryType categoryType;
    private String createdBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String uid;


    public static PostResponse of(Long id, String title, String content, CategoryType categoryType, String createdBy, LocalDateTime createdDate, LocalDateTime modifiedDate, String uid) {
        return new PostResponse(id, title, content, categoryType, createdBy, createdDate, modifiedDate, uid);
    }

    public static PostResponse from(PostDto postDto) {
        return PostResponse.of(postDto.getId(),
                postDto.getTitle(),
                postDto.getContent(),
                postDto.getCategoryType(),
                postDto.getCreatedBy(),
                postDto.getCreatedDate(),
                postDto.getModifiedDate(),
                postDto.getUserDto().getUid());
    }
}
