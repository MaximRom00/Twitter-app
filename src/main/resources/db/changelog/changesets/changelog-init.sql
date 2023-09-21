-- liquibase formatted sql
-- changeset max:create-multiple-tables splitStatements:true endDelimiter:;

create table user_accounts
(
    id       int not null auto_increment,
    username varchar(255) not null unique,
    password varchar(255) not null,
    image_link varchar(255),
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


create table tweets
(
    id       int not null auto_increment ,
    message varchar(255) not null ,
    user_id int not null ,
    created_timestamp timestamp not null ,
    updated_timestamp timestamp not null ,
    PRIMARY KEY (`id`),
    constraint tweets_user_account_fk foreign key (user_id) references user_accounts(id)
);

update tweets set updated_timestamp = created_timestamp where updated_timestamp IS NULL;
