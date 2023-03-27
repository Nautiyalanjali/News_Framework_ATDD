Feature: Validating a Guardian news article 


Scenario: Validating a Guardian news article with similar information on Google

Given a news article from https://www.theguardian.com/tone/news
When I search for the article on Google
Then I should see at least two other news articles with similar information And the Guardian news article should be considered valid

Scenario: Invalidating a Guardian news article without similar information on Google

Given a news article from https://www.theguardian.com/tone/news
When I search for the article on Google
Then I should not see any other news articles with similar information And the Guardian news article should be considered invalid

Scenario: Validating a Guardian news article with similar information on other site

Given a news article from https://www.theguardian.com/tone/news
When I search for the article on https://theprint.in/
Then I see at least two other news articles with similar information in The Print And the Guardian news article should be considered valid

Scenario: Invalidating a Guardian news article without similar information on other site

Given a news article from https://www.theguardian.com/tone/news
When I search for the article on https://theprint.in/
Then I should not seeany other news articles with similar information in the print And the Guardian news article should be considered invalid




