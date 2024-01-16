package me.rlacofls.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.rlacofls.springbootdeveloper.domain.Article;
import me.rlacofls.springbootdeveloper.dto.AddArticleRequest;
import me.rlacofls.springbootdeveloper.dto.UpdateArticleRequest;
import me.rlacofls.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: " + id));
    }

    public void deleteById(long id){
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article updateById(long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
