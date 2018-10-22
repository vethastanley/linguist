import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Project } from 'app/core';

@Component({
    selector: 'jhi-project-mgmt-detail',
    templateUrl: './project-management-detail.component.html'
})
export class ProjectManagementDetailComponent implements OnInit {
    project: Project;

    constructor(private route: ActivatedRoute) {}

    ngOnInit() {
        this.route.data.subscribe(({ project }) => {
            this.project = project.body ? project.body : project;
        });
    }
}
