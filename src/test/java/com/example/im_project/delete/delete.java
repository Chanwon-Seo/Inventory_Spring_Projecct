package com.example.im_project.delete;

import com.example.im_project.domain.Inventory;
import com.example.im_project.domain.Member;
import com.example.im_project.repository.InventoryRepository;
import com.example.im_project.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional
public class delete {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("delete")
    void delete() throws Exception {
        //given
        Member member = new Member("asdf", "adsf", "adsf");
        Inventory asdf = new Inventory("asdf", 1, 1, member);

        memberRepository.save(member);
        inventoryRepository.save(asdf);

        List<Inventory> all = inventoryRepository.findAll();
        for (Inventory inventory : all) {
            System.out.println(inventory.getId());
            System.out.println(inventory.getName());
        }
        //when
        inventoryRepository.deleteById(asdf.getId());

        //then
        List<Inventory> all1 = inventoryRepository.findAll();
        for (Inventory inventory : all1) {
            System.out.println(inventory.getId());
            System.out.println(inventory.getName());
        }
    }
}
