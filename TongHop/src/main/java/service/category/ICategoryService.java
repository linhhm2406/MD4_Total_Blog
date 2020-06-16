package service.category;

import model.Category;

import java.util.List;

public interface ICategoryService{
    Category findById(Long id);
    List<Category> findAll();
}
