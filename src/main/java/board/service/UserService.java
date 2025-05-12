package board.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import board.dto.UserDto;
import board.entity.User;
import board.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	public void registerUser(UserDto userDto) {

		User user = userDto.toEntity();
		userRepository.save(user);
	}

	public Optional<UserDto> searchUser(String uid) {
		return userRepository.findById(uid)
				.map(UserDto::from);
	}

}
