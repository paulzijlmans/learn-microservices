const SERVER_URL = 'http://localhost:8080';
const GET_CHALLENGE = '/challenges/random';
const POST_RESULT = '/attempts';
const GET_ATTEMPTS_BY_ALIAS = '/attempts?alias=';

export function challenge() {
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
