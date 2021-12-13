// type AEventStatus = "DRAFT" | "PUBLISHED" | "CANCELED";

export enum AEventStatus {
    Draft = "DRAFT",
    Published = "PUBLISHED",
    Canceled = "CANCELED"
}

export class AEvent {
    private static _idCounter: number = 20001;

    constructor(id: number, title: string, start: Date, end: Date, description: string, status: AEventStatus, isTicketed: Boolean, participationFee: number, maxParticipants: number) {
        this._id = id;
        this._title = title;
        this._start = start;
        this._end = end;
        this._description = description;
        this._status = status;
        this._isTicketed = isTicketed;
        this._participationFee = participationFee;
        this._maxParticipants = maxParticipants;
    }

    private _id: number;

    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    private _title: string;

    get title(): string {
        return this._title;
    }

    set title(value: string) {
        this._title = value;
    }

    private _start: Date;

    get start(): Date {
        return this._start;
    }

    set start(value: Date) {
        this._start = value;
    }

    private _end: Date;

    get end(): Date {
        return this._end;
    }

    set end(value: Date) {
        this._end = value;
    }

    private _description: string;

    get description(): string {
        return this._description;
    }

    set description(value: string) {
        this._description = value;
    }

    private _status: AEventStatus;

    get status(): AEventStatus {
        return this._status;
    }

    set status(value: AEventStatus) {
        this._status = value;
    }

    private _isTicketed: Boolean;

    get isTicketed(): Boolean {
        return this._isTicketed;
    }

    set isTicketed(value: Boolean) {
        this._isTicketed = value;
    }

    private _participationFee: number;

    get participationFee(): number {
        return this._participationFee;
    }

    set participationFee(value: number) {
        this._participationFee = value;
    }

    private _maxParticipants: number;

    get maxParticipants(): number {
        return this._maxParticipants;
    }

    set maxParticipants(value: number) {
        this._maxParticipants = value;
    }

    public static createRandomAEvent(): AEvent {

        let eventNames = ['The fantastic event', 'The less fantastic event', 'the flat earth convention'];
        let statusEnum = [AEventStatus.Draft, AEventStatus.Published, AEventStatus.Canceled];

        let id = this._idCounter++;
        let title = eventNames[Math.floor(Math.random() * eventNames.length)] + "-" + id;
        let start = new Date();
        let end = new Date();
        let description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget nisi sit amet enim fermentum tempor. Maecenas finibus, tortor non aliquam venenatis, risus enim imperdiet nisi, eget egestas tellus ipsum ac orci. Nulla semper urna sit amet tincidunt tristique. ";
        let status = statusEnum[Math.floor(Math.random() * statusEnum.length)];
        let isTicketed = Math.random() < 0.5;
        let participationFee = isTicketed ? Math.floor(Math.random() * 15) : 0;
        let maxParticipants = isTicketed ? Math.floor(Math.random() * 200) : 0;

        return new AEvent(id, title, start, end, description, status, isTicketed, participationFee, maxParticipants);
    }

    static trueCopy(aevent: AEvent): AEvent {
        return Object.assign(new AEvent(0, "", new Date(), new Date(), "", AEventStatus.Draft, false, 0, 0), aevent)
    }

    public trueCopy(): AEvent {
        return Object.assign(new AEvent(0, "", new Date(), new Date(), "", AEventStatus.Draft, false, 0, 0), this)
    }

    public toJSON() {
        return {
            description: this.description,
            end: this.end,
            id: this.id,
            isTicketed: this.isTicketed,
            maxParticipants: this.maxParticipants,
            participationFee: this.participationFee,
            start: this.start,
            status: this.status,
            title: this.title
        }
    }
}
