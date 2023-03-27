###############################################################################
# User Story :                                                                #
# Description: Log into webapp with different valid/invalid cases             #
# Date       : 20/02/2023                                                     #
# Author     :  Anjali                                                        #
###############################################################################                                                                             #


#Feature: Application Login
#
  #Scenario: Log into application using invalid password
    #Given user is on login page
    #When user login into the application with below details
      #| username        |
      #| willissingapore |
    #Then Home page is not opened and incorrect username or password message is displayed
   
   

Feature: Multiselect drop down 

Scenario: Verifying the multiselect drop down values
Given :User is on Select QA  page
When user click on Multiselect Drop down then all below values should be there
      | Values|
      | Green|
      | Blue |
      | Black|
      | Red  |

