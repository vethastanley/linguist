//Copyright (c) 2016 Software AG, Darmstadt, Germany and/or Software AG USA, Inc., Reston, VA, United States of America, and/or their licensors.
// This program is confidential, proprietary and a trade secret to Software AG and/or its licensors and may not be reproduced, published or disclosed to others without the express written consent of Software AG.

import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';

@Component({
    selector: 'jhi-loading',
    templateUrl: './loading.widget.html',
    styleUrls: ['./loading.widget.css']
})
export class LoadingWidget implements OnInit {
    @Input() period: number = 2000;

    @Output() hook: EventEmitter<any>;

    public hide: boolean = true;

    constructor() {
        this.hook = new EventEmitter<any>();
    }

    ngOnInit() {
        this.hook.emit(this);
        // this.showLoading();
        // setTimeout(()=>this.hideLoading(), this.period);
    }

    showLoading() {
        this.hide = false;
    }

    timeoutLoading(cBack: CallBack, timeout: number) {
        this.showLoading();
        setTimeout(() => {
            cBack.execute();
            this.hideLoading();
        }, timeout);
    }

    hideLoading() {
        this.hide = true;
    }

    isLoadingShown(): boolean {
        return !this.hide;
    }
}

export interface CallBack {
    execute();
}
