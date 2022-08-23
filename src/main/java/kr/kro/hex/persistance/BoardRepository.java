package kr.kro.hex.persistance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.kro.hex.domain.Board;

/**
 * 게시판 레포지토리
 * 
 * @author : Rubisco
 * @version : 1.0.0
 * @date : 2022-08-21 오후 10:20
 */

public interface BoardRepository extends JpaRepository<Board, Long>  {
    
    @Override
    @Query("select distinct b from Board b join fetch b.member join fetch b.category join fetch b.group")
    List<Board> findAll(Sort sort);

    @Override
    @Query("select distinct b from Board b join fetch b.member join fetch b.category left outer join fetch b.commentList where b.documentId = :id")
    Optional<Board> findById(@Param("id") Long id);
}
