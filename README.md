# ComposeMovieSpringBoot
This is a very stupid simpel App. It only has one REST API (/api/movies/{titles}) which requests in parallel the OMDB Api to get all movie informations according the received titles.

# Getting Started
1. Clone the project
2. Set the `omdb.api-key=` in the `application.properties` created in http://www.omdbapi.com/
3. Run `mvn clean install`
4. Have fun 😎

# Sidehint
- Every push to the master will trigger a GitHub Action which is building the app via maven and pushing the created docker image to my docker hub (https://hub.docker.com/repository/docker/milchkarton/compose)

# Used technologies
- Kotlin (Coroutines)
- Maven
- Microsoft Excel (No. Just Kidding. No one wants to have some excel sheets when you can have cool applications 😉 )
