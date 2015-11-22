-- Drop das tabelas
drop table Tickets;
drop table Parquimetros;
drop table Enderecos;
drop table Moedas;


create table Enderecos
(
    id integer not null primary key generated always as identity(start with 1, increment by 1),
    endereco varchar(100) not null,
    numero int not null
    -- unique (endereco, numero) --TODO: Verificar se eh necessario ser unique
                                 -- (Dois parquimetros nao tem mesmo endereco e numero?)
);

create table Parquimetros
(
    id integer not null primary key generated always as identity(start with 1, increment by 1),
    id_endereco int not null,
    foreign key (id_endereco) references Enderecos (id)
);

create table Tickets
(
    id integer not null primary key generated always as identity(start with 1, increment by 1),
    id_parquimetro int not null,
    data_emissao date not null,
    data_validade date not null,
    foreign key (id_parquimetro) references Parquimetros (id)
);

create table Moedas
(
    id integer not null primary key generated always as identity(start with 1, increment by 1),
    valor double unique not null,
    nome varchar(100) not null
);
