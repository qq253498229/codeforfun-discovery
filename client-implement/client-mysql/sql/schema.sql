create table if not exists cff_service
(
    id          bigint auto_increment primary key,
    name        varchar(200) comment '服务名',
    remark      varchar(500) comment '备注',
    last_active datetime comment '上次活跃时间'
);