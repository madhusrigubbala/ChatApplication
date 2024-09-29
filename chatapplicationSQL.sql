create database chatapplication;
use chatapplication;
create table register( 
    username varchar(10),
    password varchar(10),
    mobileno long,
    userid int,
    email varchar(10));
CREATE TABLE messages (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sender_username VARCHAR(255) NOT NULL,
    receiver_username VARCHAR(255) NOT NULL,
    message_text TEXT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE groupsu (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);
CREATE TABLE  members (
    id INT AUTO_INCREMENT PRIMARY KEY,
    group_id INT,
    member_name VARCHAR(255) NOT NULL,
    FOREIGN KEY (group_id) REFERENCES groupsu(id)
);
CREATE TABLE  message (
    id INT AUTO_INCREMENT PRIMARY KEY,
    group_id INT,
    message TEXT NOT NULL,
    FOREIGN KEY (group_id) REFERENCES groupsu(id)
);
select * from groupsu;
select * from members;
select * from messages;
select * from register;
