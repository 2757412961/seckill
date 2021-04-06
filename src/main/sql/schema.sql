CREATE DATABASE seckill;

use seckill;

CREATE table seckill (
    seckill_id bigint not null AUTO_INCREMENT COMMENT '商品库存id',
    name varchar(120) not null comment '商品名称',
    number NOT NULL comment '库存数量',
    start_time timestamp  not null  comment '秒杀开始时间',
    end_time timestamp not null comment '秒杀结束时间',
    create_time  timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
    PRIMARY KEY (seckill_id),
    key idx_start_time(start_time),
    key idx_end_time(end_time),
    key idx_create_time(create_time)
) ENGINE= InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf-8 COMMENT='秒杀库存表'

-- 初始化数据
insert into seckill(name,number,start_time,end_time)
 Values ('1000元秒杀iphone6',100,'2022-02-01 00:00:00','2022-11-02 00:00:00');
insert into seckill(name,number,start_time,end_time)
 Values ('500元秒杀ipad2',200,'2021-02-01 00:00:00','2022-11-02 00:00:00');
insert into seckill(name,number,start_time,end_time)
 Values ('300元秒杀小米4',300,'2021-02-01 00:00:00','2022-11-02 00:00:00');
insert into seckill(name,number,start_time,end_time)
 Values ('200元秒杀红米note',400,'2021-02-01 00:00:00','2022-11-02 00:00:00');

create table success_killed(
    seckill_id BIGINT NOT NULL COMMENT '秒杀商品id',
    user_phone BIGINT NOT NULL COMMENT '用户手机号',
    state TINYINT NOT NULL  NOT NULL DEFAULT -1 COMMENT '标识状态 -1 无效 0成功 1已付款 2 已发货 ',
    create_time TIMESTAMP NOT NULL default CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (seckill_id,user_phone),
    KEY idx_create_time(create_time)
)ENGINE =InnoDB DEFAULT CHARSET =utf8 COMMENT ='秒杀成功明细';

-- connect database
-- mysql -uroot -p