import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Project, ProjectService } from 'app/core';

@Component({
    selector: 'jhi-project-mgmt-delete-dialog',
    templateUrl: './project-management-delete-dialog.component.html'
})
export class ProjectManagementDeleteDialogComponent {
    project: Project;

    constructor(private projectService: ProjectService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id) {
        this.projectService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'projectListModification',
                content: 'Deleted a Project'
            });
            this.activeModal.dismiss(true);
        });
    }
}
