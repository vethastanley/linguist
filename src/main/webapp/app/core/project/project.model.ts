export class Project {
    constructor(
        public id?: any,
        public name?: string,
        public review?: boolean,
        public source?: string,
        public destination?: any[],
        public repoType?: string,
        public url?: String,
        public username?: string,
        public password?: string,
        public pattern?: string,
        public createdBy?: string,
        public createdDate?: Date,
        public lastModifiedBy?: string,
        public lastModifiedDate?: Date,
        public activated?: boolean
    ) {
        this.id = id ? id : null;
        this.name = name ? name : null;
        this.review = review ? review : false;
        this.source = source ? source : null;
        this.destination = destination ? destination : null;
        this.repoType = repoType ? repoType : null;
        this.url = url ? url : null;
        this.username = username ? username : null;
        this.password = password ? password : null;
        this.pattern = pattern ? pattern : null;
        this.createdBy = createdBy ? createdBy : null;
        this.createdDate = createdDate ? createdDate : null;
        this.lastModifiedBy = lastModifiedBy ? lastModifiedBy : null;
        this.lastModifiedDate = lastModifiedDate ? lastModifiedDate : null;
        this.activated = activated ? activated : null;
    }
}
