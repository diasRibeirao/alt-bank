package com.altbank.blackjack;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import com.altbank.blackjack.service.BlackJackServiceTest;
import com.altbank.blackjack.repository.JogoRepositoryTest;

@Suite
@SelectClasses({ 
	BlackJackServiceTest.class, 
	JogoRepositoryTest.class 
})
public class ApplicationTests {

}