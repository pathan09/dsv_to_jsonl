# dsv_to_jsonl
read a large text file then write it as jsonl

# How to run the project
1. clone the project
2. open the project by intellij
3. add 3 arguments value in edit comfigure tab. like sourceFile destinationPath separator. example "D:\\SourceCode\\input.txt" "D:\\SourceCode" ,
4. run the project

# How to create jar
1. In project dir open the terminal
2. mvn clean package

## How to use the jar
1. java -jar .\target\application-final.jar "sourceFile" "destinationPath" ',' 
