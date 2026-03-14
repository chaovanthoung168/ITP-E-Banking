-- liquibase formatted sql

-- changeset thoung:1773043259451-1
CREATE TABLE accounts
(
    account_id      UUID NOT NULL,
    customer_id     UUID,
    branch_id       UUID,
    account_number  VARCHAR(255),
    account_holder  VARCHAR(255),
    status          VARCHAR(255),
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    created_by      VARCHAR(255),
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_by      VARCHAR(255),
    account_type_id UUID,
    CONSTRAINT pk_accounts PRIMARY KEY (account_id)
);

-- changeset thoung:1773043259451-2
CREATE TABLE accounts_type
(
    account_type_code_id UUID NOT NULL,
    account_type_code    VARCHAR(255),
    CONSTRAINT pk_accounts_type PRIMARY KEY (account_type_code_id)
);

-- changeset thoung:1773043259451-3
ALTER TABLE accounts
    ADD CONSTRAINT FK_ACCOUNTS_ON_ACCOUNT_TYPE FOREIGN KEY (account_type_id) REFERENCES accounts_type (account_type_code_id);

