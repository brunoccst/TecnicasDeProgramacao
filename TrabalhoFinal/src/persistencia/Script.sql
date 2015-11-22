-- Drop das tabelas
drop table Tickets;
drop table CartoesRecarregaveis;
drop table Parquimetros;
drop table Configuracoes;

create table CartoesRecarregaveis
(
    id varchar(128) not null primary key,
    saldo double,
    residente smallint
);

create table Parquimetros
(
    id integer not null primary key generated always as identity(start with 1, increment by 1),
    endereco varchar(255)
);

create table Tickets
(
    parquimetro_id integer not null,
    serial integer not null,
    data_emissao date,
    hora_emissao time,
    data_validade date,
    hora_validade time,
    cartao_id varchar(128),
    primary Key(parquimetro_id, serial),
    foreign key (parquimetro_id) references Parquimetros (id),
    foreign key (cartao_id) references CartoesRecarregaveis (id)
);

create table Configuracoes
(
    uniuqe integer not null primary key,
    inicio_tarifacao time,
    fim_tarifacao time,
    tempo_minimo integer,
    tempo_maximo integer,
    incremento integer,
    tarifa_incremento double,
    moeda5 smallint,
    moeda10 smallint,
    moeda25 smallint,
    moeda50 smallint,
    moeda100 smallint,
    cartao_recarregavel smallint,
    cartao_residente smallint
);