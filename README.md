# OOP-Game-S16
Objected Oriented Programming Spring 2016


## How to run
- Clone the repository with:
```bash
git clone https://github.com/kanye2021/OOP-Game-S16.git
```
- Run the following command in the same directory to compile and run the game:   
```bash
find $PWD -name "*.java" > sources.txt && mkdir bin &&  javac -d bin @sources.txt && cp -R src bin/ && cd bin && java RunGame && cd .. && rm -rf bin
```
- To just compile the game run:
```bash
find $PWD -name "*.java" > sources.txt && mkdir bin &&  javac -d bin @sources.txt
```
- Then, to run the game, run:
```bash
cp -R src bin/ && cd bin && java RunGame && cd .. && rm -rf bin
```
