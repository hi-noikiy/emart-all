CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL COMMENT 'username',
  `password` varchar(64) DEFAULT NULL COMMENT 'password',
  `email` varchar(64) DEFAULT NULL COMMENT 'email',
	`roles` char(1) DEFAULT NULL COMMENT 'roles',
	`confirm_password` varchar(64) DEFAULT NULL COMMENT 'confirm password',
	`mobile_number` varchar(200) DEFAULT NULL COMMENT 'mobile number',
	`contact_number` varchar(200) DEFAULT NULL COMMENT 'contact number',
	`company_name` varchar(200) DEFAULT NULL COMMENT 'company name',
	`brief_about_company` varchar(200) DEFAULT NULL COMMENT 'brief about company',
	`postal_address` varchar(200) DEFAULT NULL COMMENT 'postal address',
	`create_time` datetime DEFAULT NULL COMMENT 'create time',
  `update_time` datetime DEFAULT NULL COMMENT 'update time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='user';


INSERT INTO `user`(`id`, `username`, `password`, `email`, `roles`, `confirm_password`, `mobile_number`, `contact_number`, `company_name`, `brief_about_company`, `postal_address`, `create_time`, `update_time`) VALUES (1, 'xuan', '123456', '123@ibm.com', '1', '123456', '15840999123', NULL, NULL, NULL, NULL, NULL, NULL);
