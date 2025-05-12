package board.dto.response;

import board.dto.PostCommentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@AllArgsConstructor
public class PostCommentResponse {

    private Long id;
    private String content;
    private String uid;
    private LocalDateTime createdDate;
    private String createdBy;

    public static PostCommentResponse of(Long id, String content, String uid, LocalDateTime createdDate, String createdBy) {
        return new PostCommentResponse(id, content, uid, createdDate, createdBy);
    }

    public static PostCommentResponse from(PostCommentDto postCommentDto) {
        return PostCommentResponse.of(
                postCommentDto.getId(),
                postCommentDto.getContent(),
                postCommentDto.getUserDto().getUid(),
                postCommentDto.getCreatedDate(),
                postCommentDto.getCreatedBy()
        );
    }
}
