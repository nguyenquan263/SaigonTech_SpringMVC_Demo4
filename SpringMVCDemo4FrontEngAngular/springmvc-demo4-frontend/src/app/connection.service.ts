import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConnectionService {

  constructor() { }

  getConnection(): string {
    return "http://localhost:8080/SpringMVCDemo4/";
  }
}
