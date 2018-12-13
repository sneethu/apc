import EventEmitter from 'eventemitter3';

export const eventEmitter = new EventEmitter();

export const NEW_MEETING = 'NEW_MEETING';
export const UPDATE_MEETINGS = 'UPDATE_MEETINGS';
export const UPDATE_MEETING = 'UPDATE_MEETING';

const sockets = {}

export const Websocket = (room) => {
    const ws = new WebSocket("ws://localhost:8301/ws/meeting");
    const eventEmitter = new EventEmitter();
    ws.onmessage = (msg) => {
        const data = JSON.parse(msg.data);
        console.log('meeting: '+data.type); 
        eventEmitter.emit(data.type,data.meeting);
    }
    return {
        eventEmitter,
        send: function(type,data) {
            sockets[room].emit(type,data);
        }
    };
}