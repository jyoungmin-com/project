package board.dto.request;

import board.dto.PostDto;
import board.dto.UserDto;
import board.entity.constant.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class PostRequest {
    private String title;
    private String content;
    private CategoryType categoryType;

    public static PostRequest of(String title, String content, CategoryType categoryType) {
        return new PostRequest(title, content, categoryType);
    }

    public PostDto toDto(UserDto userDto) {
        return PostDto.of(title, content, categoryType, userDto);
    }

}
