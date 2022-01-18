create table tb_cidade (
    id_cidade bigint not null primary key,
    nome varchar(50) not null,
    qtd_habitantes bigint
);

insert into tb_cidade
    (id_cidade, nome, qtd_habitantes)
values
       (1, 'Sao Paulo', 12396372),
       (2, 'Rio de Janeiro', 1800000),
       (3, 'Fortaleza', 824600),
       (4, 'Salvador', 11413134),
       (5, 'Belo Horizonte', 3413134),
       (6, 'Porto Alegre', 75434),
       (7, 'Porto Velho', 2413134),
       (8, 'Palmas', 63324),
       (9, 'Recife', 385342),
       (10, 'Natal', 1414124),
       (11, 'Brasilia', 1000000),
       (12, 'Vitoria', null),
       (13, 'Curitiba', null)
;


/*select * from tb_cidade where nome like 'S%' -> Comece com S e tenha qqr coisa dps*/
/*select * from tb_cidade where nome like '%S' -> Comece com qqr coisa e termine com S*/
/*select * from tb_cidade where nome like '%s%' -> Que contenha S*/



