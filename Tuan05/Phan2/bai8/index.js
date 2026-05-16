const express = require('express');
const mysql = require('mysql2/promise');

const app = express();
const port = 3000;

// Config connect MySQL
const dbConfig = {
  host: process.env.DB_HOST || 'db', // 'db' là tên service trong docker-compose.yml
  user: process.env.DB_USER || 'root',
  password: process.env.DB_PASSWORD || 'rootpassword',
  database: process.env.DB_NAME || 'testdb'
};

app.get('/', async (req, res) => {
  try {
    const connection = await mysql.createConnection(dbConfig);
    res.send('<h1>Kết nối Node.js với MySQL thành công trong Docker!</h1>');
    await connection.end();
  } catch (error) {
    console.error('Lỗi kết nối database:', error);
    res.status(500).send('<h1>Lỗi kết nối database:</h1> <p>' + error.message + '</p>');
  }
});

app.listen(port, () => {
  console.log(`App listening at http://localhost:${port}`);
});
