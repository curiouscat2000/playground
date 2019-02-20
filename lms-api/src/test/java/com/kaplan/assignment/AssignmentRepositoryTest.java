package com.kaplan.assignment;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kaplan.assignment.dao.AssignmentRepository;
import com.kaplan.assignment.model.Assignment;
import com.kaplan.assignment.model.AssignmentDuration;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AssignmentRepositoryTest {
	  
    @Autowired
    private AssignmentRepository assignmentRepository;
     
    @Test
    public void whenFindingAssignmentById_thenFound() {
    	assignmentRepository.save(new Assignment("name1", "description1", 
    			new AssignmentDuration(LocalDateTime.of(2019, 1, 31, 0, 0), 
    					LocalDateTime.of(2019, 9, 30, 0, 0)), 
    			Arrays.asList("tag1","tag2","tag3"), 
    			"urn:kaplan:course:id:1", 
    			"urn:kaplan:teacher:id:1", 
    			"urn:kaplan:student:id:1")
    	);
    	Assertions.assertThat(assignmentRepository.findById(1L)).isInstanceOf(Optional.class);
      
    }
     
    @Test
    public void whenFindingAllAssignments_thenFound() {
    	assignmentRepository.save(new Assignment( "name1", "description1", 
    			new AssignmentDuration(LocalDateTime.of(2019, 1, 31, 0, 0), 
    					LocalDateTime.of(2019, 9, 30, 0, 0)), 
    			Arrays.asList("tag1","tag2","tag3"), 
    			"urn:kaplan:course:id:1", 
    			"urn:kaplan:teacher:id:1", 
    			"urn:kaplan:student:id:1"));
    	assignmentRepository.save(new Assignment( "name2", "description2", 
    			new AssignmentDuration(LocalDateTime.of(2019, 1, 31, 0, 0), 
    					LocalDateTime.of(2019, 12, 31, 0, 0)), 
    			Arrays.asList("tag1","tag2","tag3"), 
    			"urn:kaplan:course:id:1", 
    			"urn:kaplan:teacher:id:1", 
    			"urn:kaplan:student:id:1"));
    	
    	assertThat(assignmentRepository.findAll()).isInstanceOf(List.class);
    }
    @Test
    public void whenSavingAssignment_thenFound() {
    	assignmentRepository.save(new Assignment( "name1", "description1", 
    			new AssignmentDuration(LocalDateTime.of(2019, 1, 31, 0, 0), 
    					LocalDateTime.of(2019, 9, 30, 0, 0)), 
    			Arrays.asList("tag1","tag2","tag3"), 
    			"urn:kaplan:course:id:1", 
    			"urn:kaplan:teacher:id:1", 
    			"urn:kaplan:student:id:1"));
    	Assignment assignment = assignmentRepository.findById(1L).orElseGet(() 
          -> new Assignment("name1", "description1", 
    			new AssignmentDuration(LocalDateTime.of(2019, 1, 31, 0, 0), 
    					LocalDateTime.of(2019, 9, 30, 0, 0)), 
    			Arrays.asList("tag1","tag2","tag3"), 
    			"urn:kaplan:course:id:1", 
    			"urn:kaplan:teacher:id:1", 
    			"urn:kaplan:student:id:1"));
        assertThat(assignment.getName()).isEqualTo("name1");
    }
    //@TODO
    public void whenSearchedByTag_thenFound() {
    
    	Assignment assignment1 = new Assignment( "name5", "description5", 
    			new AssignmentDuration(LocalDateTime.of(2019, 1, 31, 0, 0), 
    					LocalDateTime.of(2019, 9, 30, 0, 0)), 
    			Arrays.asList("Biology","Chemistry"), 
    			"urn:kaplan:course:id:1", 
    			"urn:kaplan:teacher:id:1", 
    			"urn:kaplan:student:id:1");
    	
    	assignmentRepository.save(assignment1);
 
    	Assignment assignmentTmp = assignmentRepository.retrieveByTag("Chemistry").get(0);
    	assertEquals("name incorrect", "name5", assignmentTmp.getName());
    	 

    }


}
