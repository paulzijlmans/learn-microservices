const SERVER_URL = 'http://localhost:8000';
const GET_CHALLENGE = '/challenges/random';
const POST_RESULT = '/attempts';
const GET_ATTEMPTS_BY_ALIAS = '/attempts?alias=';
const GET_USERS_BY_IDS = '/users';
const GET_LEADERBOARD = '/leaders'

export function getChallenge() {
  return fetch(SERVER_URL + GET_CHALLENGE);
}

export function sendGuess(user, a, b, guess) {
  return fetch(SERVER_URL + POST_RESULT, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      userAlias: user,
      factorA: a,
      factorB: b,
      guess: guess,
    }),
  });
}

export function getAttempts(userAlias) {
  return fetch(SERVER_URL + GET_ATTEMPTS_BY_ALIAS + userAlias);
}

export function getUsers(userIds) {
  return fetch(SERVER_URL + GET_USERS_BY_IDS + '/' + userIds.join(','));
}

export function getLeaderBoard() {
  return fetch(SERVER_URL + GET_LEADERBOARD);
}