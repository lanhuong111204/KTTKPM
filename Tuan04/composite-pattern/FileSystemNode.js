class FileSystemNode {
    constructor(name) {
        this.name = name;
    }

    display(indent = "") {
        throw new Error("Method 'display()' must be implemented.");
    }
}

module.exports = FileSystemNode;
