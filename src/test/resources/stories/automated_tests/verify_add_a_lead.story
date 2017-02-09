Meta:
@websiteTesting

Narrative:
In order to improve the business
As a user
I want to be able to get new leads


Scenario: Verify new leads
Meta:
@websiteTesting1
@tagValue Test-1
Given the user is in login page
When the user enter the username '<usernameFullAdmin>'
And the user enter the password '<passwordPassword>'
And the user click on login
Then the user should be on home page
When the user add a lead
And the user add details of new lead
And the user save the details
Then the user should see Opt In preferences
Examples:
General.table