import { OnInit, Component, Input, Output, EventEmitter } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { UploadFileService } from './upload.service';


@Component({
    selector: 'app-upload-files',
    templateUrl: './upload.component.html',
   // styleUrls: ['./upload-files.component.css']
  })
export class UploadFilesComponent implements OnInit {

    selectedFiles: FileList;
    currentFile: File;
    progress = 0;
    message = '';
    @Input() flag2: boolean;
    
  fileInfos: Observable<any>;
  @Output() myfileInfos:EventEmitter<string>=new EventEmitter();

  constructor(private uploadService: UploadFileService) { }
    
    
    ngOnInit(): void {
        this.fileInfos = this.uploadService.getFiles();
    }
  
  
  
  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  
  upload() {
    this.progress = 0;
  
    this.currentFile = this.selectedFiles.item(0);
    this.uploadService.upload(this.currentFile).subscribe((data:any) => {
        this.fileInfos=data.body;
         
        this.myfileInfos.emit(data.body.info);
        // if (event.type === HttpEventType.UploadProgress) {
        //   this.progress = Math.round(100 * event.loaded / event.total);
        //   if (event instanceof HttpResponse) {
        //   this.message = event.statusText;
        //   this.fileInfos = this.uploadService.getFiles();
    }
    )
    //   err => {
    //     this.progress = 0;
    //     this.message = 'Could not upload the file!';
    //     this.currentFile = undefined;
    //   });
  
    this.selectedFiles = undefined;
  }
 
}