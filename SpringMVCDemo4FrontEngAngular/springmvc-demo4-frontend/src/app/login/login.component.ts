import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
declare var $: any;
var self: any;
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [CookieService]
})
export class LoginComponent implements OnInit {

  constructor(private cookie:CookieService, private router: Router) { }

  ngOnInit() {
    self = this;
    $("#loginBT").click(function () {
      var loginContent = {
          "username": $("#usernameTB").val(),
          "password": $("#passwordTB").val()
      }

      $.ajax({
          type: 'POST',
          url: 'http://localhost:8080/SpringMVCDemo4/rest/manage/login',
          dataType: 'text',
          data: JSON.stringify(loginContent),
          success: function (data) {
              if (data != "Log-in fail") {
                  self.cookie.set("token", data);
                  console.log(data);
                  self.router.navigate(['/student']);
              } else {
                  alert(data);
              }

          },
          error: function (data) {
              console.log(data);
          }

      });
  });
  }

}
