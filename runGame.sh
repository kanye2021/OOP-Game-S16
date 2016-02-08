
echo "Adding java files to be compiled"
find src -name "*.java" > sources.txt
echo "Creating bin "
mkdir bin
echo "Compiling java files "
javac -d bin @sources.txt &
echo "Copying source files to bin"
cp -R src bin/
echo "Running Game"
java -cp bin RunGame
