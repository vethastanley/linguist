import { Injectable } from '@angular/core';
import { LoadingWidget } from './shared/loading/loading.widget';

@Injectable()
export class AppService {
    public loading: LoadingWidget;
}
