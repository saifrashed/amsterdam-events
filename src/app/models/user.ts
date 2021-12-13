export class User {

    private _id: number;
    private _name: string;
    private _eMail: Date;
    private _hashedPassWord: Date;
    private _admin: boolean;

    constructor(id: number, name: string, eMail: Date, hashedPassWord: Date, admin: boolean) {
        this._id = id;
        this._name = name;
        this._eMail = eMail;
        this._hashedPassWord = hashedPassWord;
        this._admin = admin;
    }


    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get eMail(): Date {
        return this._eMail;
    }

    set eMail(value: Date) {
        this._eMail = value;
    }

    get hashedPassWord(): Date {
        return this._hashedPassWord;
    }

    set hashedPassWord(value: Date) {
        this._hashedPassWord = value;
    }

    get admin(): boolean {
        return this._admin;
    }

    set admin(value: boolean) {
        this._admin = value;
    }
}
