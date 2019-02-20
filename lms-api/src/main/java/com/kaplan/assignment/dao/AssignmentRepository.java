package com.kaplan.assignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kaplan.assignment.model.Assignment;


@Repository
public interface AssignmentRepository extends CrudRepository<Assignment, Long> {


	@Query("SELECT a FROM Assignment a JOIN a.tags t WHERE t = LOWER(:tag)")
	List<Assignment> retrieveByTag(@Param("tag") String tag);

	
}



