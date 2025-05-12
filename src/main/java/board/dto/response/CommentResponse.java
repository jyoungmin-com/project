package board.dto.response;

import board.dto.PostCommentDto;
import board.dto.UserDto;
import board.entity.Post;
import board.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class CommentResponse {
    private Long id;
    private String content;
    private UserDto userDto;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime modifiedDate;
    private String modifiedBy;

    public static CommentResponse of(Long id, String content, UserDto userDto, LocalDateTime createdDate, String createdBy, LocalDateTime modifiedDate, String modifiedBy) {
        return new CommentResponse(id, content, userDto, createdDate, createdBy, modifiedDate, modifiedBy);
    }

    public static CommentResponse from(PostCommentDto postCommentDto) {
        return CommentResponse.of(
                postCommentDto.getId(),
                postCommentDto.getContent(),
                postCommentDto.getUserDto(),
                postCommentDto.getCreatedDate(),
                postCommentDto.getCreatedBy(),
                postCommentDto.getModifiedDate(),
                postCommentDto.getModifiedBy()
        );
    }

    public static List<CommentResponse> from(List<PostCommentDto> postCommentDtos) {
        return postCommentDtos.stream()
                .map(CommentResponse::from)
                .toList();
    }

}
