package com.example.voard.Controller;

import java.security.Principal;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.voard.Dto.CategoryDto;
import com.example.voard.Dto.PostDto;
import com.example.voard.Entity.Category;
import com.example.voard.Entity.Post;
import com.example.voard.Repository.PostRepository;
import com.example.voard.Service.CategoryService;
import com.example.voard.Service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final PostService postService;
    private final CategoryService categoryService;

    @GetMapping("/board")
    public ModelAndView Board(Principal principal) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("board");

        if (principal != null) {
            String username = principal.getName();
            mv.addObject("user", username);
            
            List<Category> category = categoryService.categoryLoad(principal);
            mv.addObject("category", category);

            List<Post> post = postService.postLoad(0 ,principal);
            mv.addObject("post", post);
        }

        return mv;
    }

    @PostMapping("/board/post/add")
    @ResponseBody
    public String addPost(@RequestBody PostDto postDto, Principal principal) {
        
          long result = postService.postSave(postDto, principal);
          
          if(result >= 0){
            return "Success Save";
          }
          
          return "Fail";
         
    }

    @PostMapping("/board/category/add")
    public String addCategory(CategoryDto categoryDto, Principal principal){
       long result = categoryService.categorySave(categoryDto, principal);

       if(result > 0) return "Success Save";

        return "fail";
    }

    @DeleteMapping("/board/post/del/{id}")
    public String delPost(@PathVariable("id")Long id){
        if(postService.postDel(id)){
            return "Success Del";
        }
        return "fail";
    }

    @GetMapping("/board/post/category/{category_id}")
    public ModelAndView getCategoryPost(@PathVariable("category_id") Long id, Principal principal){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("board");

        if (principal != null) {
            String username = principal.getName();
            mv.addObject("user", username);
            
            List<Category> category = categoryService.categoryLoad(principal);
            mv.addObject("category", category);

            List<Post> post = postService.postLoadWithCategory(id, 0, principal);
            mv.addObject("post", post);
        }

        return mv;
    }

    //목록 전체의 경우의 페이징
    @GetMapping("/board/get/post")
    public List<Post> pagePost(@RequestParam("page") int page, Principal principal){
        return postService.postLoad(page, principal);
    }

    //카테고리가 적용 된 경우의 페이징
    @GetMapping("/board/post/category")
    public List<Post> pageCategoryPost(@RequestParam("id") long id, @RequestParam("page") int page, Principal principal){
        return postService.postLoadWithCategory(id, page, principal);
    }
    

}
