CREATE TABLE launch (
	code BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL,
	due_date DATE NOT NULL,
	payment_date DATE,
	value DECIMAL(10,2) NOT NULL,
	observation VARCHAR(100),
	type VARCHAR(20) NOT NULL,
	category_code BIGINT(20) NOT NULL,
	people_code BIGINT(20) NOT NULL,
	FOREIGN KEY (category_code) REFERENCES category(code),
	FOREIGN KEY (people_code) REFERENCES people(code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO launch (description, due_date, payment_date, value, observation, type, category_code, people_code) values ('Month Salary', '2017-06-10', null, 3900.00, 'Profit share', 'INCOME', 1, 1);
INSERT INTO launch (description, due_date, payment_date, value, observation, type, category_code, people_code) values ('Month Salary', '2017-06-10', null, 2800.00, null, 'EXPENSE', 2, 2);
INSERT INTO launch (description, due_date, payment_date, value, observation, type, category_code, people_code) values ('Month Salary', '2017-06-10', null, 2000.00, 'Profit share', 'INCOME', 3, 3);
INSERT INTO launch (description, due_date, payment_date, value, observation, type, category_code, people_code) values ('Month Salary', '2017-06-10', null, 4500.00, null, 'EXPENSE', 4, 4);
INSERT INTO launch (description, due_date, payment_date, value, observation, type, category_code, people_code) values ('Month Salary', '2017-06-10', null, 1300.00, null, 'EXPENSE', 3, 4);
INSERT INTO launch (description, due_date, payment_date, value, observation, type, category_code, people_code) values ('Month Salary', '2017-06-10', null, 6700.00, 'Profit share', 'INCOME', 3, 5);
INSERT INTO launch (description, due_date, payment_date, value, observation, type, category_code, people_code) values ('Month Salary', '2017-06-10', null, 1100.00, 'Profit share', 'EXPENSE', 4, 6);
INSERT INTO launch (description, due_date, payment_date, value, observation, type, category_code, people_code) values ('Month Salary', '2017-06-10', null, 3300.00, null, 'EXPENSE', 1, 7);
INSERT INTO launch (description, due_date, payment_date, value, observation, type, category_code, people_code) values ('Month Salary', '2017-06-10', null, 3500.00, null, 'EXPENSE', 4, 8);
INSERT INTO launch (description, due_date, payment_date, value, observation, type, category_code, people_code) values ('Month Salary', '2017-06-10', null, 7000.00, null, 'INCOME', 5, 10);
INSERT INTO launch (description, due_date, payment_date, value, observation, type, category_code, people_code) values ('Month Salary', '2017-06-10', null, 9000.00, 'Profit share', 'INCOME', 5, 4);
