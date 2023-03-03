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
	DefaultKeyedValues2D nullValue = new DefaultKeyedValues2D();
	int[] TwoRowsSelector = new int[] {0, 1};
	
	
	@BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception { 
    	myValues.addValue(7.5, 0, 0);
    	myValues.addValue(2.5, 1, 0);
    	myValues.addValue(0.5, 2, 0);
    	myValues.addValue(1.0, 0, 1);
    	myValues.addValue(6.9, 1, 1);
    	myValues.addValue(4.2, 2, 1);
    	
    	//nullValue.addValue(null, null, null);
    	
    	keyedValues.addValue(0, (Number)5);
    	keyedValues.addValue(1, (Number)9);
    	keyedValues.addValue(2, (Number)2);
    	
    	
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
	 
	 @Test(expected = IllegalArgumentException.class)
	 public void mock_enterNullAs2DItem() {
		 DataUtilities.calculateColumnTotal(nullValue, 1);
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
	 
	 //Tests for Clone Method
	 @Test
	 public void cloneMethodValidData() {
		 double[][] testVals = new double[][] {{0, 1}, {2, 3}};
		 double[][] returnVals = DataUtilities.clone(testVals);
		 assertArrayEquals("Clone method returned " + "when cloning", returnVals, testVals);
	 }
	 
	 @Test
	 public void cloneMethodNullInput() {
		 double[][] testVals = new double[][] {null, {2, 3}};
		 double[][] returnVals = DataUtilities.clone(testVals);
		 assertArrayEquals("Clone method returned " + "when cloning", returnVals, testVals);
	 }
	 
	 //Tests for calculateColumnTotal with validRows argument
	 
	 @Test
	 public void mockcalculateColumnTotalForThreeValuesAndTwoRowsValidRows() {
		 double result = DataUtilities.calculateColumnTotal(myValues, 0, TwoRowsSelector);
	     assertEquals("the total of the values in the first "
	     		+ " column of the 2D table should be 10.5",
	    		 result, 10.5, .000000001d);}
	 
	 @Test
	 public void mock_tryToUseNegativeIndexForTheColumnValidRows() {
			 DataUtilities.calculateColumnTotal(myValues, -1, TwoRowsSelector);
	 }
	 
	 @Test(expected = NullPointerException.class)
	 public void mock_tryToUseLargerThanPossibleIndexForTheColumnValidRows() {
		 DataUtilities.calculateColumnTotal(myValues, 12, TwoRowsSelector);
	 }
	 
	 @Test(expected = IllegalArgumentException.class)
	 public void mock_enterNullAs2DItemValidRows() {
		 DataUtilities.calculateColumnTotal(nullValue, 1, TwoRowsSelector);
	 }
	 
	 //Test for calculateRowTotal with Valid Cols
	 
	 @Test
	 public void mock_calculateRowTotalForTwoValuesValidCols() {
		 double result = DataUtilities.calculateRowTotal(myValues, 1, TwoRowsSelector);
		 assertEquals("the total of the values in the second "
		     		+ "of 3 column of the 2D table should be 9.4",
		    		 9.4, result, .000000001d);}
	 
	 @Test(expected = NullPointerException.class)
	 public void mock_tryToUseNegativeIndexForTheRowIndexValidCols() {
	
		 DataUtilities.calculateRowTotal(myValues, -1, TwoRowsSelector);
	 }

	 @Test(expected = NullPointerException.class)
	 public void mock_tryToUseLargerThanPossibleIndexForTheRowValidCols() {
		 DataUtilities.calculateColumnTotal(myValues, 12, TwoRowsSelector);
	 }
	 
	 //Equals Method Test
	 double[][] testVals = new double[][] {{1, 5}, {2, 3}};
	 
	 @Test
	 public void equalsTestValidData() {
		 assertTrue("Did not return true when duplicate data was sent", DataUtilities.equal(testVals, testVals));
	 }
	 
	 @Test
	 public void equalsTestFirstNull() {
		 assertFalse("Returned true when first array was null and second was valid", DataUtilities.equal(null,  testVals));
	 }
	 
	 @Test
	 public void equalsTestSecondNull() {
		 assertFalse("Returns true when first array was valid and second was null", DataUtilities.equal(testVals, null));
	 }
	 
	 @Test
	 public void equalsTestBothNull() {
		 assertTrue("Does not return true when both input arrays were null", DataUtilities.equal(null,  null));
	 }
	 
	 @Test
	 public void equalsTestNotSame() {
		 double[][] testVals2 = new double[][] {{1, 5}, {2, 7}};
		 assertFalse("Returns true when two different arrays were input", DataUtilities.equal(testVals, testVals2));
	 }
	 
	 @Test
	 public void equalsTestDifferentLengths() {
		 double[][] testVals2 = new double[][] {{1, 5}, {2, 7}, {5, 8}};
		 assertFalse("Returns true when two different arrays were input", DataUtilities.equal(testVals, testVals2));
	 }
	 
	
	 
	 
    //----------------------------------------------------------
    // Testing the Cumulative Percentages method
    //----------------------------------------------------------
	 
	 
	 @Test
	 public void testCumulativePercentagesValidData() {
		 DataUtilities.getCumulativePercentages(keyedValues);
	 }
	@After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

}
