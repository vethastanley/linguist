import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProjectComponent } from 'app/project/project.component';
import { projectRoute } from 'app/project/project.route';
import { RouterModule } from '@angular/router';

@NgModule({
    imports: [CommonModule, RouterModule.forChild(projectRoute)],
    declarations: [ProjectComponent]
})
export class LinguistProjectModule {}
