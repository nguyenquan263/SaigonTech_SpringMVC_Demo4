import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';

declare var $: any;
var self: any;
@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css'],
  providers: [CookieService]
})
export class StudentListComponent implements OnInit {
  students: any;

  constructor(private cookie: CookieService, private router: Router) { }

  ngOnInit() {
    self = this;
    self.loadData();


  }

  getGender(ismale) {
    if (ismale == true)
      return "male";
    else return "female";
  }

  goAdd() {
    this.router.navigate(['/addStudent']);
  }

  goUpdate(id) {
    this.cookie.set("updateID", id);
    this.router.navigate(['/updateStudent']);
  }

  deleteStudent(i) {
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/SpringMVCDemo4/rest/StudentREST/' + i,
      headers: {
        "Authorization": this.cookie.get("token"),
      },
      dataType: 'text',
      success: function (data) {
        alert(data);
        self.loadData();
      },
      error: function (data) {
        console.log(data);
      }

    });
  }

  loadData(){
    $.ajax({
      type: 'GET',
      url: 'http://localhost:8080/SpringMVCDemo4/rest/StudentREST',
      headers: {
        "Authorization": self.cookie.get("token")
      },
      success: function (data) {
        if (data == "token expired")
          self.router.navigate(['/login']);

        self.students = data;
      },
      error: function (data) {
        console.log(data);
      }



    });
  }

}
