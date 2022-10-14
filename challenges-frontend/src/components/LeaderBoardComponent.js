import React, { useEffect, useState } from 'react';
import { getUsers, getLeaderBoard } from '../services/ApiClient';

export default function LeaderBoardComponent() {
  const [leaderBoard, setLeaderBoard] = useState([]);
  const [serverError, setServerError] = useState(false);

  useEffect(() => {
    async function refreshLeaderBoard() {
      try {
        const lbData = await getLeaderBoardData();
        let userIds = lbData.map((row) => row.userId);
  
        if(userIds.length > 0) {
          try {
            const data = await getUserAliasData(userIds);
  
            // build a map of id -> alias
            let userMap = new Map();
            data.forEach((idAlias) => {
              userMap.set(idAlias.id, idAlias.alias);
            });
  
            // add a property to existing lb data
            lbData.forEach((row) => (row["alias"] = userMap.get(row.userId)));
            updateLeaderBoard(lbData);
          } catch (reason) {
            console.log("Error mapping user ids", reason);
            updateLeaderBoard();
          }
        }
      } catch (reason) {
        setServerError(true);
        console.log("Gamification server error", reason);
      } 
    }

    refreshLeaderBoard();
    const interval = setInterval(() => {
      refreshLeaderBoard();
    }, 5000);
    return () => clearInterval(interval);
  }, []);

  async function getLeaderBoardData() {
    const lbRes = await getLeaderBoard();
    if (lbRes.ok) {
      return lbRes.json();
    } else {
      return Promise.reject("Gamification: error response");
    }
  }

  async function getUserAliasData(userIds) {
    const usRes = await getUsers(userIds);
    if (usRes.ok) {
      return usRes.json();
    } else {
      return Promise.reject("Multiplication: error response");
    }
  }

  function updateLeaderBoard(lb) {
    setLeaderBoard(lb);
    setServerError(false);
  }

  if (serverError) {
    return (
      <div>
        We're sorry, but we can't display game statistics at this moment.
      </div>
    );
  }
  
  return (
    <div>
      <h3>Leaderboard</h3>
      <table>
        <thead>
          <tr>
            <th>User</th>
            <th>Score</th>
            <th>Badges</th>
          </tr>
        </thead>
        <tbody>
          {leaderBoard.map((row) => (
            <tr key={row.userId}>
              <td>{row.alias ? row.alias : row.userId}</td>
              <td>{row.totalScore}</td>
              <td>
                {row.badges.map((b) => (
                  <span className="badge" key={b}>
                    {b}
                  </span>
                ))}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
