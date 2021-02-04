package com.db.creditdecision.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.db.creditdecision.bo.CreditScore;

/**
 * This class provide Mock data for credit score
 * 
 * @author Ramesh
 *
 */
@Service
public class CreditScoreService {

	/**
	 * Mock Data for the Credit Score
	 * 
	 * @return creditScoreList
	 */
	public List<CreditScore> getCreditScoreData() {
		List<CreditScore> creditScoreList = Arrays.asList(new CreditScore(11231, 200), new CreditScore(11232, 800),
				new CreditScore(11233, 900), new CreditScore(11234, 400), new CreditScore(11235, 700),
				new CreditScore(11223, 900), new CreditScore(11224, 400), new CreditScore(11225, 700),
				new CreditScore(11236, 200), new CreditScore(11237, 500), new CreditScore(11238, 100),
				new CreditScore(11230, 1000)

		);

		return creditScoreList;
	}

	/**
	 * Mock Data of loan sanctioned 30 days for SSN number - Created this instead of
	 * having Database calls -- We can do by presisting the LoanSanctionDetails in a
	 * Separate table and compare those data with the incoming SSN to do the
	 * validation of Applied or Not Applied
	 * 
	 * @return
	 */
	public List<Integer> getLoanSanctionedSSNNumber() {
		List<Integer> loanSanctionedSSNList = Arrays.asList(11235, 11230, 11223);
		return loanSanctionedSSNList;
	}

}
