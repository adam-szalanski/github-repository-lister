# GitHub Repository Lister

The GitHub Repository Lister project provides a RESTful API for retrieving repositories and associated branches from the GitHub API based on a user's GitHub username. It utilizes the Spring Framework and interacts with the GitHub API to fetch and present repository data.

## Classes

1. **GitController:** The `GitController` class serves as the RESTful controller for retrieving GitHub repositories. It provides an endpoint for fetching repositories based on a given username.
2. **GitService:** The `GitService` class handles the interaction with the GitHub API. It sends requests to retrieve repositories and associated branches and maps the responses to appropriate DTOs.
3. **CleanResponseMapper:** The `CleanResponseMapper` class is responsible for mapping the retrieved data to clean response objects, specifically `UserRepoResponse` and `BranchResponse`. It utilizes the MapStruct library for object mapping.
4. **UserRepoDTO:** The `UserRepoDTO` class represents the data transfer object (DTO) for repository information. It includes the repository name, owner's information (represented by `OwnerDTO`), and an array of `BranchDTO` objects representing the associated branches.
5. **OwnerDTO:** The `OwnerDTO` class represents the owner of a repository. It contains the owner's login information.
6. **BranchDTO:** The `BranchDTO` class represents a branch of a repository. It contains the branch name and information about the latest commit (represented by `CommitDTO`).
7. **CommitDTO:** The `CommitDTO` class represents a commit in a repository. It contains the SHA (unique identifier) of the commit.

## Functionality

The project allows users to retrieve repository information from GitHub by making a GET request to the `/git/{username}` endpoint. The response includes the repository name, owner's login, and an array of branches associated with each repository. Each branch is represented by a `BranchResponse` object, which includes the branch name and the SHA of the last commit.

The project supports multiple response formats, including JSON and XML. The desired response format can be specified using the `accept` request header.

In case of errors, such as invalid usernames or unsupported response formats, the project generates error responses using the `MessageResponse` class. The error response includes the HTTP status code and a corresponding message.

## Dependencies

The project has dependencies on the following frameworks, libraries, and tools:
- Spring Framework: Used for creating RESTful APIs and handling HTTP requests.
- Lombok: Used for generating boilerplate code, such as getters and setters, reducing the amount of manual coding.
- MapStruct: Used for object mapping between DTOs and response objects.

## Technologies

The project is built using the following technologies:
- Java: The primary programming language used for implementing the project.
- Spring Framework: Provides the foundation for building robust and scalable web applications.
- GitHub API: Interacts with the GitHub API to fetch repository and branch information.

# Author
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
