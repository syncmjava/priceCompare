-- local mysql start
-- mysqld --defaults-file=C:\lichunhui\mysql-5.6.36-winx64\my.ini --console
-- create database crawler;
-- use crawler;
Drop table `crawler`.`crawler_job`;
CREATE TABLE `crawler`.`crawler_job` (
  `crawler_job_id` INT NOT NULL AUTO_INCREMENT,
  `crawler_job_type` VARCHAR(1) NOT NULL,
  `crawler_search_url` VARCHAR(255) NOT NULL,
  `crawler_flag` VARCHAR(1) NOT NULL DEFAULT 0,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_by` VARCHAR(45) NOT NULL DEFAULT '-1',
  `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` VARCHAR(45) NOT NULL DEFAULT '-1',
  PRIMARY KEY (`crawler_job_id`));

--http://www.yamada-denkiweb.com/search/THB11BCSTHB11BCS/?path=&searchbox=1
--http://www.yodobashi.com/?word=
