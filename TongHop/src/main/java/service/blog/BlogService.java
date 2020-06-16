package service.blog;

import model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.BlogRepository;

import java.util.List;

@Service
public class BlogService implements IBlogService{

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    @Override
    public List<Blog> findAllList() {
        return (List<Blog>) blogRepository.findAll();
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public Blog findOne(Long id) {
        return blogRepository.findOne(id);
    }

    @Override
    public List<Blog> findByContent(String content) {
        return blogRepository.findBlogByContentContaining(content);
    }
}
