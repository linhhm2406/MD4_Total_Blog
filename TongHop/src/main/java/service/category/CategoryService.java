package service.category;

import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CategoryRepository;

import java.util.List;

public class CategoryService implements ICategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
