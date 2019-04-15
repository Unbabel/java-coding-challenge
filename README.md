# Unbabel Java Challenge

Hey :smile:

Welcome to our Java Challenge repository. This README will guide you on how to participate in this challenge.

**Please fork this repo before you start working on the challenge.** We will evaluate the code on the fork.

**FYI:** Please understand that this challenge is not decisive if you are applying to work at [Unbabel](https://unbabel.com/jobs). There are no right and wrong answers. This is just an opportunity for us both to work together and get to know each other in a more technical way.

## Challenge


#### Web Application in JAVA

Build a web application in Java composed by a page with 1 input text field, 1 picklist field, 1 button and 1 output text area:

1. **Input text field: “Text to translate:”**
	To introduce the text to be translated.
 
2.  **Picklist field: “Translate to:”**
Must present a list of languages to translate. (you can check the supported languages in the Unbabel website).
 
3.  **Button: “Submit”**
Should submit the text to our API (link in Resources) in order to be translated. (Be aware that you must check when the Response Status is completed).
 
4.  **Five column table with:**
“From Language, Original Text, To Language, Translated Text, Status".
Should present all the translations already made.
 
You can add images/fields/buttons as you seem fit. 
 
Translations must be done using the Unbabel API in sandbox mode. (Ask whoever has been in contact with you about the credentials).
You must also do a document **"Application Technical Scope"**  with:
* Data Model
* Technical Scope
* Use cases

Add as a pdf in the repository.

#### Recommendations

In this repo you will find a basic project using Spring-boot with thymeleaf as template engine and H2 as database using Hibernate ORM.
You are recommended to use this boilerplate, but if you wish to build on another platform or build from scratch, surprise us :smile: !


To run the example:
`mvn spring-boot:run`


To run tests on the example:
`mvn test`

#### Requirements
* Create a scalable application.
* Only use Unbabel's Translation API on sandbox mode
* Have unit tests

#### Resources
* Unbabel's API: http://developers.unbabel.com/
* Spring Boot guides: https://spring.io/guides