CREATE TABLE comment  (
  `parent_id` bigint NOT NULL,
  `like_count` bigint(255) NOT NULL,
  `gmt_modified` bigint(255) NOT NULL,
  `gmt_create` bigint(255) NOT NULL,
  `commentator` int(255) NOT NULL,
  `type` int NOT NULL COMMENT '评论类型 1 ：直接评论问题 2：评论评论',
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键\r\n',
  PRIMARY KEY (`id`)
);