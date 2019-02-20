package com.kaplan.assignment;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kaplan.assignment.dao.AssignmentRepository;
import com.kaplan.assignment.errors.AssignmentNotFoundException;
import com.kaplan.assignment.model.Assignment;
import com.kaplan.assignment.model.AssignmentDuration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="assignments")

public class AssignmentController {
	@Autowired
	public AssignmentRepository repository;
	@RequestMapping(value = "/api/assignments", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "View a list of assignments, filterable by tag", response = Iterable.class)

	public Iterable<Assignment> getAssignments(){
		
		return repository.findAll();

		
	}
	@RequestMapping(value = "/api/assignments/{id}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "get an assignment by id", response = Assignment.class)
	public Assignment getAssignment(@PathVariable("id") long id){
		Optional<Assignment> student = repository.findById(id);

		if (!student.isPresent())
			throw new AssignmentNotFoundException("id-" + id);

		return student.get();
	}
	
	@RequestMapping(value = "/api/assignments", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "create a new assignment", response = ResponseEntity.class)

	public ResponseEntity<Object> createAssignment(@RequestBody Assignment assignment) {
		Assignment savedAssignment = repository.save(assignment);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedAssignment.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value = "/api/assignments/{id}", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "update the assignment selected by id", response = ResponseEntity.class)

	public ResponseEntity<Object> updateAssignment(@RequestBody Assignment student, @PathVariable long id) {

		Optional<Assignment> assignmentOptional = repository.findById(id);

		if (!assignmentOptional.isPresent())
			return ResponseEntity.notFound().build();

		student.setId(id);
		
		repository.save(student);

		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value = "/api/assignments/{id}", method = RequestMethod.DELETE, produces = "application/json")
	@ApiOperation(value = "delete the assignment selected by id", response = ResponseEntity.class)
	public void deleteStudent(@PathVariable long id) {

		repository.deleteById(id);
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ApiOperation(hidden=true, value = "")
	public String homePage(ModelMap model) {
	    return "<!DOCTYPE html>\n" + 
	    		"\n" + 
	    		"\n" + 
	    		" <html>\n" + 
	    		" <head>\n" + 
	    		" <title>API Demo</title>\n" + 
	    		" </head>\n" + 
	    		" <body>\n" + 
	    		" <ul>\n" + 
	    		" <li>REST entry point /api/assignments</li>\n" + 
	    		" <li>  <a href=\"/swagger-ui.html\">Embedded Swagger API Testing tool</a> </li>\n" + 
	    		" <li> <a href=\"/v2/api-docs\">  API spec in Open API Json format </a> </li>\n" + 
	    		" </ul>\n" + 
	    		"<pre>"+
	    		"h1. Tech design\n" + 
	    		"\n" + 
	    		"Context\n" + 
	    		"- A proprietary LMS oriented PAAS\n" + 
	    		"\n" + 
	    		"assumptions\n" + 
	    		"- server side clients mostly. // we dont solve browser compatibility(IE) issues for JSONP, CORS, no browser bookmarking support\n" + 
	    		"    - HTTP verbs and HTTP status code for error signaling \n" + 
	    		"\n" + 
	    		"\n" + 
	    		"API\n" + 
	    		"- object oriented\n" + 
	    		"    - entity\n" + 
	    		"    - methods\n" + 
	    		"    - response representation types\n" + 
	    		"- Interoperable/open standards based\n" + 
	    		"    - request/response format. Json  \n" + 
	    		"- Stateless\n" + 
	    		"    - REST\n" + 
	    		"    - aggregatable/reusable\n" + 
	    		"- Discoverable\n" + 
	    		"    - explorable by any browser, auto generated plain html discoverability UI and links to links to entities and methods\n" + 
	    		"    - a standard based API tester tool integration\n" + 
	    		"- High performance\n" + 
	    		"    - caching for cacheable responses on HTTP level\n" + 
	    		"    - compression at HTTP level\n" + 
	    		"    - server cache shared across stateless API servers. Redis or Memecached\n" + 
	    		"- Scalable\n" + 
	    		"- Highly available\n" + 
	    		"    - health checks for high availability/load balancing\n" + 
	    		"    - Data storage - redundant, master<->master AWS cross region replicated \n" + 
	    		"- Maintainable\n" + 
	    		"    - health check has  \"fake failure\" mode turnable on/off. for maintenance\n" + 
	    		"    - version traceable to Git commit & last deployment time visible in discovery interface\n" + 
	    		"    - log files\n" + 
	    		"        - plain text format friendly to to simple tooling // one log record per line, date/time, priority, thread id/web session id, headline, log record content\n" + 
	    		"        - stored outside of application folder in file system/preserved on deploy\n" + 
	    		"    - usage metrics collectable and exposed via \"monitoring API\"\n" + 
	    		"    - configuration in external plain text files reachable on local file system, editable/deployable by configuration management tooling.\n" + 
	    		"        - no central configuration server, to minimize dependencies\n" + 
	    		"    - instrumental for APM (New Relic, DogWatch,etc)\n" + 
	    		"- Testable\n" + 
	    		"    - Mocks support for code and configuration\n" + 
	    		"    - Swagger \n" + 
	    		"- Upgradable\n" + 
	    		"    - No explicit versioning, given expected variety and volume of customers. Evolutionary design instead.\n" + 
	    		"        - customer to ignore tags/object properties/object methods customer does not need\n" + 
	    		"        - API vendor to ensure business objects changes are always backward compatible\n" + 
	    		"            - add new methods/properties. don't change semantic of existing ones\n" + 
	    		"            - acceptable change exiting properties/methods by relaxing restrictions (sample: raise max length allowed), but never by tightening restrictions  \n" + 
	    		"            - never blind physically remove\n" + 
	    		"            - track usage of outdated features and physically remove only when not used by customer\n" + 
	    		"	    - mark deprecated fields/methods/objects by @Deprecated attribute/communicate to customer	\n" + 
	    		"- Secure\n" + 
	    		"    - secure transport(SSL)\n" + 
	    		"    - user input validation\n" + 
	    		"        - validate when data crossing boundary of a service/component\n" + 
	    		"        - only whitelisted characters welcomed\n" + 
	    		"        - don't try sanitize malicious input - just reject it\n" + 
	    		"    - SQL injection protected - prepared statements\n" + 
	    		"    - XSS protected - dont copy user input into output.\n" + 
	    		"    - authentication\n" + 
	    		"        - access token\n" + 
	    		"    - authorization\n" + 
	    		"        - relies on external micro service \n" + 
	    		"- API signatures\n" + 
	    		"//create\n" + 
	    		"POST /api/assignments/\n" + 
	    		"//update\n" + 
	    		"PUT /api/assignments/{id}\n" + 
	    		"//delete\n" + 
	    		"DELETE /api/assignments/{id}\n" + 
	    		"//get all\n" + 
	    		"GET /api/assignments/\n" + 
	    		"//sorting,paginating,filtering|search by\n" + 
	    		"GET api/assignments/?limit=20&startFrom:Id={assignmentId}\n" + 
	    		"GET api/assignments/?limit=20&startFrom:datetime={assignment.create.datetime}\n" + 
	    		"GET api/assignments/?limit=20&startFrom:datetime={assignment.create.datetime}&searchBy:tag=teacher,student\n" + 
	    		"\n" + 
	    		"Implementation shortcuts TBD/not implemented\n" + 
	    		"-  fake data layer //in memory dev only DB\n" + 
	    		"-  consistent output format/error codes not implemented\n" + 
	    		"-  search by tag not implemented\n" + 
	    		"-  hypermedia in response\n" + 
	    		"-  security/entitlements\n" + 
	    		"-  etc,etc "
	 
	    		+ "</pre>"+
	    		" </body>\n" + 
	    		" </html>";
	}

}
