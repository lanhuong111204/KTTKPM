
const db1 = require('./database.js');
const db2 = require('./database.js');

db1.connect();

console.log(db1 === db2); 