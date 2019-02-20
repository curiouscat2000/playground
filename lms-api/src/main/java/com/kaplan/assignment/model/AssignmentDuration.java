package com.kaplan.assignment.model;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
@Embeddable
public class AssignmentDuration {
	public AssignmentDuration(LocalDateTime start, LocalDateTime end) {
		this.start=start;
		this.end=end;
	}
	public AssignmentDuration() {
	}
	@NotNull
	@ApiModelProperty(notes = "assignment duration datetime(s), FROM, in UTC timezone, inclusive")
	public LocalDateTime start;
	@NotNull
	@ApiModelProperty(notes = "assignment duration datetime(s), TO, in UTC timezone, exclusive")
	public LocalDateTime end;

}
