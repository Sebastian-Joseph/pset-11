import java.util.ArrayList;

public class ProblemSet11 {

    private boolean addToALFromString(ArrayList<Integer> iArrList, String strNumbers) {
        try {
            strNumbers = strNumbers.replace(" ", "").replace(",", "");

            char[] chars = strNumbers.toCharArray();

            for (char ch : chars) {
                iArrList.add(Character.getNumericValue(ch));
            }

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    private String intALToString(ArrayList<Integer> aL) {
        try {
            if (aL.size() <= 0) return "null";

            String lString = "";

            int i = 1;
            for (Integer j : aL)
            {
                lString += Integer.toString(j);

                if (i < aL.size())
                    lString += ", ";

                i++;
            }

            return lString;
        }
        catch (Exception e) {
            return "null";
        }
    }

    private String strALToString(ArrayList<String> aL) {
        try {
            if (aL.size() <= 0) return "null";

            String lString = "";

            int i = 1;
            for (String s : aL)
            {
                lString += s;

                if (i < aL.size())
                    lString += ", ";

                i++;
            }

            return lString;
        }
        catch (Exception e) {
            return "null";
        }
    }

    public ArrayList<String> fizzBuzz(int start, int end) {
        try {
            if (start >= end) return null;

            int len = end-start;
            ArrayList<String> sAL = new ArrayList<String>();
            int startVal = start;

            for (int i=0; i<len; i++) {
                if ( ((startVal % 3) == 0) && ((startVal % 5) == 0) )
                    sAL.add("FizzBuzz");
                else if ((startVal % 3) == 0)
                    sAL.add("Fizz");
                else if ((startVal % 5) == 0)
                    sAL.add("Buzz");
                else
                    sAL.add(Integer.toString(startVal));
                startVal++;
            }

            return sAL;
        }
        catch (Exception e) {
            return null;
        }
    }

    public int maxSpan(ArrayList<Integer> numbers) {
        try {
            if (numbers == null) return -1;
            if (numbers.size() == 1) return 1;
            if (numbers.size() <= 0) return 0;

            int len = numbers.size();
            int maxSpan = 0;

            ArrayList<String> sNumbers = new ArrayList<String>();

            for (Integer i: numbers) {
                sNumbers.add(String.valueOf(i));
            }

            String strNumbers = strALToString(sNumbers);
            strNumbers = strNumbers.replace(" ", "").replace(",", "");

            for (int i=0; i<len; i++) {
                int fOccurance, lOccurance;

                fOccurance = strNumbers.indexOf(String.valueOf(numbers.get(i)));
                lOccurance = strNumbers.lastIndexOf(String.valueOf(numbers.get(i)));

                int span = (lOccurance - fOccurance) + 1;

                if (maxSpan < span)
                    maxSpan = span;
            }

            return maxSpan;
        }
        catch (Exception e) {
            return -1;
        }
    }

    public ArrayList<Integer> fix34(ArrayList<Integer> numbers) {
        try {
            if (numbers == null) return null;   //The array must not be null

            boolean flgThreeAppeared = false;
            int cnt3 = 0, cnt4 = 0;
            int len = numbers.size();

            for (int i=0; i<len; i++) {
                if (numbers.get(i) == 3) {
                    flgThreeAppeared = true;
                    cnt3++;

                    if (i < len-1) {
                        if (numbers.get(i+1) == 3)
                            return null;    //Every 3 has a number after it that is not a 3
                    }
                    else
                        return null;    //3 is a last element; Every 3 has a number after it that is not a 4
                }

                if (numbers.get(i) == 4) {
                    cnt4++;

                    if (!flgThreeAppeared)
                        return null;    //At least one 3 must appear in the array before the first 4
                }
            }

            if (cnt3 != cnt4)
                return null;    //There must be an equal number of 3s and 4s in the array

            for (int i=0; i<len; i++) {
                if (numbers.get(i) == 3) {
                    int fourIndex = findXNotRightNextToY(numbers, 4, 3);

                    if (i < len-1)
                        if (fourIndex != -1) {
                            int temp = numbers.get(i+1);
                            numbers.set(i+1, numbers.get(fourIndex));
                            numbers.set(fourIndex, temp);
                        }
                }
            }

            return numbers;
        }
        catch (Exception e) {
            return null;
        }
    }

    private int findXNotRightNextToY(ArrayList<Integer> numbers, int x, int y) {
        try {
            int len = numbers.size();

            for (int i=0; i<len; i++) {
                if (i == 0) {
                    if (numbers.get(i) == x)
                        return i;
                }
                else if ( (numbers.get(i) == x) && (numbers.get(i-1) != y) )
                    return i;
            }

            return -1;
        }
        catch (Exception e) {
            return -1;
        }
    }

    public ArrayList<Integer> fix45(ArrayList<Integer> numbers) {
        try {
            if (numbers == null) return null;   //The array must not be null

            int cnt4 = 0, cnt5 = 0;
            int len = numbers.size();

            for (int i=0; i<len; i++) {
                if (numbers.get(i) == 4) {
                    cnt4++;

                    if (i < len-1) {
                        if (numbers.get(i+1) == 4)
                            return null;    //Every 4 has a number after it that is not a 4
                    }
                    else
                        return null;    //4 is a last element; Every 4 has a number after it that is not a 5
                }

                if (numbers.get(i) == 5)
                    cnt5++;
            }

            if (cnt4 != cnt5)
                return null;    //There must be an equal number of 4s and 5s in the array

            for (int i=0; i<len; i++) {
                if (numbers.get(i) == 4) {
                    int fiveIndex = findXNotRightNextToY(numbers, 5, 4);

                    if (i < len-1)
                        if (fiveIndex != -1) {
                            int temp = numbers.get(i+1);
                            numbers.set(i+1, numbers.get(fiveIndex));
                            numbers.set(fiveIndex, temp);
                        }
                }
            }

            return numbers;
        }
        catch (Exception e) {
            return null;
        }
    }

    public boolean canBalance(ArrayList<Integer> numbers) {
        try {
            if (numbers == null) return false;

            int len = numbers.size();

            if (len <= 0) return false;

            int sumI = 0;

            for (int i=0; i<len; i++) {
                sumI = sumI + numbers.get(i);
                int sumJ = 0;

                for (int j=i+1; j<len; j++)
                    sumJ = sumJ + numbers.get(j);

                if (sumI == sumJ)
                    return true;
            }

            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean linearIn(ArrayList<Integer> outer, ArrayList<Integer> inner) {
        try {
            if (outer == null) return false;
            if (inner == null) return false;

            int lenO = outer.size();
            int lenI = inner.size();

            if (lenO <= 0) return false;
            if (lenI <= 0) return false;

            if (!isALSorted(outer)) return false;
            if (!isALSorted(inner)) return false;

            int j = 0;

            for (int i=0; i<lenO; i++)
                if (j < lenI)
                    if (inner.get(j) == outer.get(i))
                        j++;

            if (j == lenI)
                return true;
            else
                return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    private boolean isALSorted(ArrayList<Integer> aL) {
        try {
            int len = aL.size();

            for (int i=0; i<len; i++)
                if (i < len-1)
                    if (aL.get(i) > aL.get(i+1))
                        return false;

            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public ArrayList<Integer> squareUp(int n) {
        try {
            if (n < 0) return null;

            int len = n * n;
            ArrayList<Integer> aL = new ArrayList<Integer>();

            for (int i=0; i<len; i++)
                aL.add(0);

            if(n == 0)
                return aL;

            for(int i=n-1; i<len; i=i+n)
                for(int j=i; j>=(i-(i/n)); j--)
                    aL.set(j, (i - j + 1));

            return aL;
        }
        catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Integer> seriesUp(int n) {
        try {
            if (n < 0) return null;

            int len = (n * (n+1)) / 2;
            ArrayList<Integer> aL = new ArrayList<Integer>();

            for (int i=0; i<len; i++)
                aL.add(0);

            int k = 0;

            for(int i=0; i<n; i++) {
                for(int j=0; j<i+1; j++) {
                    int indx = k + j;
                    aL.set(indx, (j + 1));
                }
                k += (i + 1);
            }

            return aL;
        }
        catch (Exception e) {
            return null;
        }
    }

    public int maxMirror(ArrayList<Integer> numbers) {
        try {
            if (numbers == null) return -1;

            int maxMirror = 0;
            int len = numbers.size();

            for(int i=0; i<len; i++) {
                for(int j=len-1; j>=0; j--) {
                    int maxSize = 0;
                    int maxJ = j;

                    for (int maxI=i; (maxI<len && maxJ>=0); maxI++) {
                        if (numbers.get(maxI) == numbers.get(maxJ)) {
                            maxSize++;
                            maxJ--;
                        }
                        else
                            break;
                    }

                    if (maxMirror <= maxSize)
                        maxMirror = maxSize;
                }
            }

            return maxMirror;
        }
        catch (Exception e) {
            return -1;
        }
    }

    public int countClumps(ArrayList<Integer> numbers) {
        try {
            if (numbers == null) return -1;

            int cnt = 0;
            int len = numbers.size();

            for(int i=0; i<len; i++) {
                int temp = numbers.get(i);
                int cLen = 1;

                for(int j=i+1; (j<len && numbers.get(i+1)==temp); j++) {
                    i++;
                    cLen++;
                }

                if (cLen > 1)
                    cnt++;
            }

            return cnt;
        }
        catch (Exception e) {
            return -1;
        }
    }
}
