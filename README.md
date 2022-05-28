# RollBounce

NOTES
- RollBounce.prop file was moved under src folder in order for my laptop to be able to read the property file (it's a Windows issue). This is because running the RollBounce.java file is done from the src folder (since it is A2.RollBounce). 
- used https://kodejava.org/how-do-i-read-a-configuration-file-using-java-util-properties/ to read property file + get values
- generated random pastel colors using this: https://stackoverflow.com/questions/4246351/creating-random-colour-in-java
- Because the balls go out of visibility on the canvas screen on my laptop otherwise, I created a windowAdjustment variable (currently set to 75) that is used to adjust the canvas by creating a smaller set of dimensions from windowHeight and windowWidth. This is so the range of the balls remain within this smaller set of dimensions than the given canvas dimensions themselves, and so the balls stay entirely visible. I'm not sure if this variable adjustment is necessary on another desktop. 


This is a working starting point for a bouncing ball simulation in Java Swing / AWT. (When you compile and run this, you'll see two animated balls moving horizontally at different speeds. You may use this as the starting point to meet the assignment objective.)

There are many online tutorials for implementing 2-dimensional animations in Java. Here are two:
* [Create basic shapes](https://www.youtube.com/watch?v=4YhrmAGpVtI)
* [Create simple animations](https://www.youtube.com/watch?v=I3usNR8JrEE)

Details of this assignment are provided separately.
