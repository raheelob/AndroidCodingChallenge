# True Caller Assignment Raheel Masood

## The Brief
Single Activity Architecture using:
1) MVVM + Clean
2) DI Using HILT.
3) Coroutine with flow
5) 2 way dataBinding.
6) Unit test of repository and view model included.
7) Navigation graph.

##Logic for each Question

#General Logic =>
Steps...
Views will call view model. 
View model is injected with use case.
Use case makes a call to repository.
Repository returns the data to the view model.
View model returns the response to the view I.e fragment. 

#1.Truecaller10thCharacterRequest:
Once the response comes, convert string into array and get the 10th character.

#2.TruecallerEvery10thCharacterRequest:
Once the response comes, convert string into char array. Loop through the char array, goto every 10th index, get character, and add this 
character to the concatenated string. (Includes spaces as well).

#3.trueCallerWordCounterRequest:
Once the response comes, convert string into array by splitting based on space, make every first character to uppercase,
to keep case insensitive. Loop through the array, and store word as key (unique), and value as its counter.
If key is not present then add it as new key, and value as '1'
if key is present then get the value, increment and place incremented value back to the key.
loop until last Index is reached. 

##Instrument Test.
An Instrument test for the view model is added, using HILT.(androidTest) Folder.
To run this test, go to androidTest folder, click play on next to the function, make sure device is connected.



character to the concatenated string.