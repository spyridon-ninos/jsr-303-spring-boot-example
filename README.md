# jsr-303-spring-boot-example
Example demonstrating the validation capabilities of JSR-303 using spring boot

## Description
A basic controller is built, which accepts a request object
and returns a response. The request has three validated objects,
the id, the name and the type.

The name has a simple pattern matching constraint, whereas the
type has a custome validator annotation and implementation that
does the same job.

There are three tests: one happy path where no validation problems
exist, one that has an invalid name, and one that has invalid name
and type. In the two invalid cases, the validation exception is thrown
that is handled by the exception handler, which - in turn - populates
a response with errors (a more suitable solution would be to use some
sort of AOP to do this, but this is out of scope for this example).