import { Component, OnInit } from '@angular/core';
import { Title, Meta } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { MovieApiServiceService } from 'src/app/service/movie-api-service.service';
import { MatDialog } from '@angular/material/dialog';
import { RatingComponent } from 'src/app/rating/rating.component';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit {

  constructor(private service: MovieApiServiceService, private router: ActivatedRoute, private title: Title, private meta: Meta, private dialogRef: MatDialog) { }
  
  
  getMovieDetailResult:any;
  getMovieVideoResult:any;
  getMovieCastResult:any;
  directorDetails: any;
  directorName: string = '';
  
  ngOnInit(): void {
    let getParamId = this.router.snapshot.paramMap.get('id');
    console.log(getParamId,'getparamid#');
  
    this.getMovie(getParamId);
    this.getVideo(getParamId);
    this.getMovieCast(getParamId);
    if (getParamId) {
      this.getDirectorData(getParamId);
    }
  }


  getMovie(id:any){
    this.service.getMovieDetails(id).subscribe(async(result)=>{
        console.log(result,'getmoviedetails#');
        this.getMovieDetailResult = await result;

        // updatetags
        this.title.setTitle(`${this.getMovieDetailResult.original_title} | ${this.getMovieDetailResult.tagline}`);
        this.meta.updateTag({name:'title',content:this.getMovieDetailResult.original_title});
        this.meta.updateTag({name:'description',content:this.getMovieDetailResult.overview});
     
        // facebook
        this.meta.updateTag({property:'og:type',content:"website"});
        this.meta.updateTag({property:'og:url',content:``});
        this.meta.updateTag({property:'og:title',content:this.getMovieDetailResult.original_title});
        this.meta.updateTag({property:'og:description',content:this.getMovieDetailResult.overview});
        this.meta.updateTag({property:'og:image',content:`https://image.tmdb.org/t/p/original/${this.getMovieDetailResult.backdrop_path}`});

    });
  }

  getVideo(id:any)
  {
    this.service.getMovieVideo(id).subscribe((result)=>{
        console.log(result,'getMovieVideo#');
        result.results.forEach((element:any) => {
            if(element.type=="Trailer")
            {
              this.getMovieVideoResult = element.key;
            }
        });

    });
  }

  getMovieCast(id:any)
  {
    this.service.getMovieCast(id).subscribe((result)=>{
      console.log(result,'movieCast#');
      this.getMovieCastResult = result.cast;
    });
  }

  getDirectorData(movieId: string) {
    this.service.getDirectorData(movieId).subscribe((data: any) => {
      console.log(this.directorDetails, 'Director Details');
      const directorData = data.filter((item: any) => item.job === 'Director');
      this.directorName = directorData.length > 0 ? directorData[0].name : null;
    });
  }

  reactColor(iconId: string) {
    const Icon = document.getElementById(iconId);
  
    if (Icon) {
      if (Icon.classList.contains('fa-regular')) {
        Icon.classList.remove('fa-regular');
        Icon.classList.add('fa-solid');
        Icon.style.color = '#e01b24';
        Icon.classList.add('fa-beat');
      } else {
        Icon.classList.remove('fa-solid');
        Icon.classList.add('fa-regular');
        Icon.style.color = '#000';
        Icon.classList.remove('fa-beat');
      }
    }
  }


  openDialog(){
    this.dialogRef.open(RatingComponent)
  }

}
