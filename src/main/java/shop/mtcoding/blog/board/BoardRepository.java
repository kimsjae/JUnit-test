package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public void delete(String title) {
        Query query = em.createNativeQuery("delete from board_tb where title = ?", Board.class);
        query.setParameter(1, title);

        try {
            query.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Transactional
    public void update(String content, String content2) {
        Query query = em.createNativeQuery("update board_tb set content = ? where content = ?", Board.class);
        query.setParameter(1, content);
        query.setParameter(2, content2);

        try {
            query.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Board selectOne(int id) {
        Query query = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        query.setParameter(1, id);


        try {
            Board board = (Board) query.getSingleResult();
            return board;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Board> selectAll() {
        Query query = em.createNativeQuery("select * from board_tb", Board.class);

        try {
            List<Board> board = query.getResultList(); // 못 찾으면 빈 컬렉션을 준다 (크기 = 0)
            return board;
        } catch (Exception e) {
            return null;
        }
    }


    @Transactional
    public void insert(String title, String content, String author){
        Query query = em.createNativeQuery("insert into board_tb(title, content, author) values(?, ?, ?)");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, author);

        query.executeUpdate();
    }
}