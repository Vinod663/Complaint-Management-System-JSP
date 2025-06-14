CREATE DATABASE IF NOT EXISTS cms_db;
USE cms_db;

CREATE TABLE IF NOT EXISTS users (
                                     id INT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(50) NOT NULL UNIQUE,
                                     password VARCHAR(255) NOT NULL,
                                     role ENUM('employee', 'admin') NOT NULL
);

CREATE TABLE IF NOT EXISTS complaints (
                                          id INT AUTO_INCREMENT PRIMARY KEY,
                                          user_id INT NOT NULL,
                                          title VARCHAR(255) NOT NULL,
                                          description TEXT NOT NULL,
                                          status ENUM('Pending', 'In Progress', 'Resolved') DEFAULT 'Pending',
                                          remark TEXT,
                                          date_created DATETIME DEFAULT CURRENT_TIMESTAMP,

                                          CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
