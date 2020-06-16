package controller.blog;

import model.Blog;
import model.Category;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.blog.IBlogService;
import service.category.ICategoryService;

import javax.xml.bind.util.JAXBSource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("blog/api")
public class BlogRestController {

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IBlogService blogService;

    //    Xem danh sách các category
    @GetMapping("/category")
    public ResponseEntity<List<Category>> showCategoryList() {
        List<Category> categoryList = categoryService.findAll();
        if (categoryList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList, HttpStatus.OK);
    }

    //    Xem danh sách các bài viết
    @GetMapping("/allBlogList")
    public ResponseEntity<List<Blog>> showAllBlogList() {
        List<Blog> blogList = blogService.findAllList();
        if (blogList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(blogList, HttpStatus.OK);
    }

    //    Xem danh sách các bài viết của một category
    @GetMapping("getBlogByCategory/{id}")
    public ResponseEntity<List<Blog>> showBlogByCategory(@PathVariable Long id) {
        List<Blog> blogList = blogService.findAllList();
        if (blogList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<Blog> resultList = new ArrayList<>();
        for (int i = 0; i < blogList.size(); i++) {
            if (blogList.get(i).getCategory().getId().equals(id)) {
                resultList.add(blogList.get(i));
            }
        }

        if (resultList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    //    Xem chi tiết một bài viết
    @RequestMapping(value = "detailBlog/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Blog> showBlogDetail(@PathVariable Long id) {
        Blog blogToShow = blogService.findOne(id);
        if (blogToShow == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(blogToShow, HttpStatus.OK);
    }

    @GetMapping(value = "searchContent", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
//    List<Blog> searchResult(@RequestBody Object searchContent) {
    List<Blog> searchResult(@RequestParam("searchContent") String searchContent) {
        if (searchContent != null)
        return blogService.findByContent(searchContent.toString());
        return blogService.findAllList();
    }
}
