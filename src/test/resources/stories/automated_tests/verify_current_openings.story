Meta:
@websiteTesting

Narrative:
In order to apply for jobs at InTouch
As a user
I want to be able to see current openings at InTouch


Scenario: Verify current openings at InTouch
Meta:
@websiteTesting1
@tagValue Test-1
Given the user is in home page
When the user look for careers under about us menu
Then the user should see current job openings
When the user looks for position '<position>'
Then the user should job description for the '<position>'
Examples:
General.table