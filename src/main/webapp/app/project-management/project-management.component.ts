import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ActivatedRoute, Router } from '@angular/router';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE } from 'app/shared';
import { Principal, Project, ProjectService } from 'app/core';
import { UserMgmtDeleteDialogComponent } from 'app/user-management/user-management-delete-dialog.component';

@Component({
    selector: 'jhi-project-mgmt',
    templateUrl: './project-management.component.html'
})
export class ProjectManagementComponent implements OnInit, OnDestroy {
    currentAccount: any;
    projects: Project[];
    error: any;
    success: any;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    constructor(
        private projectService: ProjectService,
        private alertService: JhiAlertService,
        private principal: Principal,
        private parseLinks: JhiParseLinks,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager,
        private modalService: NgbModal
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data['pagingParams'].page;
            this.previousPage = data['pagingParams'].page;
            this.reverse = data['pagingParams'].ascending;
            this.predicate = data['pagingParams'].predicate;
        });
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.currentAccount = account;
            this.loadAll();
        });
    }

    ngOnDestroy() {
        this.routeData.unsubscribe();
    }

    registerChangeInUsers() {
        this.eventManager.subscribe('projectListModification', response => this.loadAll());
    }

    setActive(project, isActivated) {
        project.activated = isActivated;

        this.projectService.update(project).subscribe(response => {
            if (response.status === 200) {
                this.error = null;
                this.success = 'OK';
                this.loadAll();
            } else {
                this.success = null;
                this.error = 'ERROR';
            }
        });
    }

    loadAll() {
        this.projectService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<Project[]>) => this.onSuccess(res.body, res.headers),
                (res: HttpResponse<any>) => this.onError(res.body)
            );
    }

    trackIdentity(index, item: Project) {
        return item.id;
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/project-management'], {
            queryParams: {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    deleteProject(project: Project) {
        const modalRef = this.modalService.open(UserMgmtDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.project = project;
        modalRef.result.then(
            result => {
                // Left blank intentionally, nothing to do here
            },
            reason => {
                // Left blank intentionally, nothing to do here
            }
        );
    }

    triggerTranslation(project: Project) {
        this.projectService.translate(project.id).subscribe(response => {
            if (response.status === 200) {
                this.alertService.info('Translation successful for project', project.name);
            } else {
                this.alertService.error('Translation failed for the project', project.name);
            }
        });
    }

    private onSuccess(data, headers) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = headers.get('X-Total-Count');
        this.queryCount = this.totalItems;
        this.projects = data;
    }

    private onError(error) {
        this.alertService.error(error.error, error.message, null);
    }
}
