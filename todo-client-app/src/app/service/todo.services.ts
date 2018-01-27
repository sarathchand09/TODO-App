import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Task} from '../Task';

@Injectable()
export class TodoServices {

  constructor(private http: HttpClient) {
  }

  getAllTasks(): Observable<any> {
    return this.http.get('http://localhost:8080/tasks/getAllTasks');
  }

  addNewTask(task: Task): Observable<any> {
    return this.http.post('http://localhost:8080/tasks/addTask', task);
  }

  updateTaskStatus(name: string, status: string): Observable<any> {
    return this.http.post('http://localhost:8080/tasks/updateTaskStatus', {name: name, status: status});
  }

  removeTaskByName(name: string): Observable<any> {
    return this.http.get('http://localhost:8080/tasks/deleteTask/' + name);
  }
}
