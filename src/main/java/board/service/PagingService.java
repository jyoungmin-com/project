package board.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PagingService {


    // 화면에 보여줄 페이지 넘버 개수
    private final static int PAGE_LENGTH = 10;

    // 화면에 출력 페이지 정의
    public List<Integer> getPageNumbers(int nowPage, int totalPages) {
//        return IntStream.range(1, 11).boxed().toList();

        int startPage = Math.max(nowPage - PAGE_LENGTH / 2, 1);
        int lastPage = Math.min(startPage + PAGE_LENGTH, totalPages + 1);

        return IntStream.range(startPage, lastPage).boxed().toList();
    }
}
