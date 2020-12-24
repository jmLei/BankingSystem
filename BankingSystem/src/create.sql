
use bankingsystem;


drop table account;
drop table customer;

create table customer (
  id      integer     not null AUTO_INCREMENT,
  name    varchar(15) not null,
  gender  char        not null check (gender = 'M' or gender = 'F'),
  age     integer     not null check (age >= 0),
  pin     integer     not null check (pin >= 0),
  constraint pk_id primary key (id)
);

ALTER TABLE customer AUTO_INCREMENT=100;

create table account (
  number  integer     not null AUTO_INCREMENT,
  id      integer     not null,
  balance integer     not null check (balance >= 0),
  type    char        not null check (type = 'C' or type = 'S'),
  status  char        not null check (status = 'A' or status = 'I'),
  constraint pk_number primary key (number),
  constraint fk_id foreign key (id) references customer(id) on delete cascade
);

ALTER TABLE account AUTO_INCREMENT=1000;

