INSERT INTO `AUTHOR` (`id`,`email`,`name`,`password`) VALUES
(1,'joao@gmail.com','Joao Paulo','1231149'),
(2,'ana@gmail.com','Ana Paula','12355'),
(3,'maria@gmail.com','Maria Clara','123578');

INSERT INTO `MEDIA`(`id`,`date`,`genre`,`name`,`score`) VALUES
(1,'2010-05-10','HORROR','THE EXORCISM OF EMILY ROSE',76.9),
(2,'2008-08-15','COMEDY','THE WHITE CHICKS',89.9),
(3,'2020-12-03','ACTION','CAPTAIN MARVEL',56.9);

INSERT INTO `TOPIC`(`id`,`description`,`status`, `title`,`author_id`,`media_id`) VALUES
(1,'DOES EVERYBODY AGREE WITH ME ON THE TITLE?','OPEN','BEST COMEDY EVER MADE!!!',1,2),
(2,'HOW THE PRODUCERS RELEASED THIS?','CLOSED','WORST ACTION MOVIE EVER MADE!!!',2,3);

INSERT INTO `REPLY`(`id`,`description`,`author_id`,`topic_id`) VALUES
(1,'I TOTTALY AGREE WITH YOU, ITS INDEED THE VERY BEST ONE.',3,1),
(2,'I DESAGREE WITH THE NOTE YOU GIVE FOR THE MOVIE, IT SHOWULD BE WORSE.',1,2);