import { Component, HostListener } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'movieflix_FE';

  navbg:any;
  @HostListener('document:scroll') scrollover(){
    console.log(document.body.scrollTop,'scrolllength#');
    
    if(document.body.scrollTop > 0 || document.documentElement.scrollTop > 0)
    {
      this.navbg = {
        'background-color':'#000000'
      }
    }else
    {
        this.navbg = {}
    }
  }

  constructor(private router: Router) {}

  isLoginPage(): boolean {
    return this.router.url.includes('login') && !this.router.url.includes('register');
  }
  
  isRegisterPage(): boolean {
    return this.router.url.includes('register');
  }

  isGenrePage(): boolean {
    return this.router.url.includes('genre');
  }

  isSearchPage(): boolean {
    return this.router.url.includes('search');
  }
}
