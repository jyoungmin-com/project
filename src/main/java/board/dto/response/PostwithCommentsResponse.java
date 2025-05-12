package board.dto.response;

import board.dto.PostCommentDto;
import board.dto.PostwithCommentsDto;
import board.entity.PostComment;
import board.entity.constant.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

@ToString
@Getter
@AllArgsConstructor
public class PostwithCommentsResponse {
    private Long id;
    private String title;
    private String content;
    private CategoryType categoryType;
    private String uid;
    private LocalDateTime createdDate;
    private String createdBy;
    private Set<PostCommentResponse> postCommentsResponse;


    public static PostwithCommentsResponse of(Long id, String title, String content, CategoryType categoryType, String uid, LocalDateTime createdDate, String createdBy, Set<PostCommentResponse> postCommentsResponse) {
        return PostwithCommentsResponse.of(id, title, content, categoryType, uid, createdDate, createdBy, postCommentsResponse);
    }

    public static PostwithCommentsResponse from(PostwithCommentsDto postwithCommentsDto) {
        return PostwithCommentsResponse.of(
                postwithCommentsDto.getId(),
                postwithCommentsDto.getTitle(),
                postwithCommentsDto.getContent(),
                postwithCommentsDto.getCategoryType(),
                postwithCommentsDto.getUserDto().getUid(),
                postwithCommentsDto.getCreatedDate(),
                postwithCommentsDto.getCreatedBy(),
                getPostCommentResponses(postwithCommentsDto.getPostComments())
        );
    }

    private static Set<PostCommentResponse> getPostCommentResponses(Set<PostCommentDto> postCommentDtos) {

        // PostCommentDto 반복 -> PostCommentResponse 변경
        Map<Long, PostCommentResponse> map = postCommentDtos.stream()
                .map(PostCommentResponse::from)
                .collect(Collectors.toMap(PostCommentResponse::getId, Function.identity()));

        // Id값을 이용하여 중복 제거 -> return
        return map.values().stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator
                        .comparing(PostCommentResponse::getCreatedDate)
                        .reversed()
                        .thenComparing(PostCommentResponse::getId)
                )));

    }
}
