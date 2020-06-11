CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
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
  `create_time` timestamp NOT NULL COMMENT 'create time',
  `update_time` timestamp NOT NULL COMMENT 'update time'
);


INSERT INTO `users`(`id`, `username`, `password`, `email`, `roles`, `confirm_password`, `mobile_number`, `contact_number`, `company_name`, `brief_about_company`, `postal_address`, `create_time`, `update_time`) VALUES (1, 'xuan', '123456', '123@ibm.com', '1', '123456', '15840999123', '0411-131234', 'ibm', 'World class AI solutions and cloud platform company  China', 'dalian', '2020-05-09 14:24:38', '2020-05-09 14:24:45');
INSERT INTO `users`(`id`, `username`, `password`, `email`, `roles`, `confirm_password`, `mobile_number`, `contact_number`, `company_name`, `brief_about_company`, `postal_address`, `create_time`, `update_time`) VALUES (2, 'lin', '123456', '456@ibm.com', '2', '123456', '15840999123', '0411-131234', 'ibm', 'World class AI solutions and cloud platform company  China', 'dalian', '2020-05-09 14:24:38', '2020-05-09 14:24:45');
