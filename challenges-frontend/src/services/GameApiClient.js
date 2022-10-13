const SERVER_URL = 'http://localhost:8081';
const GET_LEADERBOARD = '/leaders'

export function getLeaderBoard() {
    return fetch(SERVER_URL + GET_LEADERBOARD);
}