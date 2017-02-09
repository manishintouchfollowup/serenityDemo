Meta:
@restApiTesting

Narrative:
In order to test functionality of api
As a user
I want to verify response from google location api



Scenario: Verify Location Api response for valid city
Meta:
@tagValue Test-2
Given the user access google location api to search city '<validCityName>'
Then the user should receive http code '200'
And the user should see the response contains searched city '<validCityName>'
Examples:
RestApi.table