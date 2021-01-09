# Welcome to the Javacruitment

Technical interview consists of two main parts: the first one (which you'll have to perform yourself) is setting up your environment. Make sure that:

- The project has been imported into your IDE, and it compiles and runs fine.
- You're able to run Docker containers on your computer.
- You have some necessary tools: a REST client (e.g. Postman, Swagger, curl, wget) and a database client.
- You're familiar with the project structure.

The second part is a live coding during which you'll be provided with a list of tasks we'd like you to implement. During that part you're allowed to use the internet freely.

# Running the application

- This project expects a PostgreSQL database listening on port 5432. We prepared a docker-compose.yml that you should use for that purpose. To run it, simply execute:
`docker-compose up`
- Using your favorite IDE, execute the main class: `com.javacruitment.JavacruitmentApplication`
- Alternatively, you can run the following command from the terminal: `./gradlew bootRun`
- After successfully deploying the application, open your browser and go to `http://localhost:8080/`

# Troubleshooting

- Please make sure you have installed and properly configured Lombok ("Enable Annotation Processing" option in IntelliJ IDEA). In case of any issues please see: https://www.baeldung.com/lombok-ide
- If you're using Windows, we advise you to get VirtualBox with Linux in order to run Docker natively.
