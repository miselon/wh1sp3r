# **wh1sp3r**
end-to-end encrypted, self-destructing note generator
---

# [LIVE DEMO](https://wh1sp3r.herokuapp.com/)

### What is it?
This is a **Angular / Java + Spring Boot + Postgres** web application made to be a clone of privnote.com.
I created it because I liked how simple and secure the encryption mechanism is. (at least from how I think it works)
### How does it work?
1. user creates a text note with an expiration date
2. client app generates a random encryption key
3. client then encrypts the note contents and sends it the server
4. server generates a unique ID for it and saves it in the Postgres database
5. server returns the generated ID to the client
6. client returns a link to the user, containing note's ID and the encryption key 
7. user then can use the generated link to decrypt and read the message
8. retrieving the encrypted blob from the server automatically deletes it from the database

### What did I learn creating it?
This is my first "full stack" web app, I learned basics of Angular framework and overall basics of creating a crude front-end from an idea to the finished thing. Also it was a great opportunity to extend my knowledge of encryption related concepts.

### Things that I would like to improve/add in the future:
- [ ] Implement proper Spring exception handling
- [ ] Make the front-end responsive
- [ ] Maybe someday implement peer-to-peer text/video chatting
