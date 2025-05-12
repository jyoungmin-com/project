package board.entity;

import board.entity.constant.UserRoleType;
import board.util.UserRoleTypeConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Entity
public class User extends AuditingFields {
	
	@Id
	@Column(name = "uid", length = 20)
    private String uid;
    
	@Column(length = 20)
	private String username;
	
	@Column(length = 20)
    private String password;

    private String email;
    
//    @Enumerated(EnumType.STRING)
	@Convert(converter = UserRoleTypeConverter.class)
	@Column(name = "role_type", columnDefinition = "VARCHAR(50)")
    private UserRoleType userRoleType;
    
    protected User() {}
    
	private User(String uid, String username, String password, String email, UserRoleType userRoleType) {
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userRoleType = userRoleType;
	}
	
	public static User of(String userId, String username, String password, String email, UserRoleType userRoleType) {
		return new User(userId, username, password, email, userRoleType);
	}
	
}
