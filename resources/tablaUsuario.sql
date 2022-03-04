create table usuario
(
    usu_id             int auto_increment
        primary key,
    usu_nombre         varchar(80) not null,
    usu_tipo_documento varchar(4)  not null,
    usu_documento      varchar(11) not null,
    constraint tipo_documento_and_documento_UNIQUE
        unique (usu_tipo_documento, usu_documento)
);