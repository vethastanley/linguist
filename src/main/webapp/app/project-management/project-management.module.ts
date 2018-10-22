import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { ProjectManagementComponent } from 'app/project-management/project-management.component';
import { ProjectManagementDeleteDialogComponent } from 'app/project-management/project-management-delete-dialog.component';
import { ProjectManagementDetailComponent } from 'app/project-management/project-management-detail.component';
import { ProjectManagementUpdateComponent } from 'app/project-management/project-management-update.component';
import { projectManagementRoute } from 'app/project-management/project-management.route';
import { LinguistSharedModule } from 'app/shared';

@NgModule({
    imports: [LinguistSharedModule, RouterModule.forChild(projectManagementRoute)],
    declarations: [
        ProjectManagementComponent,
        ProjectManagementDeleteDialogComponent,
        ProjectManagementDetailComponent,
        ProjectManagementUpdateComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LinguistProjectManagementModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
