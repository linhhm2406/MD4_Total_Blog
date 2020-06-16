package repository;

import model.Blog;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends PagingAndSortingRepository<Blog,Long> {
    List<Blog> findBlogByContentContaining(String content);
}
