package board.entity.constant;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum UserRoleType {
	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN");
	
	private String roleType;
	
	UserRoleType(String roleType) {
		this.roleType = roleType;
	}

	public static UserRoleType getInstance(String string) {
		return Arrays.stream(UserRoleType.values())
				.filter(userRoleType -> userRoleType.getRoleType() == string)
				.findFirst().orElse(null);
	}
	
}
