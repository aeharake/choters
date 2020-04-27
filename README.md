# choters
Chat application (mock) - MVVM architecture

## Project objective ##

At startup, if no users exists locally, the app itself will randomly mock 200 names and persists them locally.

The app will consist mainly of two screens:
#### 1) Screen 1 - Users ####

This is the main activity of the app that will be displaying the users with the the last message sent/received. If no message ever sent/received, only the names will be shown.

#### 2) Screen 2 - Conversation ####

This is the conversation activity, that will be displaying the full name of the person in the ActionBar and also the messages between the user and the person.
Whenever the user sends a message, it should echo back the same message after a random delay ranging from (100 to 500 ms).


## Pattern used - MVVM ##

A chat application is something that would acquire a lot of features and development, we need to look for a pattern that can be extendable and maintainable and also unit-test friendly. MVVM is the new viral pattern for that, that guarantees code cleanliness, scalability and maintainability. 

In this way we will be having decoupled layers that can be easily managed by as many developers as we have.

