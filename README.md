<h1>Business Assumptions</h1>

1. I assume that the POC will not include an API for creating/updating/deleting users or payment_methods. 
The DB comes pre-populated with 2 users and 2 payment-methods for demonstrating the POC.
2. I assume that the POC will only persist payments to a transaction log and not actually perform the money transfers. 
3. I performed basic validations on inputs but additional validations must be made for production grade services.
for example, I didn't check that a user can't send a payment to himself. 


<h1>Design Decisions</h1>

1. Why I chose Kafka - Kafka is built for horizontal scalability while RabbitMQ is scaled vertically.
2. Why I chose PostgresSQL - A widely adopted relational DB
3. Mapping entities to DTOs - chose a mapping library over custom mappings to reduce boilerplate code, however there is a cost to this decision, since automatic mapping library can introduce unexpected results there must be tests for each mapping.
4. Why I chose to separate DTOs from Request/Response objects - There is somewhat of duplication with 3 different model classes - entities, DTOs and request/response, this may seem redundant at the POC level but as the project grows the different models will make more sense and have actual differences between them.
5. To support the transaction executions I suggest a third microservice that receives approved transactions from the risk engine and processes them.
6. chose to control manually the offset commit on the risk engine side. This way the risk engine only commits a payment if it was successfully persisted to the DB. In case of failure the message will return to the topic and will go through another retry.


<h1>Usage Explanation</h1>

<h2>Starting the Payment Platform</h2>

1. Enter the directory of the downloaded source code
2. Run the script for building the artifacts and bringing up the docker containers:
```
sh start-payment-platform.sh
```
3. This scripts deletes previous build artifacts, rebuilds them and runs docker-compose up with all required components.

<h2>Working with the Payment Platform</h2>

<h3>API documentation</h3>

1. GET /v1/users/{user_id} - get all metadata of a user by his id

2. GET /v1/users?searchTerm={search_term} - 
   - find all users whose full name matches the search term param query param
   - you can leave blank to get all users - this behavior is for POC level only and should be removed in future version.

3. GET /v1/users/{user_id}/payment-methods -
   - get all payment-methods for a user 

4. POST /v1/payments
Body - a json containing the following keys:
   1. payerId - an existing user id
   2. payeeId - an existing user id
   3. paymentMethodId - a payment method belonging to the paying user
   4. currency - a 3 letter (ISO 4217) currency code
   5. amount - an amount of the currency to transfer

example for a valid body:
```
{
   "payerId": "5f534037-f25f-4ed8-b54a-1e0f62a250a7",
   "payeeId": "a6bcd14b-0d28-4611-babd-dc5ffe3589fa",
   "paymentMethodId": "af37be8b-20f2-4a65-bbff-c946e586286b",
   "currency": "USD",
   "amount": 2303
}
```


<h2>Stopping the Payment Platform</h2>
1. Enter the directory of the downloaded source code
2. Run the script for shutting down the docker containers:

```
sh stop-payment-platform.sh
```

