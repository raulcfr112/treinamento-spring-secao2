create table tb_cidade(
    id_cidade bigint not null primary key ,
    nome varchar(50) not null ,
    qtd_habitantes bigint
);

insert into tb_cidade
    (id_cidade, nome, qtd_habitantes)
values
    (1, 'São Paulo', 12369845),
    (2, 'Rio de Janeiro', 32669815),
    (3, 'Fortaleza', 2645857);