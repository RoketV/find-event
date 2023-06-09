package com.explore.mainservice.category.repository;


import com.explore.mainservice.category.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findAll(Pageable page);

    Optional<Category> findCategoryByName(String name);

    @Query("from Category c where c.id in :ids")
    List<Category> findAllByIdIn(@Param("ids") List<Long> ids);

}
