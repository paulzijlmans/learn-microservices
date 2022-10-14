import React, { useState, useEffect } from 'react';
import { getChallenge, getAttempts, sendGuess } from '../services/ApiClient';
import LastAttemptsComponent from './LastAttemptsComponent';
import LeaderBoardComponent from './LeaderBoardComponent';

export default function ChallengeComponent() {
  const [factorA, setFactorA] = useState('');
  const [factorB, setFactorB] = useState('');
  const [user, setUser] = useState('');
  const [message, setMessage] = useState('');
  const [guess, setGuess] = useState(0);
  const [lastAttempts, setLastAttempts] = useState([]);

  useEffect(() => {
    refreshChallenge();
  }, []);

  function handleUserChange(event) {
    setUser(event.target.value);
  }

  function handleGuessChange(event) {
    setGuess(event.target.value);
  }

  async function refreshChallenge() {
    const res = await getChallenge();
    if (res.ok) {
      const json = await res.json();
      setFactorA(json.factorA);
      setFactorB(json.factorB);
    } else {
      setMessage("Can't reach the server");
    }
  }

  async function handleSubmitResult(event) {
    event.preventDefault();
    const res = await sendGuess(user, +factorA, +factorB, guess);
    if (res.ok) {
      const json = await res.json();
      if (json.correct) {
        setMessage('Congratulations! Your guess is correct');
      } else {
        setMessage(
          `Oops! Your guess ${json.resultAttempt} is wrong, but keep playing!`
        );
      }
      updateLastAttempts(user);
      refreshChallenge();
    } else {
      setMessage('Error: server error or not available');
    }
  }

  async function updateLastAttempts(userAlias) {
    const res = await getAttempts(userAlias);
    if (res.ok) {
      let attempts = [];
      const data = await res.json();
      data.forEach((item) => {
        attempts.push(item);
      });
      setLastAttempts(attempts);
    }
  }

  return (
    <div className='display-column'>
      <div>
        <h3>Your new Challenge is</h3>
        <div className='challenge'>
          {factorA} x {factorB}
        </div>
      </div>
      <form onSubmit={handleSubmitResult}>
        <label>
          Your alias:
          <input
            type='text'
            maxLength={12}
            name='user'
            value={user}
            onChange={handleUserChange}
          />
        </label>
        <br />
        <label>
          Your guess:
          <input
            type='number'
            min='0'
            name='guess'
            value={guess}
            onChange={handleGuessChange}
          />
        </label>
        <br />
        <input type='submit' value='Submit' />
      </form>
      <h4>{message}</h4>
      {lastAttempts.length > 0 && (
        <LastAttemptsComponent lastAttempts={lastAttempts} />
      )}
      <LeaderBoardComponent />
    </div>
  );
}
