package org.jfree.data;

import static org.junit.Assert.*;

import java.beans.Transient;

import org.junit.*;

public class RangeTestNew {
    private Range exampleRange1;
    private Range exampleRange2;
    private Range exampleRange3;
    
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    	exampleRange1 = new Range(-1, 1);
    	exampleRange2 = new Range(0,100);
    	exampleRange3 = new Range(-50,-5);
    }

    //----------------------------------------------------------
    // Testing Constructor Mistake Expectation
    //----------------------------------------------------------
    
    @Test(expected = IllegalArgumentException.class)
    public void expectExpectationWithReverseBoundsOnConstructors() {
    	new Range(1, -1);
    }


    
    //----------------------------------------------------------
    // Testing For the Contains Method
    //----------------------------------------------------------
    
    @Test
    public void containsValueShouldBeTrue() {
    	assertTrue("The Range of 0 to 100 should contain the number 43", 
    			exampleRange2.contains(43));
    }
    
    @Test
    public void containsValueOver() {
    	assertFalse("The Range of -1 to 1 should not contain the number 43", 
    			exampleRange1.contains(43));
    }

    @Test
    public void containsValueUnder() {
    	assertFalse("The Range of -1 to 1 should not contain the number -43", 
    			exampleRange1.contains(-43));
    }
    
    @Test 
    public void containsValueUpperEdge() {
    	assertTrue("The Range of 0 to 100 should contain the number 100 - Edge case",
    			exampleRange2.contains(100));
    }
    
    @Test 
    public void containsValueLowerEdge() {
    	assertTrue("The Range of 0 to 100 should contain the lower bound 0",
    			exampleRange2.contains(0));
    }
    
    @Test
    public void containsNegValue() {
    	assertFalse("The Range of 0 to 100 shoud not contain the number -12",
    			exampleRange2.contains(-12));
    }
    
    @Test
    public void containtsOnNegRange() {
    	assertTrue("The range of -50 to -5 should contain the value -17", 
    			exampleRange3.contains(-17));
    }
    
    //----------------------------------------------------------
    // Testing For the Upper Bound Method
    //----------------------------------------------------------
    
    @Test
    public void returnedUpperBoundShouldBeOneHundered() {
    	assertEquals("The Upper bound for the range of 0 to 100 should be 100", 
    			100, exampleRange2.getUpperBound(), .000000001d);
    }
    
    @Test
    public void returnedUpperBoundShouldNotBeSix() {
    	assertFalse("The Upper bound for the range of 0 to 100 should be 100 not 6", 
    			6 == exampleRange2.getUpperBound());
    }
    
    @Test
    public void checkUpperBoundOnNegRange() {
    	assertEquals("The upper bound for the range of -50 to -5 should be -5",
    			-5, exampleRange3.getUpperBound(), .000000001d);
    }
    
    //----------------------------------------------------------
    // Testing For the Lower Bound Method
    //----------------------------------------------------------
    
    @Test
    public void returneLowerBoundShouldBeZero() {
    	assertEquals("The Lower bound for the range of 0 to 100 should be 0", 
    			0, exampleRange2.getLowerBound(), .000000001d);
    }
    
    @Test
    public void returnedLowerBoundShouldNotBeNegativeTwelve() {
    	assertFalse("The Lower bound for the range of 0 to 100 should be 0 not -12", 
    			-12 == exampleRange2.getLowerBound());
    }
    
    @Test 
    public void checkLowerBoundOnNegRange() {
    	assertEquals("The lower bound for the range of -50 to -5 should be -50",
    			-50, exampleRange3.getLowerBound(), .000000001d);
    }

    
    //----------------------------------------------------------
    // Testing For the Get Length Method
    //----------------------------------------------------------
    
    @Test
    public void theLengthValueShouldBeTwo() {
    	assertEquals("The length of the range of -1 to 1 should be 2", 
    			2.0, exampleRange1.getLength(), .000000001d);
    }
    
    @Test 
    public void lengthValueShouldNotBeFour() {
    	assertFalse("The length of the range of 0 to 100 should be 100 not 4",
    			4.0 == exampleRange2.getLength());
    }
    
    @Test 
    public void negativeRangeLength() {
    	assertTrue("The length of the range of -50 to -5 should be 45",
    			exampleRange3.getLength() == 45.0);
    }

    
    //----------------------------------------------------------
    // Testing the intersect method
    //----------------------------------------------------------
    
    @Test
    public void theRangeShouldNotIntersect() {
    	assertFalse("The range from -1 to 1 should not intersect with a lower bound of 4", 
    			exampleRange1.intersects(4, 14));
    }
    
    @Test
    public void theRangeShouldIntersectFullOverlap() {
    	assertTrue("The range from 0 to 100 should intersect with a lower bound of 4", 
    			exampleRange2.intersects(4, 14));
    }
    
    @Test
    public void theRangeShouldIntersectUpperOverlap() {
    	assertTrue("The range from 0 to 100 should intersect with a lower bound of 97", 
    			exampleRange2.intersects(97, 130));
    }

    @Test
    public void theRangeShouldIntersectLowerOverlap() {
    	assertTrue("The range from 0 to 100 should intersect with an upper bound of 4", 
    			exampleRange2.intersects(-20, 4));
    }

    //----------------------------------------------------------
    // Testing the intersect OVERLOADED method
    //----------------------------------------------------------
    
    @Test
    public void theRangeShouldIntersectWithOtherRangeObject() {
    	assertTrue("The range from 0 to 100 should intersect with a Range of -1 to 1", 
    			exampleRange2.intersects(exampleRange1));
    }
    
    @Test
    public void theRangeShouldNotIntersectWithOtherRangeObject() {
    	Range localTestRange = new Range(30,31);
    	assertFalse("The range from -1 to 1 should not intersect with a Range of 30 to 31", 
    			exampleRange1.intersects(localTestRange));}

    //----------------------------------------------------------
    // Testing For the Constrain Method
    //----------------------------------------------------------
    
    @Test
    public void theValueShouldBeInRangeAlreadyForConstrain() {
    	assertEquals("The value 0 should be closest to the value 0 in the range -1 to 1", 
    			exampleRange1.constrain(0), 0, 0.0000001d);}
    
    @Test
    public void theValueShouldBeTheLowerBoundForClosestNumberInRange() {
    	assertEquals("The value -1 should be closest to the value -5 in the range -1 to 1", 
    			exampleRange1.constrain(-5), -1, 0.0000001d);}
    
    @Test
    public void theValueShouldBeTheUpperBoundForClosestNumberInRange() {
    	assertEquals("The value 1 should be closest to the value 10 in the range -1 to 1", 
    			exampleRange1.constrain(10), 1, 0.0000001d);}
    
    //----------------------------------------------------------
    // Testing For the combine Method
    //----------------------------------------------------------
    
    @Test
    public void rangesShouldBeCombined() {
    	assertEquals("Returned range of the combine of -1 to 1 and 0 to 100 should be -1 to 100", 
    			Range.combine(exampleRange1, exampleRange2), new Range(-1, 100));}
    
    @Test
    public void firstRangeNullShouldReturnRangeTwo() {
    	assertEquals("Returned range of the combine of null and 0 to 100 should be 0 to 100", 
    			Range.combine(null, exampleRange2), new Range(0, 100));}
    
    @Test
    public void secondRangeNullShouldReturnRangeOne() {
    	assertEquals("Returned range of the combine of -1 to 1 and null should be -1 to 1", 
    			Range.combine(exampleRange1, null), new Range(-1, 1));}
    
    //----------------------------------------------------------
    // Testing For the toString Method
    //----------------------------------------------------------
    
    @Test
    public void toStringShouldBePrintAsExpected() {
    	assertEquals("The string for range -1 to 1 should display Range[-1.0,1.0]", 
    			exampleRange1.toString(),"Range[-1.0,1.0]");}
    
    //----------------------------------------------------------
    // Testing For the hashCode Method
    //----------------------------------------------------------
    
    @Test
    public void hashCodeTestShouldReturn() {
    	int result;
    	long temp;
        temp = Double.doubleToLongBits(-1.0);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(1.0);
        result = 29 * result + (int) (temp ^ (temp >>> 32));
    	assertEquals("HASH FOR range of -1 to 1 should be" + result, 
    			exampleRange1.hashCode(),result);}
    
    //----------------------------------------------------------
    // Testing For the getCentraValue Method
    //----------------------------------------------------------
    
    @Test
    public void centerValueShouldBe0() {
    	assertEquals("The center value for range -1 to 1 should 0", 
    			exampleRange1.getCentralValue(),0, 0.000001d);}
    
    @Test
    public void centerValueShouldBe0point5() {
    	assertEquals("The center value for range -1 to 2 should 0.5", 
    			(new Range(-1,2)).getCentralValue(),0.5, 0.000001d);}

    //----------------------------------------------------------
    // Testing For the isNaNRange Method
    //----------------------------------------------------------
    
    @Test
    public void rangeIsNan() {
    	assertTrue("The range from Double.NaN to Double.NaN is a NaN range", 
    			(new Range(Double.NaN,Double.NaN)).isNaNRange());}
    
    @Test
    public void rangeIsNotNan() {
    	assertFalse("The range from -1 to 1 is not a NaN range", 
    			exampleRange1.isNaNRange());}
    
    @Test
    public void upperRangeIsNan() {
    	assertFalse("The range from -1 to Double.NaN is not a NaN range", 
    			(new Range(-1,Double.NaN)).isNaNRange());}
    
    @Test
    public void lowerRangeIsNan() {
    	assertFalse("The range from NaN to 1 is not a NaN range", 
    			(new Range(Double.NaN,1)).isNaNRange());}
    
    //----------------------------------------------------------
    // Testing For the equals Method
    //----------------------------------------------------------
    
    @Test
    public void doubleObjectIsNotARangeObject() {
    	assertFalse("Double Object is Not a Range Object so function should return false", 
    			exampleRange1.equals(new Double(3)));}
    
    @Test
    public void lowerBoundOfRangesIsEqualButUpperIsNot() {
    	assertFalse("Ranges -1 to 1 and -1 to 3 are not equal", 
    			exampleRange1.equals(new Range(-1, 3)));}
    
    @Test
    public void upperBoundOfRangesIsEqualButLowerIsNot() {
    	assertFalse("Ranges -1 to 1 and -2 to 1 are not equal", 
    			exampleRange1.equals(new Range(-2, 1)));}
    
    @Test
    public void rangesAreEqual() {
    	assertTrue("Ranges -1 to 1 and -1 to 1 are equal", 
    			exampleRange1.equals(new Range(-1, 1)));}
    
    //----------------------------------------------------------
    // Testing For the combineIgnoringNaN Method 
    //----------------------------------------------------------
    
    @Test
    public void combiningTwoNullRangesReturnANull() {
    	assertEquals("null + null = null", 
    			Range.combineIgnoringNaN(null, null), null);}
    
    @Test
    public void combiningANullRangeAndANaNRangeReturnsANull() {
    	assertEquals("null + NaN = null", 
    			Range.combineIgnoringNaN(null, new Range(Double.NaN, Double.NaN)), null);}

    @Test
    public void combiningANaNRangeAndANullRangeReturnsANull() {
    	assertEquals("NaN + null = null", 
    			Range.combineIgnoringNaN(new Range(Double.NaN, Double.NaN), null), null);}
    
    @Test
    public void combiningANaNRangeAndANaNRangeReturnsANull() {
    	assertEquals("NaN + NaN = null", 
    			Range.combineIgnoringNaN(new Range(Double.NaN, Double.NaN),
    					new Range(Double.NaN, Double.NaN)), null);}
    
    @Test
    public void combiningANullRangeAndANormalRangeReturnsRange2() {
    	assertEquals("null + range2 = range2", 
    			Range.combineIgnoringNaN(null, new Range(0, 20)), new Range(0, 20));}
    
    @Test
    public void combiningTwoNormalRangesBothWithUpperNaNReturnNull() {
    	assertEquals("range1(n,Nan) + range2(n,Nan) = range1+range2", 
    			Range.combineIgnoringNaN(new Range(0, Double.NaN),
    					new Range(0, Double.NaN)), new Range(0, Double.NaN));}
    
    @Test
    public void combiningTwoNormalRangesBothWithLowerNaNReturnNull() {
    	assertEquals("range1(Nan,n) + range2(Nan,n) = range1+range2", 
    			Range.combineIgnoringNaN(new Range(Double.NaN, 0),
    					new Range(Double.NaN, 0)), new Range(Double.NaN, 0));}
    
    @Test
    public void getToMinWithValuesToFinishCoverage() {
    	assertEquals("No Matter", 
    			Range.combineIgnoringNaN(new Range(-1, 0),
    					new Range(Double.NaN, 0)), new Range(-1, 0));}
    
    @Test
    public void rangeTwoIsNullRangeOneIsGood() {
    	assertEquals("range1(m,n) + null = range1", 
    			Range.combineIgnoringNaN(new Range(-1, 0),
    					null), new Range(-1, 0));}
    
    @Test
    public void getToMaxWithValuesToFinishCoverage() {
    	assertEquals("No Matter", 
    			Range.combineIgnoringNaN(new Range(-1, 0),
    					new Range(-1, Double.NaN)), new Range(-1, 0));}
    
    //----------------------------------------------------------
    // Testing For the expandToInclude Method 
    //----------------------------------------------------------
    
    @Test
    public void expandToIncludeUpperValue() {
    	assertEquals("to include 5 range of -1 to 1 it will go to -1 to 5", 
    			Range.expandToInclude(new Range(-1, 1), 5), new Range(-1, 5));}
    
    @Test
    public void expandToIncludeLowerValue() {
    	assertEquals("to include -5 range of -1 to 1 it will go to -5 to 1", 
    			Range.expandToInclude(new Range(-1, 1), -5), new Range(-5, 1));}
    
    @Test
    public void expandToIncludeInternalValue() {
    	assertEquals("to include 0 range of -1 to 1 it will go to -1 to 1", 
    			Range.expandToInclude(new Range(-1, 1), 0), new Range(-1, 1));}
    
    @Test
    public void nullRangeExpandToIncluderReturns1ValueRange() {
    	assertEquals("to include 0 range of null it will go to 0 to 0", 
    			Range.expandToInclude(null, 0), new Range(0, 0));}
    
    //----------------------------------------------------------
    // Testing For the shift(range, double, & bool) Method 
    //----------------------------------------------------------
    
    @Test
    public void shiftRangeBy3() {
    	assertEquals("shifting the range -1 to 1 by 3 should show 2 to 6", 
    			new Range(2, 6), Range.shift(new Range(-1, 1), 3));}
    
    @Test
    public void shiftRangeBy3WithAllowingCrosover() {
    	assertEquals("shifting the range -1 to 1 by 3 should show 2 to 6", 
    			new Range(2, 6), Range.shift(new Range(-1, 1), 3, true));}
    
    @Test
    public void shiftRangeBy3WithAllowingCrosoverWithZeroLowerRange() {
    	assertEquals("shifting the range 0 to 1 by 3 should show 3 to 4", 
    			new Range(3, 4), Range.shift(new Range(0, 1), 3, true));}
    
    @Test
    public void shiftRangeBy3WithAllowingCrosoverWithZeroUpperRange() {
    	assertEquals("shifting the range -1 to 0 by 3 should show 2 to 3", 
    			new Range(2, 3), Range.shift(new Range(-1, 0), 3, true));}
    
    @Test
    public void shiftRangeBy3WithNotAllowingCrosoverWithZeroUpperRange() {
    	assertEquals("shifting the range -1 to 0 by 3 should show 2 to 3", 
    			new Range(2, 3), Range.shift(new Range(-1, 0), 3, false));}
    
    //----------------------------------------------------------
    // Testing For the scale Method 
    //----------------------------------------------------------
    
//    @Test(expected = nullNotPermitted.class)
//    public void scalingANullRange() {
//    	Range.scale(null, 2);}
    
    @Test
    public void scalingARange() {
    	assertEquals("scalling the range -1 to 1 by 2 should show -2 to -2",
    			new Range(-2, 2), Range.scale(new Range(-1, 1), 2));
  	}
    
    @Test(expected = IllegalArgumentException.class)
    public void scalingARangeWithFactorOf0() {
    	Range.scale(new Range(-1, 1), 0);
  	}
    
    @Test(expected = IllegalArgumentException.class)
    public void scalingARangeWithFactorOfNeg() {
    	Range.scale(new Range(-1, 1), -1);
  	}
    
    //----------------------------------------------------------
    // Testing For the expand Method 
    //----------------------------------------------------------
    
    @Test
    public void expandRangeby0() {
    	assertEquals("expand the range -1 to 1 by margins o% upper and 0% lower should show -1 to 1", 
    			new Range(-1, 1), Range.expand(new Range(-1, 1), 0, 0));}
    
    @Test
    public void expandRangeby0andnegativeforlowerbownd() {
    	assertEquals("expand the range -1 to 1 by margins 0% upper and -200% lower should show 2 to 1", 
    			new Range(-1, 1), Range.expand(new Range(-1, 1), -2, 0));}
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
