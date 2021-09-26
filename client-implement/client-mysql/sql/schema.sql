create table if not exists cff_service
(
    id          bigint auto_increment primary key,
    name        varchar(50) comment '服务名',
    remark      varchar(500) comment '备注',
    host        varchar(100) comment '地址',
    port        int comment '端口',
    last_active datetime comment '上次活跃时间',
    unique key `unique_service` (`host`, `port`)
);