import {decorate, observable, action} from "mobx";
import moment from 'moment';

import Rest from '../Services/Rest'
import demo_events from './Events'
import {Websocket,NEW_MEETING,UPDATE_MEETINGS,UPDATE_MEETING} from '../Services/Websocket';
import {merge} from '../Services/Util';
 
const rest = new Rest();
const {eventEmitter,send} = Websocket('meeting');

const changeDate = (meetings) => {
    return meetings.map(meeting => {
        console.log("---> "+moment(meeting.start))
        meeting.start = moment(meeting.start).toDate();
        meeting.end = moment(meeting.end).toDate();
        return meeting;
    })
}

class Meeting {
    events = []

    constructor(props) {
        eventEmitter.on(UPDATE_MEETING,(updateEvent) => {
            console.log('notifying meeting '+updateEvent);
            this.events = merge(this.events,changeDate([updateEvent]));
        });
        eventEmitter.on(UPDATE_MEETINGS,(updateEvents) => {
            console.log('notifying meeting '+updateEvents);
            this.events = merge(this.events,changeDate(updateEvents));
        });
        eventEmitter.on(NEW_MEETING,(newEvens) => {
            console.log('notifying new meeting '+newEvens);
            this.events = this.events.concat(changeDate([newEvens]));
        });
    }

    async getMeeting(start,end) {
        try {
            const mStart = moment(start);
            const checkStartDate = ((!this.startDate) || (mStart.isBefore(this.start)));
            const mEnd = moment(end);
            const checkEndDate = ((!this.endDate) | (mEnd.isAfter(this.start)));

            if( checkStartDate && checkEndDate ) {
                const response = await rest.getMeetings(start.utc().format(),end.utc().format());
                const data = changeDate(response.data);
                this.events = merge(this.events,data);
                this.startDate = mStart;
                this.endDate = mEnd;
            }
        } catch(error) {
            console.log('Failing to request meetings '+error);
        }
    }

    async createMeeting(event) {
        try {
            await rest.createMeeting(event);
        } catch(error) {
            console.log('Failing to create meetings '+error);
        }
    }

    async updateMeeting(event) {
        try {
            await rest.updateMeeting(event);
        } catch(error) {
            console.log('Failing to update meetings '+error);  
        }
    }
}

decorate(Meeting,{
    getMeeting: action,
    events: observable,
    createMeeting: action,
    updateMeeting: action
  })

export default Meeting;