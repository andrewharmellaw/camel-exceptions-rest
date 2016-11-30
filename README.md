# camel-exceptions-rest
Extensions to camel-exceptions-core providing additional Exceptions for REST-wrangling problems.

Releases are hosted over at [bintray](https://bintray.com/capgeminiuk/maven/camel-exceptions-rest/view) licenced under Apache v.2.0.

![Build Status](https://travis-ci.org/andrewharmellaw/camel-exceptions-rest.svg?branch=master)

It's recommended when you're handling exceptions in Apache Camel that you differentiate between recoverable and irrecoverable exceptions.  These classes take the representations of these two types of exception (from [camel-exceptions-core](https://github.com/andrewharmellaw/camel-exceptions-core)) and create rest-specific subclasses.  

They are very simple, extending the relevant Recoverable or IrrecoverableException, and providing a meaningful classname.  Nothing beyond that.  

Isn't it nice when someone does a bunch of your typing for you?
