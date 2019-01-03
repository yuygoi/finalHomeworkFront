package com.jerry.gamemarket.dao;

import com.jerry.gamemarket.entity.ArticleComment;
import com.jerry.gamemarket.entity.CanteenComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 叶俊晖
 * @date 2019/1/3 0003 16:01
 */
public interface CanCommentDao extends JpaRepository<CanteenComment,String>{

    @Query(value = "select * FROM canteen_comment WHERE role!=-1",nativeQuery = true)
    List<CanteenComment> findByCanCommentId(Integer caid);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM canteen_comment WHERE can_comment_id=?1",nativeQuery = true)
    void deleteById(String commentId);

    @Query(value = "select * FROM canteen_comment WHERE ca_id=?1 ORDER BY update_time DESC ",nativeQuery = true)
    List<CanteenComment> findByAidManager(Integer articleId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE canteen_comment SET role=-1 WHERE can_comment_id=?1",nativeQuery = true)
    void banRole(Integer commentId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE canteen_comment SET role=0 WHERE can_comment_id=?1",nativeQuery = true)
    void allowRole(Integer commentId);
}