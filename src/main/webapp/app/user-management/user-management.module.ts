import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhiLanguageService } from 'ng-jhipster';
import { JhiLanguageHelper } from 'app/core';
import { UserMgmtComponent } from 'app/user-management/user-management.component';
import { UserMgmtDeleteDialogComponent } from 'app/user-management/user-management-delete-dialog.component';
import { UserMgmtDetailComponent } from 'app/user-management/user-management-detail.component';
import { UserMgmtUpdateComponent } from 'app/user-management/user-management-update.component';
import { userMgmtRoute } from 'app/user-management/user-management.route';
import { LinguistSharedModule } from 'app/shared';

@NgModule({
    imports: [LinguistSharedModule, RouterModule.forChild(userMgmtRoute)],
    declarations: [UserMgmtComponent, UserMgmtDeleteDialogComponent, UserMgmtDetailComponent, UserMgmtUpdateComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LinguistUserManagementModule {
    constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
        this.languageHelper.language.subscribe((languageKey: string) => {
            if (languageKey !== undefined) {
                this.languageService.changeLanguage(languageKey);
            }
        });
    }
}
