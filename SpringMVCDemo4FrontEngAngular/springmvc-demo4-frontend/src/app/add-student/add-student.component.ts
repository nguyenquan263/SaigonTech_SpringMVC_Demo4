import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
declare var $: any;
var self: any;
@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.css'],
  providers: [CookieService]
})
export class AddStudentComponent implements OnInit {

  constructor(private cookie: CookieService, private router: Router) { }

  ngOnInit() {
    self = this;
    $.ajax({
      type: 'GET',
      url: 'http://localhost:8080/SpringMVCDemo4/rest/SpecializationREST',
      headers: {
        "Authorization": this.cookie.get("token")
      },
      success: function (data) {
        if (data == "token expired")
          self.router.navigate(['/login']);

        console.log(data);
        for (var i = 0; i < data.length; i++) {
          $("#addSpecialization").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
        }
      },
      error: function (data) {
        console.log(data);
      }

    });
  }

  addStudent() {

    let addData: FormData = new FormData();
    addData.append("firstName", $("#addFirstName").val());
    addData.append("lastName", $("#addLastName").val());
    addData.append("email", $("#addEmail").val());
    addData.append("image", $('#addImage')[0].files[0]);
    addData.append("isMale", $('input[name=addGender]:checked').val());
    addData.append("specialization", $("#addSpecialization option:selected").val());

    $.ajax({

      url: "http://localhost:8080/SpringMVCDemo4/rest/StudentREST",
      headers: {
        "Authorization": self.cookie.get("token")
      },
      data: addData,
      type: "POST",
      processData: false,
      contentType: false,

      success: function (data) {
        console.log(data);
        self.router.navigate(['/student']);
      },

      error: function (data) {
        console.log(data);
      }

    });

  }
}
