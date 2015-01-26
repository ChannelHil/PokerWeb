CREATE TABLE user(
    username VARCHAR(12) NOT NULL,
    password VARCHAR(255) NOT NULL,
    salt BINARY(32) NOT NULL,
    primary key(username)
);

CREATE TABLE game(
    id BIGINT  AUTO_INCREMENT,
    GAMEDATE DATE,
    state VARCHAR(20),
    PRIMARY KEY (id)
);

CREATE TABLE user_game(
    id BIGINT  AUTO_INCREMENT,
    user_username VARCHAR(12) NOT NULL,
    game_id BIGINT NOT NULL AUTO_INCREMENT,
    winround BOOLEAN,
    result  VARCHAR(50),
    hand_id BIGINT,
    FOREIGN KEY (user_username) REFERENCES user (username),
    FOREIGN KEY (game_id) REFERENCES game(id),
    PRIMARY KEY (id)
);

CREATE TABLE hand(
    id BIGINT  AUTO_INCREMENT,
    PRIMARY KEY (id)
);

CREATE TABLE card(
    rank  INT NOT NULL,
    suit  VARCHAR(10) NOT NULL,
    PRIMARY KEY (rank, suit)
);

CREATE TABLE hand_card(
    hand_id BIGINT NOT NULL AUTO_INCREMENT,
    card_rank INT NOT NULL,
    card_suit VARCHAR(10) NOT NULL,
    FOREIGN KEY (hand_id) REFERENCES hand (id),
    FOREIGN KEY (card_rank,card_suit) REFERENCES card (rank,suit),
    PRIMARY KEY (hand_id,card_rank,card_suit)
);