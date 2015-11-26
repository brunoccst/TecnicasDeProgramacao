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

insert into parquimetros(ENDERECO) values('Av. Bento Gon�alves, 1990');
insert into parquimetros(ENDERECO) values('Av. Bento Gon�alves, 2000');
insert into parquimetros(ENDERECO) values('Av. Ipiranga, 800');
insert into parquimetros(ENDERECO) values('Av. Ipiranga, 1600');
insert into parquimetros(ENDERECO) values('Av. Ipiranga, 670');
insert into parquimetros(ENDERECO) values('Av. Ipiranga, 233');
insert into parquimetros(ENDERECO) values('R. Prof. Cristiano Fischer, 200');
insert into parquimetros(ENDERECO) values('R. Prof. Cristiano Fischer, 759');

insert into cartoesrecarregaveis(ID, SALDO, RESIDENTE) values('bGOTT10M00H1vibvmlQWCGA50nTddmrTcb8pMqOtApdseKiOININVtHzFBGx67LuIr0NNAJRLxu1csZZPZXk8VqooDvMBAMC5j2IT5BKoXTfBzcsNEsQ9QxipuWoIAuV', 20.0, 1);
insert into cartoesrecarregaveis(ID, SALDO, RESIDENTE) values('74GMqjHohUl7v91BLpYE2w3d1tP0z50qClot9bqEa5d7YZmearJXVTsarSePaksdNN7i8VMDh9qyCLBr3rcMKckJNOZtN9Av5uWviBZAjS0EpHLtSkxNx4lqzy89OdU4', 50.0, 0);
insert into cartoesrecarregaveis(ID, SALDO, RESIDENTE) values('XRhsRAMkZOWHW0P04P2QQqx0tLtB3GSlxblsFjmV5iQZPuJigfaSyw3EXTBVX9yLn64IWCsdy8WT6fpOOC2aZyOAGoRaJYL7ak8r58O4LH5bymta9ymfGaH0EVVlRrsp', 12.0, 1);
insert into cartoesrecarregaveis(ID, SALDO, RESIDENTE) values('IT906qeHogW4sU7iDoCsz1FopDwtoshZf4mpPiTQd06Aj7nbnPLjF3lryWMwvuhfgepOdBX1lC8i7C12ykYlTPmgcBesSpvzbuHOfy3rAFgzMULojH1MUoeqXqzLYZs6', 18.0, 1);
insert into cartoesrecarregaveis(ID, SALDO, RESIDENTE) values('Qpl3OfsDt3JOHsZ0JVvYcftEo3A4iE6HPb5uEFfHZUV1awhegyvD2oLuH8HnlrViai0LOugeHi8v2N2natqSBpWMCQGsOeZ9tYObPfMlTPojSf4fM1U3nCj4yVn2nPv4', 90.0, 1);
insert into cartoesrecarregaveis(ID, SALDO, RESIDENTE) values('Pz1f6Fb4J1s9HiVmDnUZCQWePPSTUamKYOHJyUyVRRCno6GWbacpZCrtKfOjEwuUvhCjw2a1geeBBn4K8LPkbrLKoCHO2e5uFwoVveVr9QuJSfbhSK1jCuXyYGP0AlAv', 75.0, 0);
insert into cartoesrecarregaveis(ID, SALDO, RESIDENTE) values('PRHpGI0bpTZPLqdjdufsDQZsYDQeGGeueHI3I6JLv5cHAzXYVw1WrxF09XniV4WhaieM5Ygwb5NYz1xv1zY2C8VR0KoqVYwGVNG7VHNo0Bx1PD2LxhiRBX3DvOPBPMLH', 15.0, 0);