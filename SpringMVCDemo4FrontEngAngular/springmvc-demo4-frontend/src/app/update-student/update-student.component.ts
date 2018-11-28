import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
declare var $: any;
var self: any;
var targetStudent = null;
@Component({
  selector: 'app-update-student',
  templateUrl: './update-student.component.html',
  styleUrls: ['./update-student.component.css'],
  providers: [CookieService]
})
export class UpdateStudentComponent implements OnInit {

  constructor(private cookie: CookieService, private router: Router) { }

  ngOnInit() {
    self = this;

    $.ajax({
      type: 'GET',
      url: 'http://localhost:8080/SpringMVCDemo4/rest/SpecializationREST',
      headers: {
        "Authorization": self.cookie.get("token")
      },
      success: function (data) {
        if (data == "token expired")
          location.href = "Login.html"
        console.log(data);
        for (var i = 0; i < data.length; i++) {
          $("#updateSpecialization").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
        }
      },
      error: function (data) {
        console.log(data);
      }

    });
    $.ajax({
      type: 'GET',
      url: 'http://localhost:8080/SpringMVCDemo4/rest/StudentREST/' + self.cookie.get("updateID"),
      headers: {
        "Authorization": self.cookie.get("token")
      },
      success: function (data) {
        console.log(data);

        $("#updateFirstName").val(data.firstName);
        $("#updateLastName").val(data.lastName);
        $("#updateEmail").val(data.email);
        $("input[name=updateGender][value=" + data.male + "]").attr('checked', 'checked');
        $("#updateSpecialization option[value=" + data.specialization.id + "]").attr("selected", "selected");
        targetStudent = data;
      },
      error: function (data) {
        console.log(data);
      }

    });
  }

  updateStudent() {
    var updateData = new FormData();
    updateData.append("firstName", $("#updateFirstName").val());
    updateData.append("lastName", $("#updateLastName").val());
    updateData.append("email", $("#updateEmail").val());

    var temp = $('#updateImage')[0].files[0];
    if (temp)
        updateData.append("image", $('#updateImage')[0].files[0]);
    else
        updateData.append("image", null);

    updateData.append("isMale", $('input[name=updateGender]:checked').val());
    updateData.append("specialization", $("#updateSpecialization option:selected").val());




    $.ajax({

        url: "http://localhost:8080/SpringMVCDemo4/rest/StudentREST/"+targetStudent.id,
        headers: {
            "Authorization": self.cookie.get("token")
        },
        data: updateData,
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
