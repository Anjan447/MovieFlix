import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent implements OnInit {
  @Input() maxRating = 5;
  maxRatingArr: number[] = [];

  constructor() {}
  
  ngOnInit(): void {
    this.maxRatingArr = Array(this.maxRating).fill(0);
  }
}
