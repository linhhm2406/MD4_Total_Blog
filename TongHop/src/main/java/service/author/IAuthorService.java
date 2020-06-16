package service.author;

import model.Author;

import java.util.List;

public interface IAuthorService {
    Author findById(Long id);
    List<Author> findAll();
}
