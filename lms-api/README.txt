h1.
To build from sources:
mvn package
cp target/assignments-0.0.1-SNAPSHOT.jar .
docker build --build-arg JAR_FILE=assignments-0.0.1-SNAPSHOT.jar -t kaplan.assignment.api:1 .
docker run -p 8080:8080 kaplan.assignment.api:1
# open in browser http://http://localhost:8080/

or just run pre cooked docker image assignment.api.docker.img.exported 

h1. Tech considerations

Context
- A proprietary LMS oriented PAAS

Assumptions
- server side clients mostly. // we dont solve browser compatibility(IE) issues for JSONP, CORS, no browser bookmarking support
    - HTTP verbs and HTTP status code for error signaling 
API
- object oriented
    - entity
    - methods
    - response representation types
- Interoperable/open standards based
    - request/response format. Json
        - error code by HTTP status code AND json keys, both
        - 
- Stateless
    - REST
    - aggregatable/reusable
- Discoverable
    - explorable by any browser, auto generated plain html discoverability UI and links to links to entities and methods
    - a standard based API tester tool integration
- High performance
    - caching for cacheable responses on HTTP level
    - compression at HTTP level
    - server cache shared across stateless API servers. Redis or Memecached
- Scalable
- Highly available
    - health checks for high availability/load balancing
    - Data storage - redundant, master<->master AWS cross region replicated 
- Maintainable
    - health check has  "fake failure" mode turnable on/off. for maintenance
    - version traceable to Git commit & last deployment time visible in discovery interface
    - log files
        - plain text format friendly to to simple tooling // one log record per line, date/time, priority, thread id/web session id, headline, log record content
        - stored outside of application folder in file system/preserved on deploy
    - usage metrics collectable and exposed via "monitoring API"
    - configuration in external plain text files reachable on local file system, editable/deployable by configuration management tooling.
        - no central configuration server, to minimize dependencies
    - instrumental for APM (New Relic, DogWatch,etc)
- Testable
    - Mocks support for code and configuration
    - Swagger 
- Upgradable

        - Given volume of usage and variety of customers expected, explicit versioning would be painful. Do evolutionary approach instead 
            - customer to ignore tags/object properties/object methods customer does not need
            - API vendor to ensure business objects change are always backward compatible
                - add new methods/properties. don't change semantic of existing ones
                - acceptable change exiting properties/methods by relaxing restrictions (sample: raise max length allowed), but never by tightening restrictions  
                - never blind physically remove
                - track usage of outdated features and physically remove only when not used by customer
- Secure
    - secure transport(SSL)
    - user input validation
        - validate when data crossing boundary of a service/component
        - only whitelisted characters welcomed
        - don't try sanitize malicious input - just reject it
    - SQL injection protected - prepared statements
    - XSS protected - dont copy user input into output.
    - authentication
        - access token
    - authorization
        - relies on external service 
- API signatures
//create
POST /api/assignments/
//update
PUT /api/assignments/{id}
//delete
DELETE /api/assignments/{id}
//get all
GET /api/assignments/
//sorting,paginating,filtering|search by
GET api/assignments/?limit=20&startFrom:Id={assignmentId}
GET api/assignments/?limit=20&startFrom:datetime={assignment.create.datetime}
GET api/assignments/?limit=20&startFrom:datetime={assignment.create.datetime}&searchBy:tag=teacher,student

- 
