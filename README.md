# Archangels
## Steps: 
First, clone this repository with:
```
    git clone https://github.com/SebasGarcia08/Archangels.git
```

Then, go to the Angels directory and run this console command:
``` 
    java -cp ./bin ./src/ui/Main.java
``` 

## Overview
### Statement
A friend of your Algorithm teacher likes angels very much and has commented that it would be interesting to have a small application that reminds her of their important dates, type, representative color, among others. Her teacher, having the need to pose a problem to her APO1 students, began asking her friend and this is what she told her:
Angels are categorized into several types: archangels, cherubs, seraphim.
All angels of the same type are grouped into a legion, for example: the archangel angels are grouped into a legion called "Highest Maxim." The cherub angels are grouped in a legion called "Liberi" and finally the seraphim are grouped in the legion "Seraphim".
Note: For this exercise we are only interested in the legion of the archangels.
Each archangel has a name (which ends in the syllable "el"), a photo, a prayer, a date of celebration (day and month) and a power. Among some of the recognized powers are: protection, health, abundance, justice, loyalty, but the user can create more powers. Each archangel is assigned a special candle, which has a color, a size, an essence and a degree of illuminance.

### Functional requirements
The application must allow:
<li class="item">
<ul>Enter the archangels (there is only one archangel per name and there is only one archangel per power)</ul>
<ul>Count the archangels entered</ul>
<ul>Display the archangel's information given its name</ul>
<ul>Display the archangel's information given its power</ul>
<ul>Display the celebrations to be held given one month (you must show the name of the archangel, the day of the celebration, the color and the essence of your candle)</ul>
<ul>Display all celebrations: Review each of the created angels and concatenate the response with the date of celebration, like this: name of the angel: date of celebration, name of the angel: date of celebration. Example: Miguel: September 29, Rafael: June 10.</ul>
</li>

## Problem analysis
### UML Class diagram
<img src="problem analysis\ArchangelsLab.jpeg" alt="UML Class Diagram" />

### UML Object Diagram
<img src="problem analysis\ArchangelsLab Object Diagram.jpeg" alt="UML Object Diagram"/>

