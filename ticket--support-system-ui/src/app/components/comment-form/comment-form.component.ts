// src/app/components/comment-form/comment-form.component.ts
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommentService } from '../../services/comment.service';
import { Comment } from '../../models/comment.model';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-comment-form',
  templateUrl: './comment-form.component.html',
  styleUrls: ['./comment-form.component.scss']
})
export class CommentFormComponent {
  @Input() ticketId!: number;
  @Output() commentAdded = new EventEmitter<void>();
  commentForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private commentService: CommentService,
    private snackBar: MatSnackBar
  ) {
    this.commentForm = this.fb.group({
      author: ['', Validators.required],
      message: ['', Validators.required]
    });
  }

  addComment(): void {
    const comment: Comment = this.commentForm.value;
    comment.ticketId = this.ticketId;
    this.commentService.createComment(this.ticketId, comment).subscribe({
      next: () => {
        this.snackBar.open('Comment added', 'Close', { duration: 3000 });
        this.commentForm.reset();
        this.commentAdded.emit();
      },
      error: () => this.snackBar.open('Error adding comment', 'Close', { duration: 3000 })
    });
  }
}