import { Routes } from '@angular/router';
import { ProjectComponent } from 'app/project/project.component';

export const projectRoute: Routes = [
    {
        path: 'project-management',
        component: ProjectComponent,
        data: {
            authorities: [],
            pageTitle: 'Project Management'
        }
    }
];
