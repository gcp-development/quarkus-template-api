# Quarkus Template Rest API

## Motivation

[Microservices](https://martinfowler.com/articles/microservices.html) plays an essential part of my work since 2008. Together with [Kubernetes](https://kubernetes.io/docs/home/) (around 2014) , it’s possible to scale each service independently for granular control of workload performance. Though the Microservice Architecture and Kubernetes we have a significant number of native capacity-scaling approaches, that allows us to address complex problems in a very cost effective way. 
The Kubernetes landscape is not completed without [Anthos](https://cloud.google.com/anthos/docs/concepts/overview), due the fact that is currently(2022/11) the only product that manages multiple clusters, which makes it the cherry on top of the cake.(Obviously these scenarios with Anthos should be considered only for organizations of a certain dimension or complex Kubernetes workloads across different clouds providers.)

When developing an API strategy, one of the key rules is <b>start small think big</b>. Meaning we normally start with a couple of Rest APIs with few users and after one year we have an "non planned" API marketplace (API marketplace is a user-friendly public hub where API providers can publish APIs for developers and partners to consume).

Knowing how to use Kubernetes and Microservice Architecture we are able in a controlled and cost effective way scale-up/down, to match our business demand without breaking the piggy bank. With this type of approach we can planned a [series funding round](https://www.seedready.org/resources/startup-funding-beginners-guide/) based in a flexible digital landscape at a reasonable cost.

## Table of Contents<br>
<ul>
<li><a href="https://github.com/gcp-development/quarkus-template-api/blob/main/README.md#quarkus" target="_self">Quarkus</a></li>
<li><a href="https://github.com/gcp-development/quarkus-template-api/blob/main/README.md#proof-of-conceptrest-api" target="_self">Proof of Concept(Rest API)</a></li>
   <ul>
      <li><a href="https://github.com/gcp-development/quarkus-template-api/blob/main/README.md#source-code" target="_self">Source Code</a></li>
      <li><a href="https://github.com/gcp-development/quarkus-template-api/blob/main/README.md#docker-image" target="_self">Docker image</a></li>
      <li><a href="https://github.com/gcp-development/quarkus-template-api/blob/main/README.md#testing-the-rest-api-with-postman-and-intellij-community" target="_self">Testing the Rest API with Postman and Intellij Community</a></li>
   </ul>
<li><a href="https://github.com/gcp-development/quarkus-template-api/blob/main/README.md#restful-api-design-guidelines" target="_self">Restful API design guidelines</a></li>
</ul>
<hr>

## Quarkus

[What is Quarkus?](https://quarkus.io/about/)<br>
Quarkus is a Kubernetes-native Java framework tailored for [GraalVM](https://www.graalvm.org/) and [HotSpot](https://wiki.openjdk.org/display/HotSpot), crafted from best-of-breed Java libraries and standards.

Why Quarkus?
<ul>
<li>Quarkus was designed around <a href="https://quarkus.io/kubernetes-native/" target="_blank">Kubernetes-native</a> philosophies, optimizing for low memory usage and fast startup times.</li>
<li>Quarkus, at its core, is based on a fully reactive and non-blocking architecture powered by <a href="https://vertx.io/" target="_blank">Eclipse Vert.x</a>.</li>
</ul>

Essential Guides:<br>
<ul>
   <li><a href="https://quarkus.io/guides/getting-started-testing" target="_blank">Testing Your Application</a></li>
   <li><a href="https://quarkus.io/guides/deploying-to-kubernetes" target="_blank">Deploying to kubernetes</a></li>
   <li><a href="https://quarkus.io/guides/building-native-image" target="_blank">Building an application into a native image</a></li>
   <li><a href="https://quarkus.io/guides/kafka-dev-services" target="_blank">Kafka dev services</a></li>
   <li><a href="https://quarkus.io/guides/datasource#dev-services-configuration-free-databases" target="_blank">Dev Services for databases</a></li>
   <li><a href="https://quarkus.io/guides/redis-dev-services" target="_blank">Redis dev services</a></li>
   <li><a href="https://quarkus.io/guides/amqp-dev-services" target="_blank">AMQP dev services</a></li>
   <li><a href="https://quarkus.io/guides/security-openid-connect-dev-services" target="_blank">Dev Services for OpenID Connect</a></li>
</ul>

<hr>

## Proof of Concept(Rest API)

#### Source Code

This proof of concept(PoC) developed creates an Rest API associated to an [container](https://kubernetes.io/docs/reference/glossary/?fundamental=true#term-container) to be deployed to a Kubernetes [cluster](https://kubernetes.io/docs/reference/glossary/?fundamental=true#term-cluster). This code was develop using [Intellij Community](https://www.jetbrains.com/idea/download/#section=linux) with the [quarkus-tools](https://plugins.jetbrains.com/plugin/13234-quarkus-tools) plugin in a [Ubuntu 22.04.1 LTS (Jammy Jellyfish)](https://releases.ubuntu.com/22.04/).

![image](https://user-images.githubusercontent.com/76512851/203339810-c1aaca7b-c54d-4e2a-87c9-eef016d42d12.png)

#### Execute the project

Execute the project and right-click anywhere inside of the the maven run tile.

![image](https://user-images.githubusercontent.com/76512851/203345483-4e655ec7-b310-482e-a8d6-186a951cda5d.png)


#### Open the Application

![image](https://user-images.githubusercontent.com/76512851/203411672-6b9eae1d-2712-420b-a8a5-45423ebc55e7.png)

#### OpenAPI specification

```bash
{
  "openapi" : "3.0.3",
  "info" : {
    "title" : "quarkus-template-api API",
    "version" : "1.0.0-SNAPSHOT"
  },
  "paths" : {
    "/books" : {
      "get" : {
        "tags" : [ "Book Resource" ],
        "responses" : {
          "200" : {
            "description" : "OK"
          }
        }
      },
      "post" : {
        "tags" : [ "Book Resource" ],
        "requestBody" : {
          "content" : {
            "application/json" : {
              "schema" : {
                "$ref" : "#/components/schemas/Book"
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "OK"
          }
        }
      },
      "delete" : {
        "tags" : [ "Book Resource" ],
        "responses" : {
          "200" : {
            "description" : "OK"
          }
        }
      }
    },
    "/books/bulk" : {
      "post" : {
        "tags" : [ "Book Resource" ],
        "requestBody" : {
          "content" : {
            "application/json" : {
              "schema" : {
                "type" : "array",
                "items" : {
                  "$ref" : "#/components/schemas/Book"
                }
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "OK"
          }
        }
      }
    },
    "/books/enhancedmediatype" : {
      "get" : {
        "tags" : [ "Book Resource" ],
        "responses" : {
          "200" : {
            "description" : "OK"
          }
        }
      }
    },
    "/books/{key}" : {
      "get" : {
        "tags" : [ "Book Resource" ],
        "parameters" : [ {
          "name" : "key",
          "in" : "path",
          "required" : true,
          "schema" : {
            "format" : "int32",
            "type" : "integer"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "OK"
          }
        }
      },
      "put" : {
        "tags" : [ "Book Resource" ],
        "parameters" : [ {
          "name" : "key",
          "in" : "path",
          "required" : true,
          "schema" : {
            "format" : "int32",
            "type" : "integer"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json" : {
              "schema" : {
                "$ref" : "#/components/schemas/Book"
              }
            }
          }
        },
        "responses" : {
          "200" : {
            "description" : "OK"
          }
        }
      },
      "delete" : {
        "tags" : [ "Book Resource" ],
        "parameters" : [ {
          "name" : "key",
          "in" : "path",
          "required" : true,
          "schema" : {
            "format" : "int32",
            "type" : "integer"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "OK"
          }
        }
      }
    }
  },
  "components" : {
    "schemas" : {
      "Book" : {
        "type" : "object",
        "properties" : {
          "id" : {
            "format" : "int32",
            "type" : "integer"
          },
          "title" : {
            "type" : "string"
          },
          "author" : {
            "type" : "string"
          }
        }
      }
    }
  }
}
```

#### Open the Dev UI

This screen will give us an overview of our Rest API developed.

![image](https://user-images.githubusercontent.com/76512851/203379033-261e1f67-8e4c-4420-88f3-012f1f36a64d.png)

For instance verify the unit tests result.

![image](https://user-images.githubusercontent.com/76512851/203348320-0d9963bd-5e3a-4b6e-85b1-9863f877d768.png)

Test Results

![image](https://user-images.githubusercontent.com/76512851/203348604-f52e2e19-6148-4aea-8218-226dde00ee1b.png)

For instance open the swagger-ui.

![image](https://user-images.githubusercontent.com/76512851/203380172-f02f0510-c03f-417b-bc3c-601b8d333a19.png)

Swagger-ui

![image](https://user-images.githubusercontent.com/76512851/203380391-11b0dbfe-7300-4600-a059-d381ff674d49.png)

Try it out

![image](https://user-images.githubusercontent.com/76512851/203405128-08f29d61-9d31-481f-b768-f8f452c69a50.png)

<hr>

#### Docker Image

Quarkus adopts the container first approach. This approach is optimized for low memory usage and fast startup times in areas like build time metadata processing and reduction in reflection usage. 

<b>Quarkus application load steps.</b>

![image](https://user-images.githubusercontent.com/76512851/203414467-6da0897d-2f36-4307-a0dc-17928bd8ec56.png)

Note:Image from Quarkus: [The Black Swan of Java?](https://www.jug.ch/events/slides/200430_jugch_Quarkus_-_Black_Swan_of_Java.pdf)

[.dockerignore](https://docs.docker.com/engine/reference/builder/#dockerignore-file)
```bash
target
```

[Dockerfile](https://docs.docker.com/engine/reference/builder/)

```bash
FROM quay.io/quarkus/quarkus-micro-image:2.0
WORKDIR /work/
RUN chown 1001 /work \
    && chmod "g+rwX" /work \
    && chown 1001:root /work
COPY --chown=1001:root target/*-runner /work/application

EXPOSE 8080
USER 1001

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
```
Note: [Parent-image](https://docs.docker.com/glossary/#parent-image):[quarkus-micro-image](https://quay.io/repository/quarkus/quarkus-micro-image?tab=tags&tag=2.0).


Build the docker image.

```bash
./mvnw package -Pnative
docker build -f src/main/docker/Dockerfile.native.dev -t quarkus-template-api:1.0 .
```

![image](https://user-images.githubusercontent.com/76512851/203319182-e96feeb8-1b35-4b97-91a1-9fb80ebee0e6.png)


Test the docker image.
```bash
docker run -i --rm -p 8080:8080 quarkus-template-api:1.0
```

![image](https://user-images.githubusercontent.com/76512851/203319623-0ee42cb4-99ae-4703-9dba-a0237392c9b6.png)

Push the image to [hub.docker](https://hub.docker.com/)

```bash
docker tag quarkus-template-api:1.0 {hub.docker}/quarkus-template-api:1.0
docker push {hub.docker}/quarkus-template-api:1.0
```

![image](https://user-images.githubusercontent.com/76512851/203326130-e2f5e0c0-a3e1-46b7-9c12-d02e771eb0a0.png)

<hr>

### Testing the Rest API with Postman and Intellij Community

This is a small test done with [Postman](https://www.postman.com/downloads/) and [Intellij Community](https://www.jetbrains.com/idea/download/#section=linux).

![image](https://user-images.githubusercontent.com/76512851/202681293-33dd0567-6c1b-4554-ac8c-d11f8c7fc9fd.png)

### Add one record.

URL address

```bash
http://localhost:8080/books
```

Content-Type Header
```bash
application/json
```

Accept Header
```bash
application/json
```

![image](https://user-images.githubusercontent.com/76512851/202450475-c15ea0b1-4174-4078-9e8a-ed36d63d8af1.png)

Body
```bash
{"id": 0,"title":"Moby Dick","author":"Herman Melville"}
```

![image](https://user-images.githubusercontent.com/76512851/202452756-8684d334-4d49-4a04-9431-c00a2bda3d99.png)

### Add records in bulk.

URL address

```bash
http://localhost:8080/books/bulk
```

Content-Type Header
```bash
application/json
```

Accept Header
```bash
application/json
```

Body
```bash
[
{"id": 0,"title":"Raven","author":"John Jacobs"},
{"id": 0,"title":"In Cold Blood","author":"Truman Capote"},
{"id": 0,"title":"Brave New World","author":"Aldous Huxley"},
{"id": 0,"title":"Nineteen Eighty-Four","author":"George Orwell"},
{"id": 0,"title":"The Art of War","author":"Sun-Tzu"}
]
```

![image](https://user-images.githubusercontent.com/76512851/202676095-49073b15-25d4-4270-a5f3-d233cadaabbb.png)

### Read all records.

URL address

```bash
http://localhost:8080/books
```

Content-Type Header
```bash
application/json
```

Accept Header
```bash
application/json
```

![image](https://user-images.githubusercontent.com/76512851/202724984-72780494-c3b8-4cf8-b363-81f3d634e4cb.png)


### Search for one record.

URL address

```bash
http://localhost:8080/books/2
```

Content-Type Header
```bash
application/json
```

Accept Header
```bash
application/json
```

![image](https://user-images.githubusercontent.com/76512851/202677031-05aad6dc-6f1b-4664-a763-ad754f0aa35c.png)

### Update a record.

URL address

```bash
http://localhost:8080/books/2
```

Content-Type Header
```bash
application/json
```

Accept Header
```bash
application/json
```

![image](https://user-images.githubusercontent.com/76512851/202678440-d58209a3-f796-49a3-8163-4727e7942c73.png)

### Delete a record.

URL address

```bash
http://localhost:8080/books/2
```

![image](https://user-images.githubusercontent.com/76512851/202679454-dccff9a9-59e0-4609-8b29-320d766de511.png)

### Delete all records.

URL address

```bash
http://localhost:8080/books/
```

![image](https://user-images.githubusercontent.com/76512851/202679874-0489a45c-8313-482c-80b6-1634cbf7f25c.png)

### API versioning.

URL address

```bash
http://localhost:8080/books/enhancedmediatype
```

Content-Type Header for version 1
```bash
application/org.microservice.api.v1.book+json;qs=0.5
```

Accept Header for version 1
```bash
application/org.microservice.api.v1.book+json;qs=0.5
```

![image](https://user-images.githubusercontent.com/76512851/202695072-6aeee6fa-233a-477d-b6bd-4c6caf070138.png)

URL address

```bash
http://localhost:8080/books/enhancedmediatype
```

Content-Type Header for version 2
```bash
application/org.microservice.api.v2.book+json;qs=0.9
```

Accept Header for version 2
```bash
application/org.microservice.api.v2.book+json;qs=0.9
```

![image](https://user-images.githubusercontent.com/76512851/202695995-662d87e1-df75-4662-9ab2-959e267dfcf9.png)

<hr>

## Restful API design guidelines

Design First

![image](https://user-images.githubusercontent.com/76512851/203150314-78105b67-7ae9-4cf8-a32e-0806b97611c4.png)

Design-First: This approach fundamentally means any API effort — whether one or many in a program — starts with a design process. In this model, APIs are defined in an iterative way that both humans and computers can understand — before any code is ever written. The goal is that every team speaks the same language, and every tool they use leverages the same API design. The crucial difference here compared to [others approaches](https://swagger.io/resources/articles/adopting-an-api-first-approach/) is that the design process is what ensures all stakeholders are involved, and their needs are satisfied in the creation.

<h4>1) Use nouns for resource identification.</h4>
   For an easy understanding we should use this structure for every resource:<br>
<ul>
   <li>GET - /orders - Returns a list of orders.</li>
   <li>GET - orders/59 - Returns a specific order, with the id 59.</li>
   <li>POST - /orders - Create a new order.</li>
   <li>PUT - /orders/59 - Updates a specific order, with the id 59.</li>
   <li>DELETE - /orders/59 - Deletes a specific order, with the id 59.</li>
</ul>
<h4>2) Use proper HTTP headers for serialization formats.</h4>
Both client and server, need to know which format is used for the communication. The format has to be specified in the HTTP-Header.<br>
<ul>
   <li>Content-Type defines the request format.</li>
   <li>Accept defines a list of acceptable response formats.</li>
</ul>
<h4>3) GET method and query parameters should not alter the state.</h4>
Use PUT, POST and DELETE methods instead of the GET method to alter the state. Do not use GET for state changes. GET should be idempotent. (Idempotent-property of certain operations whereby they can be applied multiple times without changing the result beyond the initial application.)

<h4>4) Use sub-resources for relations.</h4>
If a resource is related to another resource using subresources.
<ul>
   <li>GET - /orders/49/products/ Returns a list of products for order 49.</li>
   <li>GET - /orders/49/products/2 Returns product id 2 for order 49.</li>
</ul>
<h4>5) Use proper HTTP methods (verbs).</h4>
HTTP methods used by most RESTful web APIs are:<br>
<ul>
   <li>GET - retrieves a representation of the resource at the specified URI. The body of the response message contains the details of the requested resource.</li>
   <li>POST - creates a new resource at the specified URI. The body of the request message provides the details of the new resource. Note that POST can also be used to trigger operations that don't actually create resources.</li>
   <li>PUT - either creates or replaces the resource at the specified URI. The body of the request message specifies the resource to be created or updated.</li>
   <li>PATCH  - performs a partial update of a resource. The request body specifies the set of changes to apply to the resource.</li>
   <li>DELETE - removes the resource at the specified URI.</li>
</ul>
<h4>6) HTTP response status codes.</h4>
When the client raises a request to the server through an API, the client should know the feedback, whether it failed, passed or the request was wrong.
The following are the important categorization of HTTP codes:<br>
<ul>
   <li>2xx (Success category) These status codes represent that the requested action was received and successfully processed by the server.</li>
   <ul>
      <li>200 Ok The standard HTTP response representing success for GET, PUT or POST.</li>
      <li>201 Created This status code should be returned whenever the new instance is created. E.g on creating a new instance, using POST method, should always return 201 status code.</li>
      <li>204 No Content represents the request is successfully processed but has not returned any content.DELETE can be a good example of this.</li>
   </ul>
   <li>3xx (Redirection Category)</li>
   <ul>
      <li>304 Not Modified indicates that the client has the response already in its cache. And hence there is no need to transfer the same data again.</li>
   </ul>
   <li>4xx (Client Error Category) These status codes represent that the client has raised a faulty request.</li>
   <ul>
      <li>400 Bad Request indicates that the request by the client was not processed, as the server could not understand what the client is asking for.</li>
      <li>401 Unauthorized indicates that the client is not allowed to access resources, and should re-request with the required credentials.</li>
      <li>403 Forbidden indicates that the request is valid and the client is authenticated, but the client is not allowed access the page or resource for any reason.</li>
      <li>404 Not Found indicates that the requested resource is not available now.</li>
      <li>410 Gone indicates that the requested resource is no longer available which has been intentionally moved.</li>
   </ul>
   <li>5xx (Server Error Category)</li>
   <ul>
      <li>500 Internal Server Error indicates that the request is valid, but the server is totally confused and the server is asked to serve some unexpected condition.</li>
      <li>503 Service Unavailable indicates that the server is down or unavailable to receive and process the request. Mostly if the server is undergoing maintenance.</li>
   </ul>
 </ul>
 
<h4>7) Field name casing convention.</h4>

[camelCase](https://web.archive.org/web/20080411055228/http://www2.tech.purdue.edu/cit/Courses/CPT355/C_Sharp_Coding_Standards_and_Guidelines.asp) we need to make sure it is consistent across the application.

<h4>8) Searching, sorting,filtering and pagination.</h4>
All of these actions are simply the query on one dataset. There will be no new set of APIs to handle these actions. We need to append the query params with the GET method API.
<ul>
   <li> Sorting - In case, the client wants to get the sorted list of orders, the GET /ordersendpoint should accept multiple sort params in the query. E.g GET /orders?sort=rank_asc would sort the orders by its rank in ascending order.</li>
   <li>Filtering - For filtering the dataset, we can pass various options through query params. E.g GET /orders?category=urgent&location=spain would filter the orders list data with the order category of urgent and where the location is Spain.</li>
   <li>Searching - When searching for the orders name in orders list the API endpoint should be GET /orders?search=special.</li>
   <li>Pagination - When the dataset is too large, we divide the data set into smaller chunks, which helps in improving the performance and is easier to handle the response. Eg. GET /orders?page=10 means get the list of orders on 10th page.</li>
</ul>

<h4>9) Versioning.</h4>
The API Version is mandatory and we should not release an unversioned API. Use a simple ordinal number and avoid dot notation such as 1.2.
We are using the URL for the API versioning starting with the letter "v".

<h4>10) Provide link for navigating through your API(HATEOAS)</h4>
Hypermedia as the Engine of Application State is a principle that hypertext links should be used to create a better navigation through the API.
A hypermedia-driven site provides information to navigate the site’s REST interfaces dynamically by including hypermedia links with the responses.

Example:

```bash
{
    "id": "1",
    "name": "Coffee Pot",
    "links": [ {
        "rel": "self",
        "href": "http://localhost:8080/product/1"
    } ]
}
```

This response not only has the product’s name but includes the self-linking URL where that resource is located.
rel means relationship. In this case, it’s a self-referencing hyperlink. For example, an order might have a “rel”:”customer” relationship, linking the order to its customer.
href is a complete URL that uniquely defines the resource.

<h4>11) Handling ERROR JSON.</h4>
We should always return the error message in its own set of field. A JSON error body should provide a few things for the developer – a useful error message, a unique error code and possibly a detailed description.

Example:

```bash
{
   "code": 666,
   "message" : "java.lang.OutOfMemoryError: Java heap space",
   "description" : "You should use .net core.... :)"
}
```

<h4>12) How to create a Rest API URL</h4>
We should use the following formats for the URL.<br>
<ul>
   <li>https://{Domain name (:Port number)}/{A value indicating REST API}/{API version}/{path for identifying a resource}</li>
   <li>https://{Domain name indicating REST API(:Port number)}/{API version}/{path for identifying a resource}</li>
</ul>

Examples:

```bash
https://org.com/api/v1/orders/43
https://api.org.com/v1/orders/89
```

<hr>

References:<br>
[Richardson Maturity Model](https://martinfowler.com/articles/richardsonMaturityModel.html)<br>
[REST API Tutorial](https://restfulapi.net/)<br>
[Best Practices in API Design](https://swagger.io/blog/api-design/api-design-best-practices/)<br>
[Best Practices in API Documentation](https://swagger.io/blog/api-documentation/best-practices-in-api-documentation/)<br>
[Quarkus User Stories](https://quarkus.io/blog/tag/user-story/)<br>
[Dockerfile Best Practices](https://docs.docker.com/develop/develop-images/dockerfile_best-practices/)
[Swagger Docs](https://swagger.io/docs/)
