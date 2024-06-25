drop database moonstonejewerlary;

create database moonstonejewerlary;

use moonstonejewerlary;

create table User(
                     uId Varchar(6) primary key not null,
                     uName Varchar(10),
                     uRole Varchar(20),
                     uPassword Varchar(10) not null
);

INSERT INTO User(uId, uName, uRole, uPassword) VALUES
                        ('U001', 'Lasha', 'Cashier', '1234');


create table Customer(
                         cId Varchar(6) primary key,
                         cName Varchar(10),
                         cAddress Varchar(20),
                         cTel Varchar(15),
                         cEmail varchar (20)not null,
                            uId Varchar(6) not null,
                         constraint foreign key(uId) references User(uId) on delete cascade on update cascade
);


create table Orders(
                       oId Varchar(10) primary key,
                       date Date,
                       cId Varchar(6),
                       constraint foreign key(cId) references Customer(cId) on delete cascade on update cascade
);

create table Payment(
                        pId Varchar(6) primary key,
                        cId Varchar(6) not null,
                        constraint foreign key(cId) references Customer(cId) on delete cascade on update cascade,
                        oId VARCHAR(10) not null,
                        constraint foreign key(oId) references Orders(oId) on delete cascade on update cascade,
                        pAmount Decimal(10,2),
                        date Date
);




create table Item(
                     iCode Varchar(10) primary key,
                     iName Varchar(30),
                     iCategory Varchar(100),
                     iQty Int(20),
                     iPrice Decimal(10,2),
                     Date date

);



create table Employee(
                         eId Varchar(6) primary key,
                         eName Varchar(10),
                         eAddress Varchar(20),
                         eTel Varchar(15),
                         uId Varchar(6) not null,
                         constraint foreign key(uId) references User(uId) on delete cascade on update cascade
);


create table Salary(
                       sId Varchar(6) primary key,
                       sAmount Decimal(10,2),
                       date Date,
                       eId Varchar(6) not null,
                       constraint foreign key(eId) references Employee(eId) on delete cascade on update cascade
);


create table Order_Detail(
                             oId Varchar(10),
                             constraint foreign key(oId) references Orders(oId) on delete cascade on update cascade,
                             iCode Varchar(10) not null,
                             constraint foreign key(iCode) references Item(iCode) on delete cascade on update cascade,
                             price Decimal(10,2),
                             qty Int(20),
                             totalAmount Decimal(10,2)

);


create table Supplier(
                         supId Varchar(10) primary key not null,
                         supName Varchar(20)not null,
                         iName Varchar(100) not null,
                         supTelephone Varchar(15) not null,
                         uId Varchar(6) not null,
                         constraint foreign key(uId) references User(uId) on delete cascade on update cascade
);



create table Report(
                       rId Varchar(10) primary key,
                       rCetogery Varchar(500)
);


create table Report_Detail(
                              rId Varchar(10) not null,
                              pId Varchar(6) not null,
                              date Date,
                              constraint foreign key(rId) references Report(rId) on delete cascade on update cascade,
                              constraint foreign key(pId) references Payment(pId) on delete cascade on update cascade
);


create table Supplier_Detail(
                                id Varchar(10) primary key not null,
                                qty Int(20),
                                date Date,
                                supId Varchar(10) not null,
                                constraint foreign key(supId) references Supplier(supId) on delete cascade on update cascade
);
