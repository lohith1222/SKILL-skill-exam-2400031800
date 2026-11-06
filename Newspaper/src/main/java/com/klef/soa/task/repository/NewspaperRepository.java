package com.klef.soa.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.soa.task.entity.Newspaper;

@Repository
public interface NewspaperRepository extends JpaRepository<Newspaper, Long>{
	@Query("SELECT n FROM Newspaper n WHERE n.name = :name")
	List<Newspaper> findByName(String name);
	List<Newspaper> findByType(String type);
	

}
