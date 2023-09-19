-- liquibase formatted sql
-- changeset max:create-multiple-tables splitStatements:true endDelimiter:;

insert into user_accounts (username, password, email)
values ('Ivan Ivanov', '$2a$12$spqy3PjEu.uzAQZVL.84WeodAdACWmWGFRw9nyqaV0V99CHtOT5tO',
        'ivanov123@gmail.com'),
       ('Max Ivanovich', '$2a$12$RbqhQUjkBt6NM/3Wlq2EIekHV.SKKilHlXPWG35Tcj6MIo4eyRnKC',
        'mmmivanovich@gmail.com'),
       ('Oleg Zhuk', '$2a$12$SSKTSlLj4m9fvA22/6rs2uNlTm.bYd/76qG.5gxQef8qc1lga3t4G',
           'olzhuk@gmail.com');

insert into roles (id, authority)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');

insert into accounts_roles (user_account_id, role_id)
values (1, 2),
       (2, 2),
       (3, 1),
       (3, 2);