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
   <li><a href="https://quarkus.io/guides/building-native-image" target="_blank">Building an application into a native image</a></li>
   <li><a href="https://quarkus.io/guides/kafka-dev-services" target="_blank">Kafka dev services</a></li>
   <li><a href="https://quarkus.io/guides/datasource#dev-services-configuration-free-databases" target="_blank">Dev Services for databases</a></li>
   <li><a href="https://quarkus.io/guides/redis-dev-services" target="_blank">Redis dev services</a></li>
   <li><a href="https://quarkus.io/guides/amqp-dev-services" target="_blank">AMQP dev services</a></li>
   <li><a href="https://quarkus.io/guides/security-openid-connect-dev-services" target="_blank">Dev Services for OpenID Connect</a></li>
   <li><a href="https://quarkus.io/guides/deploying-to-kubernetes" target="_blank">Deploying to kubernetes</a></li>
</ul>
<hr>

## Restful API design guidelines

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
