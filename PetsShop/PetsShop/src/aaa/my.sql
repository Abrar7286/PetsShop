

use mydbl;
create table Form(
Name varchar(255) not null,
Address varchar(255),
ZipCode int(8) not null,
Country varchar(50) not null,
Gender varchar(1) not null,
Phone varchar(255) not null,
Email varchar(255) PRIMARY KEY,
Password varchar(255) not null
);
ALTER TABLE Form
ADD UserType varchar(10) after Username;