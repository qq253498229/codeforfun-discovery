create table if not exists cff_service
(
    id          bigint auto_increment primary key,
    name        varchar(200) comment '服务名',
    remark      varchar(500) comment '备注',
    is_server   tinyint default 0 comment '是否是服务端',
    is_master   tinyint comment '是否是主机。只有是服务端的时候才能是主机',
    last_active datetime comment '上次活跃时间'
);