DELETE FROM `author`
INSERT INTO `author` (`id`,`email`,`name`,`password`) VALUES
(1,'joao@gmail.com','Joao Paulo','1231149'),
(2,'ana@gmail.com','Ana Paula','12355'),
(3,'maria@gmail.com','Maria Clara','123578');

DELETE FROM `media`
INSERT INTO `media`(`release_year`,`genre`,`title`,`score`) VALUES
('2010','HORROR','THE EXORCISM OF EMILY ROSE',7.9),
('2008','COMEDY','THE WHITE CHICKS',8.9),
('2020','ACTION','CAPTAIN MARVEL',5.9);

DELETE FROM `topic`
INSERT INTO `topic`(`description`,`status`,`title`,`author_id`,`media_id`) VALUES
('BEST COMEDY EVER! BLA BLA BLA BLA BLA','NOT_REPLIED','THIS IS A MUST WATCH','1','2'),
('THATS THE POWER OF THIS MOVIE.','REPLIED','IM AWAKE 5 DAYS STRAIGHT','2','1'),
('THIS ONE KINDA MEH.','CLOSED','MEH','3','3');

DELETE FROM `reply`
INSERT INTO `reply` (`description`,`author_id`,`topic_id`) VALUES
('LOL, YOURE DAMN RIGHT','3','2'),
('IM NOT WATCHING THIS','2','3'),
('I WISH YOU HAVE POSTED THIS BEFORE I WATCHED IT','1','3');