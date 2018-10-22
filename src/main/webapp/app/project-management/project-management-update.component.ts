import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { JhiLanguageHelper, Project, ProjectService } from 'app/core';

@Component({
    selector: 'jhi-project-mgmt-update',
    templateUrl: './project-management-update.component.html'
})
export class ProjectManagementUpdateComponent implements OnInit {
    project: Project;
    languages: any[];
    isSaving: boolean;

    constructor(
        private languageHelper: JhiLanguageHelper,
        private service: ProjectService,
        private route: ActivatedRoute,
        private router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({ project }) => {
            this.project = project.body ? project.body : project;
        });
        this.languageHelper.getAll().then(languages => {
            this.languages = languages;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.project.destination = ['en', 'de'];
        if (this.project.id !== null) {
            this.service.update(this.project).subscribe(response => this.onSaveSuccess(response), () => this.onSaveError());
        } else {
            this.service.create(this.project).subscribe(response => this.onSaveSuccess(response), () => this.onSaveError());
        }
    }

    private onSaveSuccess(result) {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
