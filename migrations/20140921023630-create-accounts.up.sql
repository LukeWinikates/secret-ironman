CREATE TABLE accounts (
  id INT PRIMARY KEY DEFAULT nextval('account_seq'),
  email TEXT,
  phone TEXT,
  password_digest TEXT);
