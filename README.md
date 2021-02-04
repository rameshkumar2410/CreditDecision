# Credit Decision Engine
This Project is to calculate the Loan amount eligibility from the Credit engine

Created CreditDecisionController - this controller helps to calculate the Loan amount eligibility for the applied SSN Number, Pay load should be passed in the response body as Json format.

CreditScoreController- This class will act as an External Service and we used credit score mock data for the response.

Loan Sanction amount will be calculated based on the Annual pay, if the requested Loan amount is less than Annay pay/2 we will be giving the requested amount, if its greater than the Annual pay/2 we are giving the eligible amount AnnualPay/2.

CreditDecisionControllerTest- This Mock test class will cover all the below mentioned payload cases.

Steps to execute: Download the project and Import as Maven project, Download corresponding dependency and Execute the project as Spring boot App and Use the below url in POSTMAN to perform the testing.

Local Host URL: http://localhost:8080/creditdecision/calculateLoanAmount/


Pay Load Structure :

Eligible Pay Load:

{
"ssnNumber": "11233",
"loanAmount": "600000", 
"currentAnnualIncome": "900005"
}

Not Eligible Pay Load due to credit score less:

{
"ssnNumber": "11234",
"loanAmount": "600000", 
"currentAnnualIncome": "900005"
}

Invalid SSN Number Payload:

{
"ssnNumber": "11733",
"loanAmount": "600000", 
"currentAnnualIncome": "900005"
}

Loan Already Sanctioned Payload:

{
"ssnNumber": "11235",
"loanAmount": "600000", 
"currentAnnualIncome": "900005"
}
