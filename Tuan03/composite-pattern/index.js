const File = require('./File');
const Folder = require('./Folder');

// Tạo các file
const file1 = new File("document.txt", 15);
const file2 = new File("image.png", 2048);
const file3 = new File("script.js", 5);
const file4 = new File("system_config.json", 2);

// Tạo các thư mục
const rootFolder = new Folder("C:");
const docsFolder = new Folder("Documents");
const picturesFolder = new Folder("Pictures");
const systemFolder = new Folder("System");

// Xây dựng cấu trúc cây (Tree structure)
docsFolder.add(file1);
docsFolder.add(file3);

picturesFolder.add(file2);

systemFolder.add(file4);

rootFolder.add(docsFolder);
rootFolder.add(picturesFolder);
rootFolder.add(systemFolder);

// Hiển thị toàn bộ cấu trúc từ thư mục gốc
console.log("=== HỆ THỐNG TẬP TIN ===");
rootFolder.display();
