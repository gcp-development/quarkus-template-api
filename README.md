##Restful API design guidelines

Context

Define a common set of guidelines for Europe for Restful API design.

1) Use nouns for resource identification.
   For an easy understanding we should use this structure for every resource:
   GET - /orders - Returns a list of orders.
   GET - orders/59 - Returns a specific order, with the id 59.
   POST - /orders - Create a new order.
   PUT - /orders/59 - Updates a specific order, with the id 59.
   DELETE - /orders/59 - Deletes a specific order, with the id 59.

