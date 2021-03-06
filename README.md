# spring-boot-security-oauth
### Tech
* Spring Boot
* Spring Security
* Spring Security OAuth
* Spring Data JPA

### build
```sh
    cd data-rest-api && mvn clean package spring-boot:run
```
```sh
    curl http://localhost:9000/api/users
```
# :(
```json
{
   "error":"unauthorized",
   "error_description":"Full authentication is required to access this resource"
}
```

```sh
$ curl -X POST -vu clientApp:123456 http://localhost:9000/oauth/token -H "Accept: application/json" -d "password=123456&username=dorris&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientApp"
```
# :|
```json
{
   "access_token":"3956c4a1-796f-415b-8f12-7e92cdd39aaa",
   "token_type":"bearer",
   "refresh_token":"0416f91c-59e0-456b-a3c2-5f7eda89f629",
   "expires_in":43199,
   "scope":"read write"
}
```
```sh
curl http://localhost:9000/api/users -H "Authorization: Bearer 3956c4a1-796f-415b-8f12-7e92cdd39aaa"
```
# :)

```json
[  
   {  
      "login":"dorris",
      "name":"Dorris Froelich",
      "email":"dorrisfroelich@gmail.com"
   }
]
```
