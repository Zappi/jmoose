PRAGMA foreign_keys = ON;

CREATE TABLE Books 
(
	id integer,
	book_name varchar(60) NOT NULL,
	author_name varchar(60) NOT NULL,
	ISBN integer(13)
)

