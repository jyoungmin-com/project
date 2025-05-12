package board.controller;

import board.dto.PostDto;
import board.dto.UserDto;
import board.dto.request.PostRequest;
import board.dto.response.CommentResponse;
import board.dto.response.PostResponse;
import board.dto.response.PostwithCommentsResponse;
import board.dto.security.BoardDetails;
import board.entity.Post;
import board.entity.constant.CategoryType;
import board.entity.constant.UserRoleType;
import board.service.PagingService;
import board.service.PostCommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import board.service.PostService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/posts")
@Controller
public class PostController {

    private final PostService postService;
    private final PostCommentService postCommentService;
    private final PagingService pagingService;

    @GetMapping
    public String getPosts(ModelMap modelMap,
                           @PageableDefault(page = 0, size = 10) Pageable pageable,
                           @RequestParam(required = false) String searchType,
                           @RequestParam(required = false) String searchValue) {

//		List<PostResponse> postResponses = postService.getAllPost().stream()
//				.map(PostResponse::from).toList();

        Page<PostResponse> postResponses = null;
        if (searchType != null && searchValue != null) {
            Page<PostDto> result = postService.searchPost(searchType, searchValue, pageable);
            if (result == null) {
                result = postService.pagingPosts(pageable);
            }
            postResponses = result.map(PostResponse::from);
        } else {
            postResponses = postService.pagingPosts(pageable)
                    .map(PostResponse::from);
        }

        List<Integer> pageNumbers = pagingService.getPageNumbers(pageable.getPageNumber(), postResponses.getTotalPages());

//        postResponses.getNumber();
//        postResponses.hasNext();
//        postResponses.hasPrevious();

//        System.out.println(postResponses);

        modelMap.addAttribute("pageNumbers", pageNumbers);
        modelMap.addAttribute("posts", postResponses);
        return "posts/index";
    }

    @GetMapping("/form")
    public String goForm() {
        return "posts/form";
    }

    @GetMapping("/{pid}")
    public String getPostsBypid(@PathVariable Long pid, Model model, ModelMap modelMap) {


        PostResponse post = PostResponse.from(postService.getPost(pid));
        Map<String, Long> hasPrevNextPost = postService.hasPrevNextPost(pid);
        List<CommentResponse> commentResponse = CommentResponse.from(postCommentService.getCommentByPid(pid));
//        PostwithCommentsResponse posts = PostwithCommentsResponse.from(postService.getPostWithComments(pid));

        modelMap.addAttribute("PrevNext", hasPrevNextPost);
        model.addAttribute("post", post);
        modelMap.addAttribute("comments", commentResponse);
//        modelMap.addAttribute("commentss", posts.getPostCommentsResponse());

        return "posts/detail";
    }

    @PostMapping
    public String registerPosts(PostRequest postRequest, @AuthenticationPrincipal BoardDetails boardDetails) {
//		System.out.println(postRequest);

        System.out.println(boardDetails);
//        UserDto userDto = UserDto.of("admin", "admin", "password", "email", UserRoleType.ADMIN);

        UserDto userDto = boardDetails.toDto();

//        PostDto postDto = PostDto.of(
//                postRequest.getTitle(),
//                postRequest.getContent(),
//                postRequest.getCategoryType(),
//                userDto);

        postService.registerPost(postRequest.toDto(boardDetails.toDto()));
        return "redirect:/posts";
    }

    @GetMapping("/{pid}/form")
    public String goUpdateForm(@PathVariable Long pid, Model model) {
        PostResponse post = PostResponse.from(postService.getPost(pid));
        model.addAttribute("post", post);
        return "posts/form";
    }

    @PutMapping("/{pid}/edit")
    public String updatePosts(@PathVariable Long pid, Model model, PostRequest postRequest, @AuthenticationPrincipal BoardDetails boardDetails) {
//        UserDto userDto = UserDto.of("admin", "admin", "password", "email", UserRoleType.ADMIN);
//
//        PostDto newpostDto = PostDto.of(
//                postRequest.getTitle(),
//                postRequest.getContent(),
//                postRequest.getCategoryType(),
//                userDto);

        postService.updatePost(pid, postRequest.toDto(boardDetails.toDto()));


        return "redirect:/posts";
    }

    @DeleteMapping("/{pid}")
    public String deletePostsByPid(@PathVariable Long pid, @AuthenticationPrincipal BoardDetails boardDetails) {
        postService.deletePost(pid, boardDetails.getUid());
        return "redirect:/posts";
    }

//    @GetMapping({"/search"})
//    public String search(@RequestParam String searchValue, @RequestParam String searchType, Model model) {
//        System.out.println("searchValue = " + searchValue);
//        System.out.println("searchType = " + searchType);
//        System.out.println("hello!!!!");
//
//        model.addAttribute("posts", postService.searchPost(searchType, searchValue));
//
//        return "posts/index";
//    }
}