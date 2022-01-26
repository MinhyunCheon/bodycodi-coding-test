DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    `id`  INTEGER PRIMARY KEY AUTOINCREMENT,
    `user_name` varchar(20) NOT NULL,
    `password` varchar(10) NOT NULL
);
