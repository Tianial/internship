package com.parcaune.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@SpringBootTest
class CalculatorTest {


	// creating an instance of the class
		Calculator underTest= new Calculator();
		@Test // tells us that itshouldAddTwoNumbers() is a test method
		void itshouldAddTwoNumbers(){

			//given
			int numberOne = 1;
			int numberTwo= 3;
			//when
			int result=underTest.add(numberOne,numberTwo);

			// Then
			int expectedValue= 4;
			assertThat(result).isEqualTo(expectedValue);

		}

		class Calculator {
			int add(int a, int b) {
				return a + b;
			}
		}
	}

