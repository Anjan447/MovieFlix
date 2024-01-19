import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomescreenComponent } from './pages/homescreen/homescreen.component';
import { SearchComponent } from './pages/search/search.component';
import { MovieDetailsComponent } from './pages/movie-details/movie-details.component';
import { LoginComponent } from './login/login.component';
import { GenreComponent } from './pages/genre/genre.component';
import { RegisterComponent } from './register/register.component';

const routes: Routes = [
  {path: '', component: HomescreenComponent},
  {path: 'search', component: SearchComponent},
  {path: 'login', component: LoginComponent},
  {path: 'genre', component: GenreComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'movie/:id', component: MovieDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{
    scrollPositionRestoration: 'enabled'
  }) ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
