# CONTROLLER TEST

Test the controller layer is a little complicated because this layer is the biggest and more important for an api application. We can split the responsibilities in 6 parts:

1. Verifying HTTP Request Matching
2. Verifying Input Deserialization
3. Verifying Input Validation
4. Verifying Business Logic Calls
5. Verifying Output Serialization
6. Verifying Exception Handling


When we look at unit test, we think about testing the code that you can see in front of you, all the lines of the object that you are seeing. For example

```kotlin
    @GetMapping("/{id}")
    fun show(@PathVariable id: Long) : ResponseEntity<TopicView?> {
        val topic = topicService.getById(id)
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(topic)
    }
```

So a typical unit test would call show and would check if topicService is called and the return of the function. But...
You can have a broader picture and think about the code like a context. In the example showed you can see that the function has some annotations, like GeMapping or Pathvariable.. This code is given by the framework, but we can consider that it is part of the context of the function so an unit test would have to take a look at it too. 

Some would say that it is an integration test, but it is important to see that all the dependencies like the service is mocked, so you stay in your code context. That's why in the responsabilities of the code you see input validation, deserialization, exception handling or listening to http requests.


## 1. Verifying HTTP Request Matching

A controller has to respond to a Http request. It is the public face of an api and its biggest responsibility is to receive the demand from the client, call others layers to process the request and return a satisfactory answer or an understandable error to the client.

