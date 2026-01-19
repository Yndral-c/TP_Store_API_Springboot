package com.example.TPStoreAPI.bo;

import com.example.TPStoreAPI.dao.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ResponseCode<List<Article>> getAll(){
        List<Article> articles = articleRepository.findAll();
        return new ResponseCode<List<Article>>("202", "Msg getAll success", articles);
    }

    public ResponseCode<Article> getArticle(Long id) {
        Article article = articleRepository.findById(id).orElseGet(null);
        if (article == null){
            return new ResponseCode<Article>("703", "Msg getArticle not found", null);
        }

        return new ResponseCode<Article>("202", "Msg getArticle success", article);
    }

    public ResponseCode<Boolean> deleteArticle(Long id){
        boolean exist = articleRepository.existsById(id);
        if (exist) {
            articleRepository.deleteById(id);
            return new ResponseCode<Boolean>("202", "Msg deleted successfull", exist);
        }
        return new ResponseCode<Boolean>("703", "Msg deleted error", exist);
    }

    public ResponseCode<Article> saveArticle(Article article){
        if (article.getId() == null){
            return new ResponseCode<Article>("202", "Article created", articleRepository.save(article));
        }

        // UPDATE → vérifier existence
        boolean exist = articleRepository.existsById(article.getId());
        if (!exist) {
            return new ResponseCode<>("404", "Article not found", null);
        }

        Article saved = articleRepository.save(article);
        return new ResponseCode<>("203", "Article modified", saved);
    }
}
