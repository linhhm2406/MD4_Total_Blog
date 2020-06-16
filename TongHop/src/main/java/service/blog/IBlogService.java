package service.blog;

import model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService {
    Page<Blog> findAll(Pageable pageable);
    List<Blog> findAllList();
    void save(Blog blog);
    Blog findOne(Long id);
    List<Blog> findByContent(String content);
}
