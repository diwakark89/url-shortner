# url-shortner
Project to Shorten the URL. Application takes the Long URL and gives the short url and vice versa. Response is like Base URL + Shorten String.

Used Base URL is = https://www.shorturl.com/, which is configurable in application.properties. Project use the Base64 encoding to generate the identifier for the Long URL.

Tech Stack:
Java 17
Spring Boot 3.3.1
MongoDB

Example:
### Create Short URL
> 
> Post API:\
> curl -X POST "http://localhost:8080/url/shorten?originalUrl=https://github.com/diwakark89/url-shortner"
>
> Response:\
> https://www.shorturl.com/MTAwMDAz 

### GET Original URL from Short URL
>
> Get API:\
> curl -X GET "http://localhost:8080/url?shortUrl=https://www.shorturl.com/MTAwMDAy"
>
> Response:\
> https://github.com/diwakark89/url-shortner


Note: To run the project MongoDB installation is required