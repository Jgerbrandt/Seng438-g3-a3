package org.jfree.data;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataUtilitiesTest {
	
	DefaultKeyedValues2D myValues = new DefaultKeyedValues2D();
	DefaultKeyedValues keyedValues = new DefaultKeyedValues();
	
	
	@BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception { 
    	
    }
	

	 
	 @Test
	 public void mockcalculateColumnTotalForThreeValuesAndTwoRows() {
		 double result = DataUtilities.calculateColumnTotal(myValues, 0);
	     assertEquals("the total of the values in the first "
	     		+ " column of the 2D table should be 10.5",
	    		 result, 10.5, .000000001d);}
	 
	 @Test
	 public void mock_tryToUseNegativeIndexForTheColumn() {
			 DataUtilities.calculateColumnTotal(myValues, -1);
	 }
	 
	 @Test(expected = NullPointerException.class)
	 public void mock_tryToUseLargerThanPossibleIndexForTheColumn() {
		 DataUtilities.calculateColumnTotal(myValues, 12);
	 }
	 
    //----------------------------------------------------------
    // Testing the row total method
    //----------------------------------------------------------
	 
	 @Test
	 public void mock_calculateRowTotalForTwoValues() {
		 double result = DataUtilities.calculateRowTotal(myValues, 1);
		 assertEquals("the total of the values in the second "
		     		+ "of 3 column of the 2D table should be 9.4",
		    		 9.4, result, .000000001d);}
	 
	 @Test(expected = NullPointerException.class)
	 public void mock_tryToUseNegativeIndexForTheRowIndex() {
	
		 DataUtilities.calculateRowTotal(myValues, -1);
	 }

	 @Test(expected = NullPointerException.class)
	 public void mock_tryToUseLargerThanPossibleIndexForTheRow() {
		 DataUtilities.calculateColumnTotal(myValues, 12);
	 }

	 
    //----------------------------------------------------------
    // Testing Create Number Array method
    //----------------------------------------------------------
	 
	 @Test
	 public void createNumberArrayFromDoubleArray() {
		 
		 double[] dblArr = new double[] {12.0, 10.0, 3.0};
		 
		 Number[] expNmbrArr = new Number[] {12.0, 10.0, 3.0};
		 
		 //Error: create number array seemingly drops the last value and returns null for it
		 Number[] actNmbrArr = DataUtilities.createNumberArray(dblArr);
		 
		 assertArrayEquals("The array created by the createNumberArray should be"
		 		+ "the same as the expect array",expNmbrArr, actNmbrArr);
	 }
	 
	 @Test
	 public void createNumberArrayWithEmptyArray() {
		 
		 double[] dblArr = new double[] {};
		 
		 Number[] expNmbrArr = new Number[] {};
		
		 Number[] actNmbrArr = DataUtilities.createNumberArray(dblArr);
		 
		 assertArrayEquals("The array created by the createNumberArray should be"
		 		+ "an empty Number array",expNmbrArr, actNmbrArr);
	 }
	 
	 @Test(expected = NullPointerException.class)
	 public void checkThatNoExceptionIsThrownForArrayWithZeroWithCreateNumberArray() {
		 double[] testArr = new double[] {11,0};
		 DataUtilities.createNumberArray(testArr);

	 }
	 
	 @Test(expected = NullPointerException.class)
	 public void checkThatNoExceptionIsThrownForArrayCreatedWithNegativeNum() {
		 double[] testArr = new double[] {-11,-4.0};
		 DataUtilities.createNumberArray(testArr);
	 }
	 
	 
    //----------------------------------------------------------
    // Testing Create 2D Number Array Method
    //----------------------------------------------------------
	 
	 @Test
	 public void createNumber2DFromDoubleMatrix() {
		 double[][] dblMat = new double[][] {{12.0, 10.0, 3.0},{01,3.23,12}};
		 
		 Number[][] expNmbrMat = new Number[][] {{12.0, 10.0, 3.0},{01,3.23,12}};
		 
		 //Error: create number matrix seemingly drops the last value of the first 
		 //array and returns null for it instead
		 Number[][] actNmbrMat = DataUtilities.createNumberArray2D(dblMat);
		 
		 assertArrayEquals("The Matrix created by the createNumberArray2D should be"
		 		+ "the same as the expect matrix",expNmbrMat, actNmbrMat);}
	 
	 @Test
	 public void createNumberArray2DWithEmptyMainArray() {
		 
		 double[][] dblMat = new double[][] {};
		 
		 Number[][] expNmbrMat = new Number[][] {};
		 
		 Number[][] actNmbrMat = DataUtilities.createNumberArray2D(dblMat);
		 
		 assertArrayEquals("The Matrix created by the createNumberArray2D should be"
		 		+ "the same as the expect matrix",expNmbrMat, actNmbrMat);
	 }
	 
	 @Test
	 public void createNumberArray2DWithEmptySubArray() {
		 
		 double[][] dblMat = new double[][] {{}};
		 
		 Number[][] expNmbrMat = new Number[][] {{}};
		 
		 Number[][] actNmbrMat = DataUtilities.createNumberArray2D(dblMat);
		 
		 assertArrayEquals("The Matrix created by the createNumberArray2D should be"
		 		+ "the same as the expect matrix",expNmbrMat, actNmbrMat);
	 }
	 
	 @Test
	 public void createNumberArray2DWithDifferentlySizedSubArrays() {
		 
		 double[][] dblMat = new double[][] {{1},{1,2}};
		 
		 Number[][] expNmbrMat = new Number[][] {{1},{1,2}};
		 
		 Number[][] actNmbrMat = DataUtilities.createNumberArray2D(dblMat);
		 
		 assertArrayEquals("The Matrix created by the createNumberArray2D should be"
		 		+ "the same as the expect matrix",expNmbrMat, actNmbrMat);}
	 
	 @Test(expected = NullPointerException.class)
	 public void checkThatNoExceptionIsThrownForArrayWithZeroWithCreateNumberArray2D() {
		 double[][] dblMat = new double[][] {{0},{1,2}};
		 DataUtilities.createNumberArray2D(dblMat);
	 }
	 
	 @Test(expected = NullPointerException.class)
	 public void checkThatNoExceptionIsThrownForArrayCreatedWithNegativeNumCreateNumberArray2D() {
		 double[][] dblMat = new double[][] {{-12},{-2}};
		 DataUtilities.createNumberArray2D(dblMat);
	 }
	 
	 
    //----------------------------------------------------------
    // Testing the Cumulative Percentages method
    //----------------------------------------------------------
	 
	@After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

}
