# Horus

Horus is named after the part-falcon god of writing from ancient Egypt. Horus is a web application that provides you with a call-in number for recording voice memos. After your voice memo is done, Horus contacts you with a link via SMS.

Horus is written in Clojure. Every feature is written using TDD. Clojure is a functional language, so in many cases, testing is easy. At the same time, the Clojure ecosystem is young, and finding the right techniques to apply TDD to a Clojure project is part of the challenge. Hopefully this project illustrates some effective techniques for practicing TDD in Clojure.

## Initial Setup

First, clone this repository.

Horus uses [Twilio](twilio.com) to captured recordings from incoming phone calls and to send SMS messages.

A Twilio account is required to run Horus for testing or development. Additionally, you'll need a Clojure development environment, including Leiningen.

To run Horus in development or test mode, you'll need to configure a handful of environment variables:

```
TWILIO_ACCOUNT
TWILIO_AUTH_TOKEN
TWILIO_NUMBER
```

You can do this by hand in bash, or by using the `lein-environ` plugin. To configure Horus using `lein-environ`, create a `profiles.clj` file containing the credentials you plan to use. An example file, `profiles.clj.example`, is included in this repository. Populate `profiles.clj` with your own Twilio credentials. These should be live credentials and phone number for `dev-env`,  and [test credentials](https://www.twilio.com/docs/api/rest/test-credentials) for `test-env`.

## Running the Dev Server

Once initial configuraiton values have been set, you can start the dev server using  Foreman.

```
% gem install foreman
% foreman start
```

## Running Specs

`% lein spec`

