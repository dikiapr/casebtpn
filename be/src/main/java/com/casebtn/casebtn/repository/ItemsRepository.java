package com.casebtn.casebtn.repository;

import com.casebtn.casebtn.model.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepository extends JpaRepository<Items,Long> {
}
