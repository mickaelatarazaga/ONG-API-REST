package com.alkemy.ong.repository;

import com.alkemy.ong.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categorie, Long> {

}
