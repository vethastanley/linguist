import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';

import { ProjectManagementComponent } from './project-management.component';
import { ProjectManagementDetailComponent } from './project-management-detail.component';
import { ProjectManagementUpdateComponent } from './project-management-update.component';
import { Principal, Project, ProjectService } from '../core';

@Injectable({ providedIn: 'root' })
export class ProjectResolve implements CanActivate {
    constructor(private principal: Principal) {}

    canActivate() {
        return this.principal.identity().then(account => this.principal.hasAnyAuthority(['ROLE_ADMIN', 'ROLE_PM']));
    }
}

@Injectable({ providedIn: 'root' })
export class ProjectManagementResolve implements Resolve<any> {
    constructor(private service: ProjectService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id);
        }
        return new Project();
    }
}

export const projectManagementRoute: Routes = [
    {
        path: 'project-management',
        component: ProjectManagementComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            pageTitle: 'Project Management',
            defaultSort: 'id,asc'
        }
    },
    {
        path: 'project-management/:id/view',
        component: ProjectManagementDetailComponent,
        resolve: {
            project: ProjectManagementResolve
        },
        data: {
            pageTitle: 'Create Project'
        }
    },
    {
        path: 'project-management/new',
        component: ProjectManagementUpdateComponent,
        resolve: {
            project: ProjectManagementResolve
        }
    },
    {
        path: 'project-management/:id/edit',
        component: ProjectManagementUpdateComponent,
        resolve: {
            project: ProjectManagementResolve
        }
    }
];
