import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { Project } from 'app/core';

@Injectable({ providedIn: 'root' })
export class ProjectService {
    private resourceUrl = SERVER_API_URL + 'api/projects';

    constructor(private http: HttpClient) {}

    create(project: Project): Observable<HttpResponse<Project>> {
        return this.http.post<Project>(this.resourceUrl, project, { observe: 'response' });
    }

    update(project: Project): Observable<HttpResponse<Project>> {
        return this.http.put<Project>(this.resourceUrl, project, { observe: 'response' });
    }

    find(id: string): Observable<HttpResponse<Project>> {
        return this.http.get<Project>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<HttpResponse<Project[]>> {
        const options = createRequestOption(req);
        return this.http.get<Project[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(name: string): Observable<HttpResponse<any>> {
        return this.http.delete(`${this.resourceUrl}/${name}`, { observe: 'response' });
    }

    translate(id: string): Observable<HttpResponse<any>> {
        return this.http.post<Project>(`${this.resourceUrl}/${id}/translate`, null, { observe: 'response' });
    }
}
