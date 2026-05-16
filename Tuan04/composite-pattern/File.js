const FileSystemNode = require('./FileSystemNode');

class File extends FileSystemNode {
    constructor(name, size) {
        super(name);
        this.size = size; // Kích thước file (KB, MB...)
    }

    display(indent = "") {
        console.log(`${indent}- 📄 File: ${this.name} (${this.size} KB)`);
    }
}

module.exports = File;
