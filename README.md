# Dungeon Crawler

A text-based dungeon crawler CLI written in Java.
Explore different rooms, fight enemies, collect items, and face a final boss at the end.

This project was built to reinforce my Java fundamentals through a fun, complete, shippable game.

---

## Features

- Multiple interconnected rooms
- Turn-based combat system
- Enemies with health and attack behavior
- Inventory system (weapons and potions)
- Item pickup, equip, and use commands
- Boss room with confirmation prompt
- Win / lose conditions
- Error handling of invalid commands

---

## Requirements

- **Java 21+**
- **Maven** (optional)

---

## Running the Game

### Option 1: Run the JAR (Recommended)

download the prebuilt JAR from the GitHub release then run:

```bash
java -jar dungeon-crawler.jar
```
### Option 2: Build and run with Maven

```bash
mvn clean package
java -jar target/dungeon-crawler-1.0.0.jar
```