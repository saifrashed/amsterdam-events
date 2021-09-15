
type AEventStatus = "DRAFT" | "PUBLISHED" | "CANCELED";

export class AEvent {
    private title: string;
    private start: Date;
    private end: Date;
    private description: string;
    private status: AEventStatus;
    private isTicketed: Boolean;
    private participationFee: number;
    private maxParticipants: number;

    constructor(title: string, start: Date, end: Date, description: string, status: AEventStatus, isTicketed: Boolean, participationFee: number, maxParticipants: number) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.status = status;
        this.isTicketed = isTicketed;
        this.participationFee = participationFee;
        this.maxParticipants = maxParticipants;
    }


}
