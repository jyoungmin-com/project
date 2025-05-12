package board.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Entity
@Table(name = "post_comment")
@EqualsAndHashCode
public class PostComment extends AuditingFields {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String content;
    
    @ManyToOne(optional = false) 
    @JoinColumn(name = "pid", referencedColumnName = "pid")
    private Post post;

    @ManyToOne(optional = false)
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    private User user;

	protected PostComment() {}

	private PostComment(String content, Post post, User user) {
		this.content = content;
		this.post = post;
		this.user = user;
	}
    
	public static PostComment of(String content, Post post, User user) {
		return new PostComment(content, post, user);
	}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PostComment that)) {
            return false;
        }
        return this.getId() != null && this.getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
