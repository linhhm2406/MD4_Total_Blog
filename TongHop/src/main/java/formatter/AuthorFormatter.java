package formatter;

import model.Author;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import service.author.AuthorService;

import java.text.ParseException;
import java.util.Locale;

@Component
public class AuthorFormatter implements Formatter<Author> {
    private AuthorService authorService;

    public AuthorFormatter(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public Author parse(String text, Locale locale) throws ParseException {
        return authorService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Author object, Locale locale) {
        return null;
    }
}
