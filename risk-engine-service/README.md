<h1>Design Considerations</h1>

1. Manual control over the offset commiting, allows me to only commit after the payment was processed successfully and persisted to the DB.
2. The payment ID is not auto generated since I want to create the ID on the payment-service and persist it to the DB
