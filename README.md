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
<h4>2)Use proper HTTP headers for serialization formats.</h4>
Both client and server, need to know which format is used for the communication. The format has to be specified in the HTTP-Header.<br>
<ul>
   <li>Content-Type defines the request format.</li>
   <li>Accept defines a list of acceptable response formats.</li>
</ul>
<h4>3)GET method and query parameters should not alter the state.</h4>
Use PUT, POST and DELETE methods instead of the GET method to alter the state. Do not use GET for state changes. GET should be idempotent. (Idempotent-property of certain operations whereby they can be applied multiple times without changing the result beyond the initial application.)

<h4>4)Use sub-resources for relations.</h4>
If a resource is related to another resource using subresources.
<ul>
   <li>GET /orders/49/products/ Returns a list of products for order 49.</li>
   <li>GET /orders/49/products/2 Returns product id 2 for order 49.</li>
</ul>

<h4>5)Use proper HTTP methods (verbs).</h4>
HTTP methods used by most RESTful web APIs are:<br>
<ul>
   <li>GET - retrieves a representation of the resource at the specified URI. The body of the response message contains the details of the requested resource.</li>
   <li>POST creates a new resource at the specified URI. The body of the request message provides the details of the new resource. Note that POST can also be used to trigger operations that don't actually create resources.</li>
   <li>PUT either creates or replaces the resource at the specified URI. The body of the request message specifies the resource to be created or updated.</li>
   <li>PATCH performs a partial update of a resource. The request body specifies the set of changes to apply to the resource.</li>
   <li>DELETE removes the resource at the specified URI.</li>
</ul>



