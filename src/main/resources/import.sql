-- INITIAL DATA
INSERT INTO client (id, name, surname, personal_code, email, phone) VALUES (1, 'John', 'Bush', '111111-11111', 'john.bush@homework', '+37122222222');
INSERT INTO client (id, name, surname, personal_code, email, phone) VALUES (2, 'Peter', 'Singer', '222222-22222', 'peter.singer@homework', '+37123333333');

INSERT INTO loan (id, client_id, application_date, term_days, term_date, amount, outstanding_amount, ip_address) VALUES (1, 1, '2017-03-01', 30, '2017-03-31', 100.00, 100.00, '0:0:0:0:0:0:0:1');
INSERT INTO loan (id, client_id, application_date, term_days, term_date, amount, outstanding_amount, ip_address) VALUES (2, 1, '2017-03-01', 10, '2017-03-11', 50.00, 50.00, '0:0:0:0:0:0:0:1');
INSERT INTO loan (id, client_id, application_date, term_days, term_date, amount, outstanding_amount, ip_address) VALUES (3, 2, '2017-03-01', 20, '2017-03-21', 150.00, 150.00, '0:0:0:0:0:0:0:1');

INSERT INTO extension (id, loan_id, extension_date, fee, term_days, term_date, day_factor) VALUES (1, 1, '2017-03-05', 50.00, 7, '2017-04-07', 0.21);
UPDATE loan SET term_date = '2017-04-07', outstanding_amount = 150 WHERE id = 1;

INSERT INTO extension (id, loan_id, extension_date, fee, term_days, term_date, day_factor) VALUES (2, 3, '2017-03-05', 75.00, 7, '2017-03-28', 0.21);
UPDATE loan SET term_date = '2017-03-28', outstanding_amount = 225 WHERE id = 3;