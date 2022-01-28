DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    `id`  INTEGER PRIMARY KEY AUTOINCREMENT,
    `user_name` varchar(20) NOT NULL,
    `password` varchar(10) NOT NULL
);

DROP TABLE IF EXISTS `MESSAGES`;
CREATE TABLE `MESSAGES` (
    `ID`  INTEGER PRIMARY KEY AUTOINCREMENT,
    `SENDER` INTEGER NOT NULL,
    `RECIPIENT` INTEGER NOT NULL,
    `CONTENT_TYPE` VARCHAR(20) NOT NULL,
    `CONTENT_URL` VARCHAR(50),
    `CONTENT_TEXT` VARCHAR(50),
    `REG_DATE` DATETIME NOT NULL DEFAULT (datetime('now', 'localtime'))


);