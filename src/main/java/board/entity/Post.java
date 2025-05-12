package board.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import board.dto.PostDto;
import board.entity.constant.CategoryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Entity
public class Post extends AuditingFields {

    @Id
    @Column(name = "pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String title;

    @Setter
    private String content;

    @Setter
    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "category_type", columnDefinition = "VARCHAR(50)")
    private CategoryType categoryType;

    @ToString.Exclude
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private final Set<PostComment> postComments = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "uid")
    private User user;

    protected Post() {}

    private Post(String title, String content, CategoryType categoryType, User user) {
        this.title = title;
        this.content = content;
        this.categoryType = categoryType;
        this.user = user;
    }

    public static Post of(String title, String content, CategoryType categoryType, User user) {
        return new Post(title, content, categoryType, user);
    }


}
