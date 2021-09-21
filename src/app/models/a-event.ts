// type AEventStatus = "DRAFT" | "PUBLISHED" | "CANCELED";

enum AEventStatus {
    Draft = "DRAFT",
    Published = "PUBLISHED",
    Canceled = "CANCELED"
}

export class AEvent {
    static idCounter: number = 20001;

    private _id: number;
    private _title: string;
    private _start: Date;
    private _end: Date;
    private _description: string;
    private _status: AEventStatus;
    private _isTicketed: Boolean;
    private _participationFee: number;
    private _maxParticipants: number;

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


    public static createRandomAEvent(): AEvent {

        let eventNames = ['Amsterdam\'s Neighbourhood Secrets', 'How to spend Valentine\'s Day in Amsterdam', 'Winter day trips from Amsterdam', 'Canal Cruise Ticket'];
        let statusEnum = [AEventStatus.Draft, AEventStatus.Published, AEventStatus.Canceled];

        let id = this.idCounter++;
        let title = eventNames[Math.floor(Math.random() * eventNames.length)];
        let start = new Date();
        let end = new Date();
        let description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse eget nisi sit amet enim fermentum tempor. Maecenas finibus, tortor non aliquam venenatis, risus enim imperdiet nisi, eget egestas tellus ipsum ac orci. Nulla semper urna sit amet tincidunt tristique. ";
        let status = statusEnum[Math.floor(Math.random() * statusEnum.length)];
        let isTicketed = Math.random() < 0.5;
        let participationFee = isTicketed ? Math.floor(Math.random() * 15) : 0;
        let maxParticipants = isTicketed ? Math.floor(Math.random() * 200) : 0;

        return new AEvent(id, title, start, end, description, status, isTicketed, participationFee, maxParticipants);
    }


    get title(): string {
        return this._title;
    }

    set title(value: string) {
        this._title = value;
    }

    get start(): Date {
        return this._start;
    }

    set start(value: Date) {
        this._start = value;
    }

    get end(): Date {
        return this._end;
    }

    set end(value: Date) {
        this._end = value;
    }

    get description(): string {
        return this._description;
    }

    set description(value: string) {
        this._description = value;
    }

    get status(): AEventStatus {
        return this._status;
    }

    set status(value: AEventStatus) {
        this._status = value;
    }

    get isTicketed(): Boolean {
        return this._isTicketed;
    }

    set isTicketed(value: Boolean) {
        this._isTicketed = value;
    }

    get participationFee(): number {
        return this._participationFee;
    }

    set participationFee(value: number) {
        this._participationFee = value;
    }

    get maxParticipants(): number {
        return this._maxParticipants;
    }

    set maxParticipants(value: number) {
        this._maxParticipants = value;
    }


    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }
}
