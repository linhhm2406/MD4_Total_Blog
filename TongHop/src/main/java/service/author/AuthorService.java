package service.author;

import model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import repository.AuthorRepository;

import java.util.List;

public class AuthorService implements IAuthorService{

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public Author findById(Long id) {
        return authorRepository.findOne(id);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }
}
