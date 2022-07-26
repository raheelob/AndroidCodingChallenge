# Android Coding Challenge Raheel Masood

#The Brief
A simple app which shows a list of shape with their descriptions.
It has 3 filters: Available, Not available, and Both:
A) In case of Available, Shape image appears on the starting of the item layout.
B) In case of Not-Available, Shape image appears on the ending of the item layout.

OnClick on a particular item, it takes the user to the detail of the selected shape.

At the bottom of the detail page, a link to the design pattern used is shown excluding local data source.


#Architecture and design.
Single Activity Architecture using:
1) MVVM + Clean
2) DI Using HILT.
3) Coroutine with flow
5) 2 way dataBinding.
6) Unit test of repository and view model included.
7) Navigation graph.
8) Error Handling, in case of no internet or error from network. Once the internet resumes, user can hit the request again.



#General Logic for the code
Steps...
Views will call view model.
View model is injected with use case.
Use case makes a call to repository.
Repository returns the data to the view model.
View model returns the response to the view I.e fragment.


P.S: Unit Tests are included in test folder.