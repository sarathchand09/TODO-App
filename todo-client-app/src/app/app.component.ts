import {Component, Inject, OnInit} from '@angular/core';
import {TodoServices} from './service/todo.services';
import {Task} from './Task';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  tasks: Task[] = [];
  defaultTask: Task = {name: '', description: '', status: 'NEW'};
  addTask: Task = {name: '', description: '', status: 'NEW'};
  addTaskClick: boolean;

  ngOnInit(): void {
    this.getTasks();
  }

  constructor(@Inject(TodoServices) private _TODOService) {
  }

  getTasks(): void {
    this._TODOService.getAllTasks().subscribe((data) => {
      this.tasks = data;
    }, (error) => console.log(error));
  }

  updateTask(index): void {
    const task: Task = this.tasks[index];
    this.tasks[index].status = task.status === 'NEW' ? 'COMPLETED' : 'NEW';
    this._TODOService.updateTaskStatus(task.name,task.status )
      .subscribe((data) => console.log(data), (error) => console.log(error));
  }

  removeTask(index): void {
    this._TODOService.removeTaskByName(this.tasks[index].name).subscribe((data) => console.log(data), (error) => console.log(error));
    this.tasks.splice(index, 1);
  }

  addNewTask(): void {
    this.addTaskClick = !this.addTaskClick;
    this._TODOService.addNewTask(this.addTask).subscribe((data) => console.log(data), (error) => console.log(error));
    this.tasks.push(this.addTask);
    this.addTask = this.defaultTask;
  }
}
