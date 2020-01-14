# tender-service

#0.1.0
 - For deploy - configure following properties:
    - spring.flyway.url 
    - spring.flyway.user
    - spring.flyway.password
    - spring.flyway.schemas
    - spring.datasource.url
    - spring.datasource.username
    - spring.datasource.password
    
 - Run Application (or Test) for initial population of MySQL database (DDL)
    - Service should create database (schema) if does not exist (and user has those privileges)
    - Run tests (TenderServiceApplicationTest) for initial population of company, tender and offer data
    
 - Api documentation (swagger auto-generated): http://localhost:8080/v2/api-docs
 - Database model: tender-service/tender.png