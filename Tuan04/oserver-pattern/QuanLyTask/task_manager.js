class ISubject {
  attach(observer) {
    throw new Error("method attach() must be implemented");
  }
  detach(observer) {
    throw new Error("method detach() must be implemented");
  }
  notifyObservers() {
    throw new Error("method notifyObservers() must be implemented");
  }
}

class IObserver {
  update(subject) {
    throw new Error("method update() must be implemented");
  }
}

class Task extends ISubject {
  #taskId;
  #status;
  #teamMembers;
  constructor(taskId) {
    super();
    this.#taskId = taskId;
    this.#status = "To Do";
    this.#teamMembers = [];
  }

  getStatus() {
    return this.#status;
  }

  getTaskId() {
    return this.#taskId;
  }

  setStatus(newStatus) {
    console.log(
      `Task ${this.#taskId} đổi trạng thái từ ${this.#status} sang ${newStatus}`,
    );
    this.#status = newStatus;
    this.notifyObservers();
  }

  attach(observer) {
    this.#teamMembers.push(observer);
  }

  detach(observer) {
    this.#teamMembers = this.#teamMembers.filter((obs) => obs !== observer);
  }

  notifyObservers() {
    for (const member of this.#teamMembers) {
      member.update(this);
    }
  }
}

class TeamMember extends IObserver {
  #role;
  #name;

  constructor(name, role) {
    super();
    this.#name = name;
    this.#role = role;
  }

  getInfo() {
    return `${this.#role}-${this.#name}`;
  }

  update(subject) {
    if (subject instanceof Task) {
      console.log(
        `[${this.getInfo()}] nhận thông báo: Task  ${subject.getTaskId()} đang có trạng thái ${subject.getStatus()}`,
      );
    }
  }
}

console.log("---Khởi tạo Task---");
const task123 = new Task("TASK-123");

console.log("---Khởi tạo Observers---");
const dev = new TeamMember("Hung", "Dev");
const qa = new TeamMember("Binh", "QA");
const pm = new TeamMember("Anh", "PM");

console.log("--- Attach Observers vào Subject ---");
task123.attach(dev);
task123.attach(qa);
task123.attach(pm);

console.log("---Test thay đổi trạng thái---");
task123.setStatus("In Progress");

console.log(`PM ngừng theo dõi Task: ${task123.getTaskId()}`);
task123.detach(pm);

console.log("---Trạng thái cập nhật tiếp---");
task123.setStatus("Done");
