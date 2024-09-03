<h1>Design Considerations</h1>

<h2>API Design</h2>
1. I followed the recommended REST API design principals, based on entities:
   1. users ```/v1/users```
      1. search by full name - add query param searchTerm 
      2. GET user by user id - ```/{user-id}``` 
   2. payment-methods /v1/users/{user-id}/payment-methods
      1. GET get user payment methods
   3. payments ```/v1/payments```
      1. POST create new payment

<h1>Testability Considerations</h1>
I suggest to develop tests from the bottom up - starting with unit-testing each component inside the microservices in isolation and ending with 
end-to-end testing of the entire flow.  
<h2>Unit tests</h2>

   1. controllers unit tests - cover happy path and all possible bad inputs for APIs
   2. model mappings unit tests - Test each mapping done by the ModelMapper library to make sure all fields are as expected
   3. services unit tests - Test all service methods and their logic (e.g. what happens if payer_id not found)
   4. Test input validations (required fields, field must conform to some format)
   5. Test exception handling - (e.g. Kafka unavailable) returned output to user.

<h2>Integration Tests</h2>

<h3>Setup</h3>
1. Use docker-compose to spin up the micro-service, Kafka and Postgres DB instances
2. Create a separate configuration for the integration tests to not affect the development process or affect 