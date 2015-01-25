CREATE TABLE user(
    username VARCHAR(12) NOT NULL,
    password VARCHAR(255) NOT NULL,
    salt BINARY(32) NOT NULL,
    primary key(username)
);

CREATE TABLE game_history(
    id BIGINT  AUTO_INCREMENT,
    result  VARCHAR(50) NOT NULL,
    winUsername VARCHAR(12),
    GAMEDATE DATE,
    PRIMARY KEY (id)
);

CREATE TABLE game_history_user(
    user_username BIGINT NOT NULL AUTO_INCREMENT,
    game_history_id BIGINT NOT NULL AUTO_INCREMENT,
    FOREIGN KEY (user_username) REFERENCES user (username),
    FOREIGN KEY (game_history_id) REFERENCES game_history (id)
);

CREATE TABLE hand(
    id BIGINT  AUTO_INCREMENT,
    USER_USERNAME VARCHAR(12),
    FOREIGN KEY (USER_USERNAME) REFERENCES USER (USERNAME),
    PRIMARY KEY (id)
);

CREATE TABLE card(
    id BIGINT NOT NULL AUTO_INCREMENT,
    rank  VARCHAR(50) NOT NULL,
    suit  VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE hand_card(
    hand_id BIGINT NOT NULL AUTO_INCREMENT,
    card_id BIGINT NOT NULL AUTO_INCREMENT,
    FOREIGN KEY (hand_id) REFERENCES hand (id),
    FOREIGN KEY (card_id) REFERENCES card (id)
);

CREATE TABLE hand_game_history(
    hand_id BIGINT NOT NULL AUTO_INCREMENT,
    game_history_id BIGINT NOT NULL AUTO_INCREMENT,
    FOREIGN KEY (hand_id) REFERENCES hand (id),
    FOREIGN KEY (game_history_id) REFERENCES game_history (id)
);