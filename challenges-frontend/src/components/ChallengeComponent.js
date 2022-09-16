import React, { useState, useEffect } from 'react';
import { challenge, sendGuess } from '../services/ApiClient';

export default function ChallengeComponent() {
  const [factorA, setFactorA] = useState('');
  const [factorB, setFactorB] = useState('');
  const [user, setUser] = useState('');
  const [message, setMessage] = useState('');
  const [guess, setGuess] = useState(0);

  useEffect(() => {
    async function getChallenge() {
      const res = await challenge();
      if (res.ok) {
        const json = await res.json();
        setFactorA(json.factorA);
        setFactorB(json.factorB);
      } else {
        setMessage("Can't reach the server");
      }
    }
    getChallenge();
  }, []);

  function handleUserChange(event) {
    setUser(event.target.value);
  }

  function handleGuessChange(event) {
    setGuess(event.target.value);
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
    } else {
      setMessage('Error: server error or not available');
    }
  }

  return (
    <div>
      <div>
        <h3>Your new Challenge is</h3>
        <h1>
          {factorA} x {factorB}
        </h1>
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
    </div>
  );
}
