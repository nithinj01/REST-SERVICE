# rest-service
Smaato assignment demo
● The service has one GET endpoint - /api/smaato/accept which is able to accept an integer id as a
mandatory query parameter and an optional string HTTP endpoint query parameter. It should return
String “ok” if there were no errors processing the request and “failed” in case of any errors.
● Every minute, the application should write the count of unique requests your application received in
that minute to a log file. Uniqueness of request is based on the id parameter provided.
● When the endpoint is provided, the service should fire an HTTP GET request to the provided endpoint
with count of unique requests in the current minute as a query parameter. Also log the HTTP status
code of the response.
If you would like to take this task even further you can build the following extensions:
Extension 1: Instead of firing an HTTP GET request to the endpoint, fire a POST request. The data structure
of the content can be freely decided.
Extension 2: Make sure the id deduplication works also when the service is behind a Load Balancer and 2
instances of your application get the same id simultaneousl
