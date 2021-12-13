export class User {

    constructor(id: number, name: string, eMail: Date, hashedPassWord: Date, admin: boolean) {
        this._id = id;
        this._name = name;
        this._eMail = eMail;
        this._hashedPassWord = hashedPassWord;
        this._admin = admin;
    }

    private _id: number;

    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    private _name: string;

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    private _eMail: Date;

    get eMail(): Date {
        return this._eMail;
    }

    set eMail(value: Date) {
        this._eMail = value;
    }

    private _hashedPassWord: Date;

    get hashedPassWord(): Date {
        return this._hashedPassWord;
    }

    set hashedPassWord(value: Date) {
        this._hashedPassWord = value;
    }

    private _admin: boolean;

    get admin(): boolean {
        return this._admin;
    }

    set admin(value: boolean) {
        this._admin = value;
    }
}
