CREATE DATABASE project1;

\c project1;


CREATE TABLE admin_details(admin_id int GENERATED ALWAYS AS IDENTITY, 
admin_password varchar(20), 
admin_first_name varchar(20), 
admin_last_name varchar(20), 
admin_contact bigint, 
admin_email varchar(20), 
admin_address varchar(50),
admin_role varchar(20),
PRIMARY KEY(admin_id));

INSERT INTO admin_details VALUES(DEFAULT, 
'password',
'John', 
'Smith', 
1234567, 
'email@gmail.com', 
'123 Cool Street USA', 
'admin');

CREATE TABLE employee_details(emp_id int GENERATED ALWAYS AS IDENTITY, 
emp_password varchar(20), 
emp_first_name varchar(20), 
emp_last_name varchar(20), 
emp_contact bigint, 
emp_email varchar(30), 
emp_address varchar(50), 
emp_role varchar(20), 
PRIMARY KEY(emp_id));

INSERT INTO employee_details VALUES(DEFAULT, 
'password', 
'Dan', 
'Mann', 
5555555, 
'email@hotmal.com', 
'4567 Main Street USA', 
'employee');

CREATE TABLE reimbursements_pending(pend_id int GENERATED ALWAYS AS IDENTITY, 
pend_request int, 
pend_amount double precision, 
pend_reason varchar(50), 
pend_request_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
pend_resolve_time varchar(20), 
pend_response int, 
pend_status varchar(20), 
PRIMARY KEY(pend_id), 
CONSTRAINT fk_empId FOREIGN KEY(pend_request) REFERENCES employee_details(emp_id) ON DELETE NO ACTION, 
CONSTRAINT fk_adminId FOREIGN KEY(pend_response) REFERENCES admin_details(admin_id) ON DELETE NO ACTION);

INSERT INTO reimbursements_pending VALUES(DEFAULT, 
1, 
435.87, 
'Work Trip', 
DEFAULT, 
'', 
1, 
'PENDING');

CREATE TABLE reimbursements_final(final_id int, 
final_request int, 
final_amount double precision, 
final_reason varchar(50), 
final_request_time varchar(40), 
final_resolve_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
final_response int, final_status varchar(20), 
PRIMARY KEY(final_id), 
CONSTRAINT fk_empId FOREIGN KEY(final_request) REFERENCES employee_details(emp_id) ON DELETE NO ACTION, 
CONSTRAINT fk_adminId FOREIGN KEY(final_response) REFERENCES admin_details(admin_id) ON DELETE NO ACTION);

