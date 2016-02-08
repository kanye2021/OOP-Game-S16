# OOP-Game-S16
Objected Oriented Programming Spring 2016, Iteration 1.

**Team KANYE 2020**
- Sergio Puleri
- Denzel Mathews
- Chen Ben
- David Peguero
- Austin Seber
- Bradley Treuherz
- David Yeung
- Mathew Tschiggfire
- Phillip Smith
- Jorge Varela


## How to run
- Clone the repository if you do not have the source code, with:
```bash
git clone https://github.com/kanye2021/OOP-Game-S16.git
```
- Run the following command at the root directory to compile and run the game:   
```bash
find $PWD -name "*.java" > sources.txt && mkdir bin &&  javac -d bin @sources.txt && cp -R src bin/ && cd bin && java RunGame && cd .. && rm -rf bin
```
- To just compile the game run:
```bash
find $PWD -name "*.java" > sources.txt && mkdir bin &&  javac -d bin @sources.txt && cp -R src bin/
```
- Then, to run the game, run:
```bash
cd bin && java RunGame && cd ..
```
