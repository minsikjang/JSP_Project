CREATE TABLE `notice` (
  `idx` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `subject` VARCHAR(200) NOT NULL,
  `context` VARCHAR(3000) NOT NULL,
  `writedate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `rewrite` DATETIME,
  `hit` INT(11) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;   


ALTER TABLE `notice` CONVERT TO CHARSET utf8;

DELETE FROM `notice`;

ALTER TABLE `notice` AUTO_INCREMENT = 1;
