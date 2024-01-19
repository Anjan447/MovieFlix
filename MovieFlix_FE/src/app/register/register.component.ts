import { Component, OnInit } from '@angular/core';
import { faEye, faEyeSlash } from '@fortawesome/free-solid-svg-icons';
import { AbstractControl, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  
  faEye = faEye;
  faEyeSlash = faEyeSlash;

  visible: boolean = true;
  changetype: boolean = true;

  viewpass() {
    this.visible = !this.visible;
    this.changetype = !this.changetype;
  }

  numericOnly(event: { key: string; }): boolean {    
    let patt = /^([0-9])$/;
    let result = patt.test(event.key);
    return result;
  }

  registerForm: FormGroup = new FormGroup({
    firstName: new FormControl('', [Validators.required, Validators.minLength(2), Validators.pattern('^[a-zA-Z].*')]),
    lastName: new FormControl('', [Validators.required, Validators.minLength(2), Validators.pattern("[a-zA-Z].*")]),
    email: new FormControl('', [Validators.required, this.customEmailValidator]),
    mobileNumber: new FormControl('', [Validators.required, Validators.maxLength(10), Validators.pattern('^[0-9]+$')]),
    userName: new FormControl('', [Validators.required]),
    birthDate: new FormControl('', [Validators.required]),
    gender: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    confirmPassword: new FormControl('', [Validators.required]),
  });


  getError(control:any): string{
    if(control.errors?.required && control.touched)
      return 'field cannot be empty';
    else if (control.errors?.emailError && control.touched)
      return 'please enter valid email';
    else return '';
  }

  customEmailValidator(control:AbstractControl){
    const pattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,20}$/;
    const value = control.value;
    if(pattern.test(value) && control.touched)
      return {emailError:true}
    else return null;
  }
  // customEmailValidator(control: AbstractControl): { [key: string]: any } | null {
  //   const pattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,20}$/;
  //   const value = control.value;
  //   if (value && !pattern.test(value)) {
  //     return { emailError: true };
  //   }
  //   return null;
  // }

  constructor(){

  }
  ngOnInit(): void {
      
  }

  registerSubmited(){
    console.log(this.registerForm);
  }

  get FirstName(): FormControl {
    return this.registerForm.get("firstname") as FormControl;
  }

  get LastName(): FormControl {
    return this.registerForm.get("lastname") as FormControl;
  }

  get Email(): FormControl {
    return this.registerForm.get("email") as FormControl;
  }

  get MobileNumber(): FormControl {
    return this.registerForm.get("mobilenumber") as FormControl;
  }

  get UserName(): FormControl {
    return this.registerForm.get("username") as FormControl;
  }

  get BirthDate(): FormControl {
    return this.registerForm.get("birthdate") as FormControl;
  }

  get Gender(): FormControl {
    return this.registerForm.get("gender") as FormControl;
  }

  get Password(): FormControl {
    return this.registerForm.get("password") as FormControl;
  }

  get ConfirmPassword(): FormControl {
    return this.registerForm.get("confirmpassword") as FormControl;
  }

}