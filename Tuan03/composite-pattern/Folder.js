const FileSystemNode = require('./FileSystemNode');

class Folder extends FileSystemNode {
    constructor(name) {
        super(name);
        this.children = [];
    }

    add(node) {
        this.children.push(node);
    }

    remove(node) {
        this.children = this.children.filter(child => child !== node);
    }

    display(indent = "") {
        console.log(`${indent}+ 📁 Folder: ${this.name}`);
        this.children.forEach(child => {
            child.display(indent + "   ");
        });
    }
}

module.exports = Folder;
