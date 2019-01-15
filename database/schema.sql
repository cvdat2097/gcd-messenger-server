-- ==================== SCHEMA ===================
CREATE DATABASE IF NOT EXISTS GCD_MESSENGER_DB;
USE GCD_MESSENGER_DB;

-- USER
DROP TABLE IF EXISTS users;
CREATE TABLE users(
	id int auto_increment primary key unique,
    username varchar(20) unique not null,
    pass varchar(20),
    fullname nvarchar(50),
    avatar text,
    created_timestamp timestamp not null default current_timestamp,
    active bool default 1
);

-- MESSAGES
DROP TABLE IF EXISTS messages;
CREATE TABLE messages(
	id int auto_increment primary key,
    content text not null,
    userid int not null references users(id),
    ms_timestamp timestamp not null default current_timestamp,
    active bool default 1
);


-- ==================== TEST DATA ===================
INSERT INTO users
	(username, pass, fullname, avatar)
VALUES
	('cvdat2097', '123', 'Cao Vo Dat', 'https://community.intersystems.com/sites/default/files/pictures/picture-default.jpg'),
    ('prouser01', '123', 'Nguyen Van A', 'https://community.intersystems.com/sites/default/files/pictures/picture-default.jpg'),
    ('prouser02', '123', 'Nguyen Van B', 'https://community.intersystems.com/sites/default/files/pictures/picture-default.jpg'),
    ('prouser03', '123', 'Nguyen Van CD', 'https://community.intersystems.com/sites/default/files/pictures/picture-default.jpg'),
    ('prouser04', '123', 'Nguyen Van AEG', 'https://community.intersystems.com/sites/default/files/pictures/picture-default.jpg'),
    ('prouser05', '123', 'Nguyen Van EFG', 'https://community.intersystems.com/sites/default/files/pictures/picture-default.jpg'),
    ('prouser06', '123', 'Nguyen Van GDB', 'https://community.intersystems.com/sites/default/files/pictures/picture-default.jpg'),
    ('prouser07', '123', 'Nguyen Van DEFG', 'https://community.intersystems.com/sites/default/files/pictures/picture-default.jpg');

INSERT INTO messages
	(content, userid)
VALUES
	('1 Hello', 1),
    ('2 How are your?', 2),
    ('3 I\'m very fine', 1),
    ('4 Thank you, and you?', 2),
    ('5 I\'m fucking awesome atm!', 1),
    ('6 It\'s good to know that', 4),
    ('7 What is your intention?', 5),
    ('8 I\'m going to graduate from HCMUS and then get a life! :(((', 1),
    ('9 What\'s wrong?', 3),
    ('10 Nothing, I just need a break.', 1),
    ('11 So you overwork yourself or what?', 2),
    ('12 No bc I relax way too much u know :))', 1),
    ('13 Damn... @@', 3),
    ('14 Time to leave, goodbye pal.', 1),
    ('14 Bye', 2);


