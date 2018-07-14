export interface IAlarm {
    id?: number;
    appl?: string;
    bfunc?: string;
    chgj?: string;
    ci?: string;
    desc?: string;
    email?: string;
    fmail?: string;
    fsms?: string;
    ftlg?: string;
    hpsm?: string;
    infrastructure?: string;
    messtext?: string;
    sitname?: string;
    sittype?: string;
    sms?: string;
    tlg?: string;
    url?: string;
    hpsm_override?: string;
}

export class Alarm implements IAlarm {
    constructor(
        public id?: number,
        public appl?: string,
        public bfunc?: string,
        public chgj?: string,
        public ci?: string,
        public desc?: string,
        public email?: string,
        public fmail?: string,
        public fsms?: string,
        public ftlg?: string,
        public hpsm?: string,
        public infrastructure?: string,
        public messtext?: string,
        public sitname?: string,
        public sittype?: string,
        public sms?: string,
        public tlg?: string,
        public url?: string,
        public hpsm_override?: string
    ) {}
}
