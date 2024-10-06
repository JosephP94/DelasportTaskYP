**Delasport Task Solution**

The goal of the solution was to create an automated test framework using Selenium and Cucumber BDD, and I'm happy to share the results!

**Task Breakdown**
Here’s what I had to do:
1. Navigate to https://luckybandit.club.test-delasport.com/en/sports.
2. Log in using these credentials:
- Username: tu_yosif
- Password: Pass112#
3. Close any modal pop-ups that show up after logging in.
4. Check if the balance in the header matches the response from the getMemberBalance request.

**Bonus Points**
I also made sure to follow the BDD (Behavior-Driven Development) process.

**Prerequisites**
To get this project running, you’ll need:

Latest versions of Chrome and Firefox: The script is built to work on these two browsers. If you want to switch the browser, just update the browser property in the POM.xml file.

**Project Structure**

Feature files are in: src/test/resources/features
Step definitions can be found in: src/test/java/stepdefinitions
