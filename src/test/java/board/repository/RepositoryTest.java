package board.repository;

import board.entity.Post;
import board.entity.PostComment;
import board.entity.User;
import board.entity.constant.CategoryType;
import board.entity.constant.UserRoleType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Repository 테스트")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(RepositoryTest.TestJPAConfig.class)
class RepositoryTest {
    //repo 사용 가능하게 주입시켜줄거임(생성자주입, RequiredArg 말고)
    private final UserRepository userRepository;
    private final PostCommentRepository postCommentRepository;
    private final PostRepository postRepository;

    RepositoryTest(@Autowired UserRepository userRepository, @Autowired PostCommentRepository postCommentRepository, @Autowired PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postCommentRepository = postCommentRepository;
        this.postRepository = postRepository;
    }


    @DisplayName("Repository 확인")
    @Test
    @Disabled
    void testRepository() {
        System.out.println(postCommentRepository);
    }


    @DisplayName("Repository Dummy 데이터")
    @Test
    @Rollback(value = false)
    void setDummies() throws InterruptedException {
        // ----------
        // given -- 사용할 데이터 만들기

//		User user = User.of("admin",
//				"admin",
//				"admin",
//				"admin@test.com",
//				UserRoleType.ADMIN);
//
//		long prevUserCnt = userRepository.count(); // 0


        // ----------
        // when -- 마련한 데이터 가지고 어떠한 작업을 수행했는가 (비지니스 로직)

//		userRepository.save(user);


        // ----------
        // then -- 결과 확인, 기븐 샘플 데이터와 비지니스 로직가지고 결과 확인

//		assertThat(userRepository.count()).isEqualTo(prevUserCnt + 1);


        // ----------
//        // given -- 사용할 데이터 만들기
//
//        // user 10명
//
////		for (int i = 1; i <= 10; i++) {
////			User user = User.of(String.valueOf(i), "user" + i, "user" + i, "user" + i + "@board.com", UserRoleType.USER);
////			userRepository.save(user);
////		}
//
//        List<User> users = IntStream.rangeClosed(1, 10)
//                .mapToObj(i -> User.of("user" + i, "user" + i, "user" + i, "user" + i + "@board.com", UserRoleType.USER))
//                .toList();
//                .collect(Collectors.toList());
//
//
//        // ----------
//        // when -- 마련한 데이터 가지고 어떠한 작업을 수행했는가 (비지니스 로직)
//        userRepository.saveAllAndFlush(users);
//
//        // ----------
//        // then -- 결과 확인, 기븐 샘플 데이터와 비지니스 로직가지고 결과 확인






        // category 3개

        //given



        //when




        //then






//        // post 200개
//        //given
//        //title 1~200, Content 1~200, categoryType FRONT, WEB, BACKEND
//
//        CategoryType[] categoryTypes = CategoryType.values();
//        Random random = new Random();
////
//        List<Post> posts = IntStream.rangeClosed(1, 200)
//                .mapToObj(i -> Post.of("Title " + i, "Content " + i, categoryTypes[random.nextInt(categoryTypes.length)], userRepository.getReferenceById("user" + (random.nextInt(10)+1))))
//                .toList();
//
//        //when
//
//        postRepository.saveAllAndFlush(posts);
//
//
//        //then








        // post 1개당 --> comment 3개씩
//        List<Post> posts = postRepository.findAll();
//        List<User> users = userRepository.findAll();
//        List<PostComment> postComments = IntStream.rangeClosed(1, 600)
//                .mapToObj(i -> PostComment.of("post comment : "+i,
//                        posts.get(i%posts.size()),
//                        users.get(i%users.size())))
//                .collect(Collectors.toList());
//
//        postCommentRepository.saveAllAndFlush(postComments);


    }


    @TestConfiguration
    @EnableJpaAuditing
    static class TestJPAConfig {
        @Bean
        AuditorAware<String> auditorAware() {
            return () -> Optional.of("admin");
        }
    }

}
