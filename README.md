# Arkanoid Game

## Overview
This project is a classic **Arkanoid Game** implemented in Java.<br>
The objective is to destroy all the blocks by bouncing a ball off a paddle controlled by the player.<br>

## Features
- **Paddle Control**: Move the paddle left and right to bounce the ball.
- **Blocks**: Breakable blocks arranged in rows that the player must destroy.
- **Ball Physics**: The ball bounces off the walls, paddle, and blocks with realistic movement.
- **Collision Detection**: Collisions are accurately detected and handled to ensure smooth gameplay.
- **Game Over**: The game ends when the player destroys all the blocks or loses all the balls.
- **Score Tracking:** Keep track on the score on the top of the screen.

## How to Play
1. Run the program.
2. Control the paddle movement using the **left** and **right** arrow keys.
3. Try to break all the blocks by bouncing the balls off the paddle.
4. Avoid dropping the balls below the paddle.
5. The game ends when the player destroys all the blocks or loses all the balls.

## Technologies Used
- **Language:** Java
- **Concepts:** Classes, Inheritance & Polymorphism, Observer, Collision Detection, Input Handling, Bouncing Mechanics, Sprite Animation & Management, Packages

## Setup and Usage
1. Clone the repository:
   ```bash
   git clone https://github.com/Fisher-Shira/Arkanoid-Game.git
2. Navigate to the project directory:
   ```bash
    cd Arkanoid-Game
3. Compile the project:
   ```bash
    javac -d build src/geometry_primitives/*.java src/sprite_settings/*.java src/*.java
4. Run the executable:
   ```bash
    java -cp build ArkanoidGame

## Project Structure
- **`src/`**: Contains the Java source code for all game components, including the paddle, ball, blocks, and game logic.
- **`geometry_primitives/, sprite_settings/`**: Java packages inside src folder.
