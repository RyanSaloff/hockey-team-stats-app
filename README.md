# My Personal Project - Ryan Saloff
## Topic: NHL Team Customizable Statistics Application

In this project, I plan to design an application to organize statistics for a professional hockey team.
The aim of this application is to allow a user to record and update statistics for each player on a team.
A list of such statistics include:

**Player Stats:**
- Games Played
- Goals
- Assists
- Points
- Plus/Minus
- Penalty Minutes 
- Time on Ice Per Game 
- Hits
- Blocked Shots
- Shots
- Shooting %
- Power Play Goals
- Short Handed Goals
- Faceoffs Taken
- Faceoffs Won
- Faceoff Win %
- Shootout Attempts
- Shootout Goals
- Shootout Goals %

**Goalie Stats:**
- Games Played
- Games Started
- Wins
- Losses
- Overtime Losses
- Shutouts
- Saves
- Save %
- Shots Against
- Goals Against
- Goals Against Average
- Penalty Minutes
- Shootout Shots Against
- Shootout Saves
- Shootout Save %
- Goals
- Assists

As a result, this application is designed to be used by a hockey team to both record and visualize stats for their players.
After statistics have been added by the user for each player, the user can choose to display a table to show 
the players' statistics. The user has the choice to show either skater or goalie stats.
Furthermore, users can easily sort players by a metric of their choice.

I chose this project because I really enjoy data analysis and how it can be applied to  sports statistics.
Furthermore, I wanted to create a project that would be relevant for my future job prospects in data analytics and
data science. Creating an application that takes in and organizes data is a skill that is very important to demonstrate
in the field of data analysis and data science.

## User Stories
**Add an X to a Y** 
- **DONE** ~~I want to be able to select a player and update that player's information~~ 
- **DONE** ~~I want to be able to add or remove a player from a team~~

**View a list of items**
- I want to be able to select some or all of the stats for my team and view a table for those specific player/goalie
stats
- **DONE** ~~I want to be able to select and view either the skaters' or the goalies' information~~  

**Other**
- **DONE** ~~I want to be able to know many skaters and how many goalies are on a team~~ 
- I want to be able to select a quantitative statistic and view it so that it is sorted in either ascending or
descending order
- I want to be able to select a player and automatically calculate a percentage statistic by dividing the total number
of attempts by the number of successful attempts
  - For example, if I input the number of Faceoffs Taken and the number of Faceoffs won for a skater, I want to
  calculate and store the Faceoff %

## Phase 1 Update
In this phase, I created an application to create a team of hockey players. Each player is either a Goalie or a Skater.
Additionally, each player on a team has a name, a unique jersey number, an age, and a position. Positions include:

- "G" - Goalie
- "D" - Defenseman
- "C" - Center
- "RW" - Right Wing
- "LW" - Left Wing

Currently, this application has the following options: 
- Set a new team name
- View either the team's list of goalies and players
- Return the number of players on a team
- Add a player to a team
- Remove a play from a team
- Update a player's information on a team

Now that players and goalies can be added to a team, the next stage will involve saving teams and creating
tables for the team's statistics.
