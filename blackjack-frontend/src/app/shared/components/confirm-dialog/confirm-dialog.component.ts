import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({templateUrl: 'confirm-dialog.component.html'})
export class ConfirmDialogComponent {
	message: string = "Deseja realmente excluir?"
	confirmButtonText = "Sim"
	cancelButtonText = "Cancelar"
	constructor(@Inject(MAT_DIALOG_DATA) private data: any,	private dialogRef: MatDialogRef<ConfirmDialogComponent>) {
		if(data){
			this.message = data.message || this.message;
			if (data.buttonText) {
				this.confirmButtonText = data.buttonText.ok || this.confirmButtonText;
				this.cancelButtonText = data.buttonText.cancel || this.cancelButtonText;
			}
		}
	}

	onConfirmClick(): void {
		this.dialogRef.close(true);
	}

  onCancelClick(): void {
		this.dialogRef.close(false);
	}
}
