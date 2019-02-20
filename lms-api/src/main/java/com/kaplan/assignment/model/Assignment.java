package com.kaplan.assignment.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name ="assignments")
public class Assignment {
	public Assignment( String name, String description, AssignmentDuration duration, List<String> tags,
			String course, String teacher, String student) {
		super();
		//required
		this.name = name;
		//required
		this.description = description;
		//required
		this.duration = duration;
		//at least one tag required
		this.tags = tags;
		//required
		this.courseURN = course;
		//optional. Either Student or Teacher required
		this.teacherURN = teacher;
		//optional. Either Student or Teacher required
		this.studentURN = student;
	}
	public Assignment() {
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "internal database id for fast lookup")
	private long id;
	
	
	@Size(max = 40)
	@ApiModelProperty(notes = "assignment name")
	private String name;
	@Size(max = 80)
	@ApiModelProperty(notes = "a short human friendly description, plain text only")
	private String description;
	@Embedded
	@ApiModelProperty(notes = "assignment duration datetime(s), FROM and TO, in UTC timezone.")
	private AssignmentDuration duration;

	@Size(max = 255)
	@NotNull
	@ApiModelProperty(notes = "customer exposable, persistent across environments, ID of a learning Course in Kaplan Platform\n"
			+ "Sample:  urn:kaplan:course:MachineLearningA-Z")
	private String courseURN;
	@Size(max = 255)
	@NotNull
	@ApiModelProperty(notes = "customer exposable, persistent across environments, ID of a Teacher in Kaplan Platform\n"
			+ "Sample:  urn:kaplan:teacher:m.stuart@mit.edu")

	private String teacherURN;
	@Size(max = 255)
	@NotNull
	@ApiModelProperty(notes = "customer exposable, persistent across environments, ID of a Student in Kaplan Platform\n"
			+ "Sample:  urn:kaplan:student:student.abc@xyz.com")
	private String studentURN;
	
	
	@ElementCollection
    private List<String> tags = new ArrayList<>();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public AssignmentDuration getDuration() {
		return duration;
	}
	public void setDuration(AssignmentDuration duration) {
		this.duration = duration;
	}
	public List<String> getTags() {
		return this.tags;
	}
	public void setTags(List<String> tags) {
		this.tags=tags;
	}
	public String getCourseURN() {
		return courseURN;
	}
	public void setCourseURN(String course) {
		this.courseURN = course;
	}
	public String getTeacherURN() {
		return teacherURN;
	}
	public void setTeacherURN(String teacher) {
		this.teacherURN = teacher;
	}
	public String getStudentURN() {
		return studentURN;
	}
	public void setStudentURN(String student) {
		this.studentURN = student;
	}

}
