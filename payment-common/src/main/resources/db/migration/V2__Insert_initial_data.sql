INSERT INTO users (id, user_name, email, password_hash, full_name, phone_number)
VALUES
(uuid_generate_v4(), 'john_doe', 'john.doe@example.com', 'hashed_password_1', 'John Doe', '123-456-7890'),
(uuid_generate_v4(), 'jane_smith', 'jane.smith@example.com', 'hashed_password_2', 'Jane Smith', '098-765-4321');

INSERT INTO user_payment_methods (user_id, payment_type, provider, account_number, expiration_date, billing_address, is_default, created_at, updated_at)
VALUES
((SELECT id FROM users WHERE user_name = 'john_doe'), 'CREDIT_CARD', 'VISA', '4111111111111111', '2025-12-31', '123 Main St, Anytown', TRUE, NOW(), NOW()),
((SELECT id FROM users WHERE user_name = 'jane_smith'), 'PAYPAL', 'PAYPAL', 'paypal_account@example.com', NULL, '456 Elm St, Anytown', FALSE, NOW(), NOW());
