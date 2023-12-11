create table USERS(
  ID int not null AUTO_INCREMENT,
  USERNAME varchar(100) not null,
  PASSWORD varchar(100) not null,
  STATUS int,
  PRIMARY KEY ( ID )
);

create table COUNTRIES(
  ID int not null AUTO_INCREMENT,
  NAME varchar(100) not null,
  PRIMARY KEY ( ID )
);