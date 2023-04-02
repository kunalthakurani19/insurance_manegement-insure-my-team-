# insurance_manegement-insure-my-team-Insurance Management System
This is an Insurance Management System that allows users to manage clients, insurance policies, and claims.

Entities

Client
A client is a person who purchases an insurance policy from the company.
Each client has a unique ID, name, email address, and phone number.
Clients can purchase multiple insurance policies from the company.


Insurance Policy
An insurance policy is a contract between the insurance company and the client.
Each policy has a unique ID, a client ID that identifies the owner of the policy, a policy type, a start date, an end date, a premium amount, and a status.
The policy type can be one of the following: life, health, auto, or property.
The status can be one of the following: active, expired, or cancelled.


Claims
A claim is a request made by a client to the insurance company for compensation for a loss covered by the policy.
Each claim has a unique ID, a policy ID that identifies the policy the claim is associated with, a claim number, a description of the loss, a status, and a date of submission.
The status can be one of the following: submitted, in review, approved, or rejected.


Endpoints:

a. Clients:
i. GET /api/clients: Fetch all clients.
ii. GET /api/clients/{id}: Fetch a specific client by ID.
iii. POST /api/clients: Create a new client.
iv. PUT /api/clients/{id}: Update a client's information.
v. DELETE /api/clients/{id}: Delete a client.

b. Insurance Policies:
i. GET /api/policies: Fetch all insurance policies.
ii. GET /api/policies/{id}: Fetch a specific insurance policy by ID.
iii. POST /api/policies: Create a new insurance policy.
iv. PUT /api/policies/{id}: Update an insurance policy.
v. DELETE /api/policies/{id}: Delete an insurance policy.

c. Claims:
i. GET /api/claims: Fetch all claims.
ii. GET /api/claims/{id}: Fetch a specific claim by ID.
iii. POST /api/claims: Create a new claim.
iv. PUT /api/claims/{id}: Update a claim's information.
v. DELETE /api/claims/{id}: Delete a claim.


Technologies Used
Java
Spring Boot
MySQL
Hibernate
