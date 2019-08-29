const GUEST_TYPES = ['BRIDE', 'GROOM', 'BESTMAN', 'MADE OF HONOR', 'PARENT', 'MUSICIAN', 'PHOTOGRAPHER', 'REGULAR', 'CHILD'];

export class Guest {

    constructor(
        public id: number,
        public fullName: string,
        public type: string,
        public guestOf: string,
        public arrivalConfirmed: boolean,
        public presentAtReception: boolean,
        public travelingWithBus: boolean,
        public presentAtWedding: boolean,
        public presentAtDinner: boolean,
        public sleeping: boolean,
        public presentAtSundayLunch: boolean    ) {}
}