INSERT INTO accounts_type (account_type_code_id, account_type_code)
VALUES
    (gen_random_uuid(), 'SAVING'),
    (gen_random_uuid(), 'PAYROLL'),
    (gen_random_uuid(), 'LOAN'),
    (gen_random_uuid(), 'CHECKING');
