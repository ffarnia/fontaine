# fontaine
Using https://developer.github.com/v3/ document to search GitHub public repositories and list committers and their impact on timeline.

This project is written with below tools and technologies:
1. java8
2. Maven
3. spring boot
4. vue
5. intellijIDEA

# run
To build and run this application by maven clean compile install
then can run java -jar github-api-0.0.1-SNAPSHOT.jar
app run on port 8082
this backend app provide rest webservices:
1. search in public repository by name: endpoint:  search/query/{query}
2. get list of contributors: endpoint: search/contributors?url={url} this url given by committers_url of every selected repository
3. get number of committers on every repository: endpoint: search/commits/number?url={url} url can be set via commits_url of every selected repository
4. get date of commits per repository: endpoint: search/commits/timeline?url={url}

To run front end in github-api-frontend:
npm install
npm run serve
the UI can be available on 127.0.0.1:8081 with nodejs server


