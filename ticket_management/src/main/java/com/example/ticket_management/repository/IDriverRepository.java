package com.example.ticket_management.repository;

import com.example.ticket_management.model.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

public interface IDriverRepository extends JpaRepository<Driver,Integer> {
    @Query(value = "select d.* from driver d where d.name like :name and d.is_delete = 0",
            nativeQuery = true,
            countQuery = "select count(*) from(select d.* from driver d where d.name like :name and d.is_delete = 0) temp")
    Page<Driver> find(Pageable pageable, @Param("name") String name);


    @Transactional
    @Modifying
    @Query(value = "update driver d set d.is_delete = true where d.id = :id ",nativeQuery = true)
    void deleteById(@Param("id") Integer id);
}
