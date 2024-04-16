package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // [트랜잭션] 시작

        try {
            Member member10 = em.find(Member.class, 10L);

            member10.setName("changed-name");

            System.out.println("===BEFORE COMMIT===");
            tx.commit(); // [트랜잭션] 커밋
            System.out.println("===AFTER COMMIT===");
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
