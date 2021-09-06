create table Fornecedor (
	id serial primary key,
	cnpj char(12),
	nome varchar(100) unique not null,
	endereco varchar(200) unique,
	telefone varchar(20) unique,
	email varchar(50) unique,
	sicaf varchar(50) unique
);

create table MateriaPrima (
	id serial primary key,
	descricao varchar(100) unique not null,
	cas varchar(10) not null,
	dcb varchar(100) not null,
	dci varchar(100) not null
);

create table Embalagem (
	id serial primary key,
	descricao varchar(100) unique not null,
	material varchar(50),
	capacidade int
);

create table Licitacao (
	id serial primary key,
	dataCriacao date
);

create table FornecimentoMateriaPrima (
	idLicitacao int,
	idFornecedor int,
	idMateriaPrima int,
	preco numeric(6,2),
	primary key (idLicitacao, idFornecedor, idMateriaPrima)
);

create table FornecimentoEmbalagem (
    idLicitacao int,
    idFornecedor int,
    idEmbalagem int,
    preco numeric(6,2),
    primary key (idLicitacao, idFornecedor, idEmbalagem)
);
