package com.example.im_project;

import com.example.im_project.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class initDB {

    private final InitService initService;


   @PostConstruct
    public void init() {
        initService.dbInit1();
//        initService.dbInit2();
//        initService.dbInit3();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        private final BCryptPasswordEncoder bCryptPasswordEncoder;

        public void dbInit1() {
            Member member = new Member("관리자", "admin", bCryptPasswordEncoder.encode("admin"), "ROLE_ADMIN");
            em.persist(member);
        }
/*
        public void dbInit2() {

            Member member1 = new Member("서찬원1", "user1", bCryptPasswordEncoder.encode("user1"), "ROLE_USER");
            Address address1 = new Address("인천", "미추홀구", "10000");

            address1.setMember(member1);

            em.persist(address1);
            em.persist(member1);

            Inventory item1 = new Inventory("노트북", 100, 10000, member1);
            Inventory item2 = new Inventory("모니터", 200, 20000, member1);
            em.persist(item1);
            em.persist(item2);
        }

        public void dbInit3() {
            Member member2 = new Member("서찬원2", "user2", bCryptPasswordEncoder.encode("user2"), "ROLE_USER");
            Address address2 = new Address("서울", "강남", "20000");

            address2.setMember(member2);

            em.persist(address2);
            em.persist(member2);

            Inventory item3 = new Inventory("스프링북", 300, 30000, member2);
            Inventory item4 = new Inventory("타임리프북", 400, 40000, member2);

            em.persist(item3);
            em.persist(item4);

        }
*/
    }
}
