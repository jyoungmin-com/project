package board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class CommonController {
	
	private final UserService userService;
	
	@GetMapping({"/"})
	public String mainPage() {
		return "forward:/posts";
	}



	
}
