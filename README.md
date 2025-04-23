# Plants vs Zombies Game

## Overview
This project is a simple implementation of the classic Plants vs Zombies game using Java and Gradle. The game features a graphical interface with a main panel containing 5 lanes, where players can select seeds and place plants to defend against incoming zombies.

## Project Structure
The project is organized as follows:

```
plants-vs-zombies
├── app
│   ├── build.gradle
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── org
│   │   │   │       └── example
│   │   │   │           ├── App.java
│   │   │   │           ├── game
│   │   │   │           │   ├── GamePanel.java
│   │   │   │           │   ├── Lane.java
│   │   │   │           │   ├── Plant.java
│   │   │   │           │   ├── Zombie.java
│   │   │   │           │   ├── Peashooter.java
│   │   │   │           │   ├── Sunflower.java
│   │   │   │           │   └── Potato.java
│   │   │   │           └── graphics
│   │   │   │               ├── Pea.java
│   │   │   │               └── Sprite.java
│   │   │   └── resources
│   │   │       └── application.properties
│   │   └── test
│   │       ├── java
│   │       │   └── org
│   │       │       └── example
│   │       │           └── AppTest.java
│   │       └── resources
├── settings.gradle
└── README.md
```

## Game Features
- **Lanes**: The game consists of 5 lanes where players can plant their defenses.
- **Plants**: Players can choose from three types of plants:
  - **Peashooter**: Shoots peas that damage zombies.
  - **Sunflower**: Generates sun points for planting more plants.
  - **Potato**: A defensive plant that can be eaten by zombies.
- **Zombies**: Zombies will attempt to eat the plants, and they can take damage from the Peashooter.

## Setup Instructions
1. Clone the repository to your local machine.
2. Navigate to the `app` directory.
3. Run `gradle build` to build the project.
4. Execute the game using `gradle run`.

## Game Rules
- Place plants strategically in the lanes to defend against zombies.
- Use Sunflowers to generate sun points for planting more powerful plants.
- The Peashooter will shoot peas at zombies, reducing their health.
- If a zombie reaches the end of a lane, it will eat the plants in its path.

Enjoy playing the game!# plants-vs-zombies-threaded
