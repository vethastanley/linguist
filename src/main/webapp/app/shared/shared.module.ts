import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import { LinguistSharedLibsModule, LinguistSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

import { LoadingWidget } from './loading/loading.widget';

@NgModule({
    imports: [LinguistSharedLibsModule, LinguistSharedCommonModule],
    declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective, LoadingWidget],
    providers: [
        {
            provide: NgbDateAdapter,
            useClass: NgbDateMomentAdapter
        }
    ],
    entryComponents: [JhiLoginModalComponent],
    exports: [LinguistSharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective, LoadingWidget],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LinguistSharedModule {}
