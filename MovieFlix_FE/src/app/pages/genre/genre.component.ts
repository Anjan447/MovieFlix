import { Component } from '@angular/core';
import { MovieApiServiceService } from 'src/app/service/movie-api-service.service';
import { Title,Meta } from '@angular/platform-browser';

@Component({
  selector: 'app-genre',
  templateUrl: './genre.component.html',
  styleUrls: ['./genre.component.css']
})
export class GenreComponent {

  constructor(private service: MovieApiServiceService) {
  }

  actionMovieResult: any = [];
  adventureMovieResult: any = [];
  animationMovieResult: any = [];
  comedyMovieResult: any = [];
  documentaryMovieResult: any = [];
  dramaMovieResult: any = [];
  fantasyMovieResult: any = [];
  historyMovieResult: any = [];
  horrorMovieResult: any = [];
  musicMovieResult: any = [];
  mysteryMovieResult: any = [];
  romanceMovieResult: any = [];
  sciencefictionMovieResult: any = [];
  thrillerMovieResult: any = [];

  ngOnInit(): void {
    this.actionMovie();
    this.adventureMovie();
    this.comedyMovie();
    this.animationMovie();
    this.documentaryMovie();
    this.dramaMovie();
    this.fantasyMovie();
    this.historyMovie();
    this.horrorMovie();
    this.musicalMovie();
    this.mysteryMovie();
    this.romanceMovie();
    this.sciencefictionMovie();
    this.thrillerMovie();
  }


  // action 
  actionMovie() {
    this.service.fetchActionMovies().subscribe((result) => {
      this.actionMovieResult = result.results;
    });
  }

  // adventure 
  adventureMovie() {
    this.service.fetchAdventureMovies().subscribe((result) => {
      this.adventureMovieResult = result.results;
    });
  }


  // animation 
  animationMovie() {
    this.service.fetchAnimationMovies().subscribe((result) => {
      this.animationMovieResult = result.results;
    });
  }


  // comedy 
  comedyMovie() {
    this.service.fetchComedyMovies().subscribe((result) => {
      this.comedyMovieResult = result.results;
    });
  }

  // documentary 
  documentaryMovie() {
    this.service.fetchDocumentaryMovies().subscribe((result) => {
      this.documentaryMovieResult = result.results;
    });
  }

  // drama 
  dramaMovie() {
    this.service.fetchDramaMovies().subscribe((result) => {
      this.dramaMovieResult = result.results;
    });
  }

  // fantasy 
  fantasyMovie() {
    this.service.fetchFantasyMovies().subscribe((result) => {
      this.fantasyMovieResult = result.results;
    });
  }

  // historical 
  historyMovie() {
    this.service.fetchHistoryMovies().subscribe((result) => {
      this.historyMovieResult = result.results;
    });
  }

  // horror 
  horrorMovie() {
    this.service.fetchHorrorMovies().subscribe((result) => {
      this.horrorMovieResult = result.results;
    });
  }

  // muscial 
  musicalMovie() {
    this.service.fetchMusicalMovies().subscribe((result) => {
      this.musicMovieResult = result.results;
    });
  }

  // mystery 
  mysteryMovie() {
    this.service.fetchMysteryMovies().subscribe((result) => {
      this.mysteryMovieResult = result.results;
    });
  }

  // romance 
  romanceMovie() {
    this.service.fetchRomanceMovies().subscribe((result) => {
      this.romanceMovieResult = result.results;
    });
  }

  // science-fiction 
  sciencefictionMovie() {
    this.service.fetchScienceFictionMovies().subscribe((result) => {
      this.sciencefictionMovieResult = result.results;
    });
  }

  // thriller
  thrillerMovie() {
    this.service.fetchThrillerMovies().subscribe((result) => {
      this.thrillerMovieResult = result.results;
    });
  }
}
