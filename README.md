# README

## Stars
**TODO: Fill out this section!**

###Known bugs
none
###Design details specific to your code
Hashmap is used to store command actions in Repl.java
Lambda is also used to represent the actions
###Runtime/space optimizations you made beyond the minimum requirements (if applicable)
none
###How to run any tests you wrote/tried by hand
junit test: 
mvn test 
system test:
cs32-test tests/ta/stars/stars1/*.test
###How to build/run your program
mvn package 
./run
###Answers to design questions
I used a hashmap to store every action's name and the action itself.
I defined a sub class, StarsRepl. StarsRepl is where I have my command action.
If I have to add 10+ more commands, I can simply add the additional commands or actions in StarsRepl.
In the new command object/class, add the specific actions.
Lastly, add the new command object/class into the contractor.
###Explanations for any Checkstyle errors your code has (hopefully none)
none 