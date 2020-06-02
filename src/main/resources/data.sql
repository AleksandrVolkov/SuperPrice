 insert into backet (id) values
 (-1),
 (-2);

-- password = 'admin'

insert into my_user (id, backet_id, email, first_name, last_name, user_name, password) values
('1', -1, 'test_email', 'test_first', 'test_last', 'test_username', '$2a$04$I.njcaSiDeGLYlTW7nGjhu6FN30Dj.zWP0A9eBmthHoMewv5O3FM2'),
('2', -2, 'test_email1', 'test_first1', 'test_last1', 'test_username1', '$2a$04$I.njcaSiDeGLYlTW7nGjhu6FN30Dj.zWP0A9eBmthHoMewv5O3FM2');

insert into roles (id, name) values
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

insert into user_roles (user_id, role_id) values
(1, 1),
(1, 2),
(2, 1),
(2, 2);
