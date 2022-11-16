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


