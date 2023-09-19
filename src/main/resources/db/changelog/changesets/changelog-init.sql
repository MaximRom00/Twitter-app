-- liquibase formatted sql
-- changeset max:create-multiple-tables splitStatements:true endDelimiter:;

create table user_accounts
(
    id       int not null auto_increment,
    username varchar(255) not null unique,
    password varchar(255) not null,
    email    varchar(255) not null unique,
    PRIMARY KEY (`id`)
);

create table roles
(
    id       int not null auto_increment ,
    authority varchar(32) not null unique,
    PRIMARY KEY (`id`)
);

create table accounts_roles
(
  user_account_id int not null,
  role_id int not null,
  constraint `users_roles_roles_fk` foreign key (`role_id`) references roles (id),
  constraint `users_roles_user_account_fk` foreign key (`user_account_id`) references user_accounts (id)
);


# insert into roles(authority) value ('ROLE_ADMIN'), ('ROLE_USER');