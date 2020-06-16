package controller.blog;

import model.Author;
import model.Blog;
import model.BlogForm;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.author.IAuthorService;
import service.blog.IBlogService;
import service.category.ICategoryService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private IAuthorService authorService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("authorList")
    public List<Author> author(){
        return authorService.findAll();
    }

    @ModelAttribute("categoryList")
    public List<Category> category(){
        return categoryService.findAll();
    }

    @GetMapping("blog")
    ModelAndView showListBlog(Pageable pageable) {
        Page<Blog> blogList = blogService.findAll(pageable);
        return new ModelAndView("blog/blogList", "blogList", blogList);
    }

    @GetMapping("blog/compose")
    ModelAndView showComposeForm() {
        BlogForm newBlog = new BlogForm();
        newBlog.setId((long) (blogService.findAllList().size()+1));
        return new ModelAndView("blog/composeForm", "blogForm", newBlog);
    }

    @Autowired
    Environment env;

    @PostMapping("blog/compose")
    String composeBlog(@ModelAttribute("blogForm") BlogForm blogForm) {
        Blog blog = new Blog(blogForm.getId(), blogForm.getTitle(), blogForm.getContent(), blogForm.getAuthor(), blogForm.getCategory(), null);
        MultipartFile multipartFile = blogForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = env.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(blogForm.getAvatar().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        blog.setAvatar(fileName);
        blogService.save(blog);
        return "redirect:/blog";
    }
}
