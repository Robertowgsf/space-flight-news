CREATE TABLE article (
    id SERIAL PRIMARY KEY,
    featured BOOLEAN NOT NULL,
    title VARCHAR(255) NOT NULL,
    url VARCHAR(255) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    news_site VARCHAR(255) NOT NULL,
    summary VARCHAR(255) NOT NULL,
    published_at VARCHAR(255) NOT NULL
);

CREATE TABLE launch (
    id VARCHAR(255) PRIMARY KEY,
    provider VARCHAR(255) NOT NULL,
    article_id INT NOT NULL,
    CONSTRAINT fk_article FOREIGN KEY(article_id) REFERENCES article(id)
);

CREATE TABLE event (
    id VARCHAR(255) PRIMARY KEY,
    provider VARCHAR(255) NOT NULL,
    article_id INT NOT NULL,
    CONSTRAINT fk_article FOREIGN KEY(article_id) REFERENCES article(id)
);