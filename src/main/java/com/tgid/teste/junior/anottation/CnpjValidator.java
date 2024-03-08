package com.tgid.teste.junior.anottation;

import com.tgid.teste.junior.anottation.Cnpj;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CnpjValidator implements ConstraintValidator<Cnpj, String> {
    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext constraintValidatorContext) {
        if(cnpj == null || cnpj.length() !=14 || hasEqualDigits(cnpj)){
            return false;
        }
        return isValidCnpj(cnpj);
    }

    private static boolean hasEqualDigits(String cnpj) {
        char firstDigit = cnpj.charAt(0);

        return cnpj.chars().allMatch(digit -> digit == firstDigit);
    }

    public static boolean isValidCnpj(String cnpj) {
        int[] firstBaseCnpjNumbersMultipliers = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] secondBaseCnpjNumbersMultipliers = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        String firstTwelveNumbersOfCnpj = cnpj.substring(0, 12);
        int actualFirstVerifier = Character.getNumericValue(cnpj.charAt(12));
        int actualSecondVerifier = Character.getNumericValue(cnpj.charAt(13));
        int firstCalculation = calculateCnpjBaseNumbers(firstBaseCnpjNumbersMultipliers, firstTwelveNumbersOfCnpj);
        int realFirstVerifier = getRealVerifierNumber(firstCalculation);
        if (realFirstVerifier != actualFirstVerifier) {
            return false;
        }
        String firstThirteenNumbersOfCnpj = cnpj.substring(0, 13);
        int secondCalculation = calculateCnpjBaseNumbers(secondBaseCnpjNumbersMultipliers, firstThirteenNumbersOfCnpj);
        int realSecondVerifier = getRealVerifierNumber(secondCalculation);
        return (actualSecondVerifier == realSecondVerifier);
    }

    private static int calculateCnpjBaseNumbers(int[] baseMultipliers, String numbers) {
        int sum = 0;
        for (int i = 0; i < baseMultipliers.length; i++) {
            sum += Character.getNumericValue(numbers.charAt(i)) * baseMultipliers[i];
        }
        return sum;
    }

    private static int getRealVerifierNumber(int calculationTotal) {
        int modOfFirstDivision = calculationTotal % 11;
        return (modOfFirstDivision < 2) ? 0 : 11 - modOfFirstDivision;
    }

}
