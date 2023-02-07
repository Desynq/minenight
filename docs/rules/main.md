# Rules

## Main Rules

### Misc Rules

- Skip Percentage
  - What percentage of players need to skip in order to skip the talking phase and go straight to voting
  - Float between 0 and 1
    - Rounds up during gameplay. So 50% with 7 players will need 4 players (not 3.5) to skip talking.
- Pause Percentage
  - What percentage of players need to agree in order to pause the game
  - Float between 0 and 1
    - Rounds up during gameplay. So 50% with 7 players will need 4 players (not 3.5) to skip talking.

### Voting Rules

- Clockwise Proposing
  - Proposing goes clockwise instead of counter-clockwise
  - True/False

- Hammer Threshold
  - At how many denied votes will hackers win?
  - Integer between 1 and 10

- Voting Time
  - How long do players have, in seconds, to vote?
  - Integer between 30 and 90

- Talking Time
  - How long do players have, in seconds, before voting to talk things through?
  - Integer between 0 and 120

- Hacker Consensus
  - Can hackers see their teammates' votes?
  - True/False
    - Scripties cannot see their teammates' votes regardless

- Second Thoughts
  - Can players choose to not vote?
    - If all players choose to not vote on a vote, the vote will skipped
  - True/False

- Abstinence
  - Players cannot vote for their own proposals
  - True/False

### Node Rules

- Stealth Hacking
  - Agents cannot see hacked nodes
  - True/False

- Messy Hacking
  - Ignore "Stealth Hacking" if a scriptie hacked the node
  - True/False

- Hacker Count
  - Players can see how many hackers hacked a node.
  - True/False/Scripties
    - Scripties = Only the amount of scripties who hacked the node is shown

- Traceback
  - Should a node show up as bluffed if a SCRIPTIE or INTERN was in it?

### Hacker Team

- Bruteforcer
  - Which hacker teams are unable to secure nodes
  - NEITHER/HACKERS/SCRIPTIES/BOTH

- Visibility
  - Which teams can see them
  - Hackers/Scripties/Neither/Both

### Scriptie Team

- Count
  - How many hackers are scripties?
  - Integer between 0 and 3

- Visibility
  - Which teams can see them
  - Hackers/Scripties/Neither/Both

## Gamemodes

- Gamemode
  - Which gamemode is chosen
  - Default/Mainframe/Interns
