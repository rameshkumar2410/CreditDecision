package com.db.creditdecision;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.db.creditdecision.bo.ApplicantDetails;
import com.db.creditdecision.bo.CreditScore;
import com.db.creditdecision.controller.CreditDecisionController;
import com.db.creditdecision.validator.CreditDecisionValidator;

/**
 * 
 * @author Ramesh
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class CreditDecisionControllerTest {

	@InjectMocks
	CreditDecisionController creditDecisionController;
	
	@Mock
	CreditDecisionValidator creditDecisionValidator;
	
	@Mock
	RestTemplate restTemplate;
	
	@Value("${app.db.api.getCreditScoreURL}")
	private String getCreditScoreURL;

	/**
	 * Mock test for eligibleForLoanAmount
	 */
	@Test
	public void eligibleForLoanAmount() {
		try {
			ApplicantDetails applicantDetails = new ApplicantDetails();
			applicantDetails.setSsnNumber(11233);
			applicantDetails.setLoanAmount(25000);
			applicantDetails.setCurrentAnnualIncome(900005);
			CreditScore creditscore = new CreditScore();
			creditscore.setCreditScore(900);
			when(creditDecisionValidator.validateApplicantDetails(applicantDetails)).thenCallRealMethod();
			when(creditDecisionValidator.validateLoanSanctionHistory(applicantDetails)).thenReturn(false);
			String uri = getCreditScoreURL + applicantDetails.getSsnNumber();
			when(restTemplate.getForObject(uri, CreditScore.class)).thenReturn(creditscore);
			ResponseEntity<?> result = creditDecisionController.calculateLoanAmount(applicantDetails);
			assertEquals(result.getStatusCode(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * Mock test for notEligibleForLoanAmount
	 */
	@Test
	public void notEligibleForLoanAmount() {
		try {
			ApplicantDetails applicantDetails = new ApplicantDetails();
			applicantDetails.setSsnNumber(11234);
			applicantDetails.setLoanAmount(50000);
			applicantDetails.setCurrentAnnualIncome(100000);
			CreditScore creditscore = new CreditScore();
			creditscore.setCreditScore(400);
			when(creditDecisionValidator.validateApplicantDetails(applicantDetails)).thenCallRealMethod();
			when(creditDecisionValidator.validateLoanSanctionHistory(applicantDetails)).thenReturn(false);
			String uri = getCreditScoreURL + applicantDetails.getSsnNumber();
			when(restTemplate.getForObject(uri, CreditScore.class)).thenReturn(creditscore);
			ResponseEntity<?> result = creditDecisionController.calculateLoanAmount(applicantDetails);
			assertEquals(result.getStatusCode(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * Mock test for invalidSSNNumber
	 */
	@Test
	public void invalidSSNNumber() {
		try {
			ApplicantDetails applicantDetails = new ApplicantDetails();
			applicantDetails.setSsnNumber(11247);
			applicantDetails.setLoanAmount(25000);
			applicantDetails.setCurrentAnnualIncome(900005);
			CreditScore creditscore = new CreditScore();
			creditscore.setCreditScore(400);
			when(creditDecisionValidator.validateApplicantDetails(applicantDetails)).thenCallRealMethod();
			when(creditDecisionValidator.validateLoanSanctionHistory(applicantDetails)).thenReturn(false);
			String uri = getCreditScoreURL + applicantDetails.getSsnNumber();
			when(restTemplate.getForObject(uri, CreditScore.class)).thenReturn(null);
			ResponseEntity<?> result = creditDecisionController.calculateLoanAmount(applicantDetails);
			assertEquals(result.getStatusCode(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * Mock test for loanSanctionedAlready
	 */
	@Test
	public void loanSanctionedAlready() {
		try {
			ApplicantDetails applicantDetails = new ApplicantDetails();
			applicantDetails.setSsnNumber(11235);
			applicantDetails.setLoanAmount(25000);
			applicantDetails.setCurrentAnnualIncome(700005);
			when(creditDecisionValidator.validateApplicantDetails(applicantDetails)).thenCallRealMethod();
			when(creditDecisionValidator.validateLoanSanctionHistory(applicantDetails)).thenReturn(true);
			ResponseEntity<?> result = creditDecisionController.calculateLoanAmount(applicantDetails);
			assertEquals(result.getStatusCode(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
