import { Component, Output, Input, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { MyComment } from 'src/app/models/Comment';
import { AuthenticationService } from 'src/app/services/authentication-service';

@Component({
    selector: 'app-comment',
    templateUrl: './comment.component.html',
   // styleUrls: ['./upload-files.component.css']
  })
export class CommentComponent {
    panelOpenState:any;
    bool:boolean=false;
    comment:MyComment
    comments:MyComment[]=[];
    text:string="";
    //@Input() flag2: boolean;
    @Input() id:number;
    @Input() name:string;
    bool2:boolean=false;
  //  fileInfos: Observable<any>;
   // @Output() myfileInfos:EventEmitter<string>=new EventEmitter();

    constructor(private http:HttpClient,public auth:AuthenticationService) { }
    get() {
       this.bool2=!this.bool2;
       if(this.bool2==false){
           return;
       }
      //  this.comment.userName=JSON.parse(localStorage.getItem('userName'));
        this.http.get(environment.apiUrl+'/comment/getcomments?id='+this.id).subscribe((data:MyComment[]) => {
            this.comments=data;

        })

    }

    postComment(){
        if(this.text.trim() == "")
            alert("заполните поле")
        else {
            this.comment=new MyComment();
            this.comment.comments=this.text;
            this.comment.productId=this.id;
            this.comment.userName=localStorage.getItem('username');
            this.http.post(environment.apiUrl+'/comment/comment',this.comment).subscribe((data:MyComment[]) => {
                this.comments=data;

            })
            this.text="";
            this.bool=false;
         }
    }

    flag(){
        this.bool=true;
    }


    public like1(item:MyComment): void {
        item.pluslike++;
        this.http.post(environment.apiUrl+'/comment/comment/like',item).subscribe();
    }

    public dislike1(item:MyComment): void {
      item.dislike++;

      this.http.post(environment.apiUrl+'/comment/comment/dislike',item).subscribe();
  }


}
