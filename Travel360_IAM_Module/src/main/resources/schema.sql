CREATE TABLE user (
                      user_id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(100),
                      email VARCHAR(100) UNIQUE,
                      password_hash VARCHAR(255),
                      role VARCHAR(50),
                      phone VARCHAR(20),
                      status VARCHAR(20)
);

CREATE TABLE auditlog (
                          audit_id INT AUTO_INCREMENT PRIMARY KEY,
                          user_id INT,
                          action VARCHAR(100),
                          entity_type VARCHAR(50),
                          entity_id INT,
                          timestamp DATETIME,
                          FOREIGN KEY (user_id) REFERENCES user(user_id)
);
