package es.upm.miw.repositories;

import es.upm.miw.TestConfig;
import es.upm.miw.documents.Article;
import es.upm.miw.dtos.ArticleDto;
import es.upm.miw.dtos.ArticleSearchDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ArticleRepositoryIT {

    @Autowired
    private ArticleRepository articleRepository;

    private Article article;
    private Article article2;
    private Article article3;

    @BeforeEach
    void seedDb() {
        this.article = new Article();
        this.article.setCode("1");
        this.article.setDescription("art1");
        this.article.setRetailPrice(BigDecimal.valueOf(23.5));
        this.article.setStock(4);

        this.article2 = new Article();
        this.article2.setCode("2");
        this.article2.setDescription("Otro articulo");
        this.article2.setRetailPrice(BigDecimal.valueOf(22.6));
        this.article2.setStock(0);

        this.articleRepository.save(this.article);
        this.articleRepository.save(this.article2);
    }

    @Test
    void testReadAll() {
        assertTrue(this.articleRepository.findAll().size() > 4);
    }

    @Test
    void testFindByDescriptionLikeIgnoreCaseAndStockGreaterThanEqualNullSafe(){
        List<ArticleSearchDto> articleList = articleRepository.findByDescriptionLikeAndStockGreaterThanEqualNullSafe
                ("Art", null);
        assertEquals(2, articleList.size());

        List<ArticleSearchDto> articleList2 = articleRepository.findByDescriptionLikeAndStockGreaterThanEqualNullSafe
                (null, null);
        assertEquals(7, articleList2.size());

        List<ArticleSearchDto> articleList3 = articleRepository.findByDescriptionLikeAndStockGreaterThanEqualNullSafe
                (null, 1);
        assertEquals(5, articleList3.size());

        List<ArticleSearchDto> articleList4 = articleRepository.findByDescriptionLikeAndStockGreaterThanEqualNullSafe
                ("Art", 1);
        assertEquals(1, articleList4.size());
    }

    @Test
    void testFindByRetailPriceBetweenNullSafe(){
        List<ArticleSearchDto> articleList1 = articleRepository.findByRetailPriceBetweenNullSafe
                (null, null);
        assertFalse(articleList1.isEmpty());

        List<ArticleSearchDto> articleList2 = articleRepository.findByRetailPriceBetweenNullSafe
                ("22.6", null);
        assertEquals(3, articleList2.size());

        List<ArticleSearchDto> articleList3 = articleRepository.findByRetailPriceBetweenNullSafe
                (null, "27");
        assertEquals(6, articleList3.size());

        List<ArticleSearchDto> articleList4 = articleRepository.findByRetailPriceBetweenNullSafe
                ("22.6", "27");
        assertEquals(2, articleList4.size());
    }

    @Test
    void testFindByReferenceNullAndProviderNull(){
        List<ArticleSearchDto> articleList = articleRepository.findByReferenceNullAndProviderNull();
        assertEquals(2, articleList.size());
        assertEquals("art1", articleList.get(0).getDescription());
        assertEquals("Otro articulo", articleList.get(1).getDescription());
    }
}
