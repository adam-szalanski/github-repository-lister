# GitHub Repository Lister

The GitHub Repository Lister project provides a RESTful API for retrieving repositories and associated branches from the GitHub API based on a user's GitHub username. It utilizes the Spring Framework to handle HTTP requests and interacts with the GitHub API to fetch and present repository data.

## Functionality

The project allows users to retrieve repository information from GitHub by making a GET request to the `/git/{username}` endpoint. The response includes the repository name, owner's login, and an array of branches associated with each repository. Each branch is represented by a `BranchResponse` object, which includes the branch name and the SHA of the last commit.

In case of errors, such as when a user is not found or an unsupported header is provided, the project generates error responses with the corresponding HTTP status code and error message.

## Classes

1. **GitController:** The `GitController` class is a RESTful controller that handles requests related to GitHub repositories. It provides an endpoint (`/git/{username}`) for fetching repositories based on a given username.

2. **GitService:** The `GitService` class interacts with the GitHub API to retrieve repository information. It uses the RestTemplate library to send requests and retrieve data. The retrieved data is then mapped to appropriate Data Transfer Objects (DTOs).

3. **CleanResponseMapper:** The `CleanResponseMapper` class is responsible for mapping the retrieved data to clean response objects. It utilizes the MapStruct library for object mapping, specifically mapping `UserRepoDTO` to `UserRepoResponse` and `BranchDTO` to `BranchResponse`.

4. **UserRepoResponse:** The `UserRepoResponse` class represents the response object for repository information. It includes fields such as `repositoryName` (the name of the repository), `ownerLogin` (the login name of the repository owner), and an array of `BranchResponse` objects representing the associated branches.

5. **BranchResponse:** The `BranchResponse` class represents a branch in a repository. It includes fields such as `name` (the name of the branch) and `lastCommitSha` (the SHA of the last commit on the branch).

6. **ErrorDetails:** The `ErrorDetails` class represents error details when an exception occurs in the project. It includes fields such as `status` (HTTP status code) and `Message` (the corresponding error message).

7. **CustomExceptionHandler:** The `CustomExceptionHandler` class handles exceptions thrown during the execution of the API. It provides custom error handling for specific exceptions and generates appropriate error responses.

## Dependencies

The project has dependencies on the following frameworks, libraries, and tools:

- Spring Framework: Used for creating RESTful APIs and handling HTTP requests.
- Lombok: Used for generating boilerplate code, such as getters and setters, reducing the amount of manual coding.
- MapStruct: Used for object mapping between DTOs and response objects, simplifying the conversion process.

## Author
<table>
<tr>
<td><p align="center">Adam Szałański</p></td>
</tr>
<tr>
<td>
<a href="https://linkedin.com/in/adam-szalanski">
   <img width="300px" height="300px" src="https://media.licdn.com/dms/image/D4D03AQEQQN-CcweVoQ/profile-displayphoto-shrink_800_800/0/1675129015106?e=1691625600&v=beta&t=uvJfoD4nDRJDEpLwzY0u2_ldEXJWMgbcx0iu_FOu7aI" alt="Adam Szałański">
  </a>
</td>
</tr>
<tr>
<td align="center">

[![LinkedIn][linkedin-shield]][linkedin-url-adam]

</td>
</tr>
</table>

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url-adam]: https://linkedin.com/in/adam-szalanski
