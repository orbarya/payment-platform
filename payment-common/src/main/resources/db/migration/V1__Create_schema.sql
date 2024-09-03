CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE payment_type AS ENUM ('CREDIT_CARD', 'PAYPAL', 'BANK_ACCOUNT');

CREATE TYPE provider AS ENUM ('VISA', 'MASTERCARD', 'PAYPAL', 'AMERICAN_EXPRESS', 'DISCOVER');

CREATE TABLE IF NOT EXISTS users (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    user_name VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS user_payment_methods (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    payment_type payment_type NOT NULL,                 -- E.g., 'credit_card', 'paypal', 'bank_account'
    provider provider,                                  -- E.g., 'Visa', 'Mastercard', 'PayPal'
    account_number VARCHAR(100) NOT NULL,               -- E.g., credit card number, bank account number
    expiration_date DATE,                               -- For credit cards
    billing_address TEXT,                               -- Address associated with the payment method
    is_default BOOLEAN DEFAULT FALSE,                   -- Indicates if this is the default payment method for the user
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS payments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    payer_id UUID REFERENCES users(id) ON DELETE CASCADE NOT NULL,
    payee_id UUID REFERENCES users(id) ON DELETE CASCADE NOT NULL,
    payment_method_id UUID REFERENCES user_payment_methods(id) NOT NULL,
    amount DECIMAL(15, 2) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    risk_score DECIMAL(3, 2) NOT NULL,
    is_passed_risk_engine BOOLEAN NOT NULL DEFAULT FALSE, -- Indicates if the payment was approved by the risk engine
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);