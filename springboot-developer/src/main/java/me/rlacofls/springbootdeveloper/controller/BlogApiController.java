package me.rlacofls.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.rlacofls.springbootdeveloper.domain.Article;
import me.rlacofls.springbootdeveloper.dto.AddArticleRequest;
import me.rlacofls.springbootdeveloper.dto.ArticleResponse;
import me.rlacofls.springbootdeveloper.dto.UpdateArticleRequest;
import me.rlacofls.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findByArticeId(@PathVariable Long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<?> deleteByArticelId(@PathVariable Long id){
        blogService.deleteById(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateByArticleId(@PathVariable Long id, @RequestBody UpdateArticleRequest request){
        Article article = blogService.updateById(id, request);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));

    }
}
