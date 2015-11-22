-- Drop das tabelas
drop table Tickets;
drop table Parquimetros;
drop table Enderecos;
drop table Moedas;


create table Enderecos
(
    id int primary key not null,
    endereco varchar(100) not null,
    numero int not null
    -- unique (endereco, numero) --TODO: Verificar se eh necessario ser unique
                                 -- (Dois parquimetros nao tem mesmo endereco e numero?)
);

create table Parquimetros
(
    id int primary key not null,
    id_endereco int not null,
    foreign key (id_endereco) references Enderecos (id)
);

create table Tickets
(
    id int primary key not null,
    id_parquimetro int not null,
    data_emissao date not null,
    data_validade date not null,
    foreign key (id_parquimetro) references Parquimetros (id)
);

create table Moedas
(
    id int primary key not null,
    valor double unique not null,
    nome varchar(100) not null
);
