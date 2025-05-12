package board.controller;

import board.dto.UserDto;
import board.dto.request.PostCommentRequest;
import board.dto.security.BoardDetails;
import board.entity.constant.UserRoleType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import board.dto.PostCommentDto;
import board.service.PostCommentService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class PostCommentController {
	
	private final PostCommentService postCommentService;
//	private final PostCommentRequest postCommentRequest;
	
	@PostMapping
	public String registerNewPostComment(@ModelAttribute PostCommentDto postCommentDto, @AuthenticationPrincipal BoardDetails boardDetails) {
//		UserDto userDto = UserDto.of("admin", "admin", "password", "email", UserRoleType.ADMIN);
		PostCommentDto comment = PostCommentDto.of(postCommentDto.getContent(), postCommentDto.getPid(), boardDetails.toDto());
		Long pid = postCommentDto.getPid();
		postCommentService.registerPostComment(comment);


//		postCommentService.registerPostComment(postCommentRequest.toDto(userDto));
		return "redirect:/posts/" + pid;
	}
	
	@DeleteMapping("/{pcid}")
	public String deletePostComment(@PathVariable Long pcid, Long pid, @AuthenticationPrincipal BoardDetails boardDetails) {
//		UserDto userDto = UserDto.of("admin", "admin", "password", "email", UserRoleType.ADMIN);
		postCommentService.deletePostComment(pcid, boardDetails.getUid());
		return "redirect:/posts/" + pid;
	}
}