import { Component } from '@angular/core';
import { MovieApiServiceService } from 'src/app/service/movie-api-service.service';
import { Title,Meta } from '@angular/platform-browser';

@Component({
  selector: 'app-homescreen',
  templateUrl: './homescreen.component.html',
  styleUrls: ['./homescreen.component.css']
})
export class HomescreenComponent {
  constructor(private service: MovieApiServiceService,private title:Title,private meta:Meta) {
    this.title.setTitle('Home - movieflix');
    this.meta.updateTag({name:'description',content:'watch online movies'});
    
   }

  bannerResult: any = [];
  trendingMovieResult: any = [];
  nowplayingMovieResult: any = [];
  popularMovieResult: any = [];
  topratedMovieResult: any = [];
  upcomingMovieResult: any = [];
  actionMovieResult: any = [];
  adventureMovieResult: any = [];
  animationMovieResult: any = [];
  comedyMovieResult: any = [];
  documentaryMovieResult: any = [];
  sciencefictionMovieResult: any = [];
  thrillerMovieResult: any = [];

  ngOnInit(): void {
    this.bannerData();
    this.trendingData();
    this.nowplayingData();
    this.popularData();
    this.topratedData();
    this.upcomingData();
  }


  // bannerdata
  bannerData() {
    this.service.bannerApiData().subscribe((result) => {
      console.log(result, 'bannerresult#');
      this.bannerResult = result.results;
    });
  }

  // trendingdata
  trendingData() {
    this.service.trendingMovieApiData().subscribe((result) => {
      console.log(result, 'trendingresult#');
      this.trendingMovieResult = result.results;
      // this.trendingMovieResult
    });
  }


  // now playing
  nowplayingData() {
    this.service.nowplayingMovieApiData().subscribe((result) => {
      this.nowplayingMovieResult = result.results;
    });
  }

  // popular
  popularData() {
    this.service.popularMovieApiData().subscribe((result) => {
      this.popularMovieResult = result.results;
    });
  }

  // top rated
  topratedData() {
    this.service.topratedMovieApiData().subscribe((result) => {
      this.topratedMovieResult = result.results;
    });
  }

  // upcoming
  upcomingData() {
    this.service.upcomingMovieApiData().subscribe((result) => {
      this.upcomingMovieResult = result.results;
    });
  }

}
