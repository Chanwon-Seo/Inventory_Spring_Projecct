package com.example.im_project.service;

import com.example.im_project.controller.form.InventoryForm;
import com.example.im_project.controller.form.ItemOrderForm;
import com.example.im_project.controller.form.UpdateForm;
import com.example.im_project.domain.Inventory;
import com.example.im_project.domain.Member;
import com.example.im_project.repository.InventoryRepository;
import com.example.im_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void join(InventoryForm inventoryForm, String sessionUser_id) {

        Member chkByUserId = findMember(sessionUser_id);

        Inventory inventory = new Inventory(inventoryForm, chkByUserId);

        inventoryRepository.save(inventory);

    }

    public List<Inventory> findItemsAll(String sessionUser_id) {
        Member result = findMember(sessionUser_id);
        List<Inventory> all = inventoryRepository.findByMemberId(result.getId());

        return all;
    }

    public Member findMember(String sessionUser_id) {
        return memberRepository.findChkByUserId(sessionUser_id);
    }

    @Transactional
    public void remove(Long id) {
        inventoryRepository.deleteById(id);
    }

    public UpdateForm findInventory(Long id) {
        Inventory inventory = inventoryRepository.findById(id).orElse(null);

        UpdateForm form = new UpdateForm();
        form.setId(inventory.getId());
        form.setName(inventory.getName());
        form.setCount(inventory.getCount());
        form.setPrice(inventory.getPrice());
        return form;
    }

    @Transactional
    public void updateItem(Long id, UpdateForm form) {
        Inventory inventory = inventoryRepository.findById(id).orElse(null);
        inventory.setName(form.getName());
        inventory.setCount(form.getCount());
        inventory.setPrice(form.getPrice());
    }

    public ItemOrderForm createOrder(Long id) {
        Inventory findInventory = inventoryRepository.findById(id).orElse(null);

        ItemOrderForm itemOrderForm = new ItemOrderForm();
        itemOrderForm.setId(findInventory.getId());
        itemOrderForm.setName(findInventory.getName());
        itemOrderForm.setCount(findInventory.getCount());
        itemOrderForm.setPrice(findInventory.getPrice());

        return itemOrderForm;
    }


    @Transactional
    public int minCount(Long id, ItemOrderForm form) {
        Inventory inventory = inventoryRepository.findById(id).orElse(null);

        if (inventory.getCount() < form.getMinusCount()) {
            return 0;
        }

        inventory.setCount(inventory.getCount() - form.getMinusCount());
        return 1;
    }
}
