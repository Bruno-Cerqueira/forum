# Kotlin Spring testing guide

Hi! If you want to have a guide for testing your application, that's my goal... I would love some help! :) I want to understand how to have a nice architecture, a good understanding of the methods applied,  and how to make testing our application a nice experience. This guide has the purpose of answering these questions. I am not an expert and if you feel that you can contribute or improve, you can follow the section about contributions. You are very welcome to help!

## What can I find

This project is made to explore all the testing strategies in all the layers of a typical project like controllers, services, repositories, etc. With this in mind we try to show the best way to cover the project with the appropriate tests.

## Approaches to cover your code with test

SpringBoot offers you with multiple annotations to be able to set up part of the project needed to be able to execute the test following a specified strategy. Usually we can approach the tests in 2 ways:

### Unit tests

Unit tests are made to test a code in its own context. Without being worried about effects outside its code.

<a href="">Annotations for unit testing in spring</a>

### Integration tests

Integration tests are made to test the behavior of combining different layers of the application, so here when you test the code you verify all or part of the context that you execute.

<a href="">Annotations for unit testing in spring</a>

## Testing Code in action

<a href="documents/CONTROLLER-TEST.md">Controller test guide</a>