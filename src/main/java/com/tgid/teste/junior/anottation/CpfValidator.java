package com.tgid.teste.junior.anottation;

import com.tgid.teste.junior.anottation.Cpf;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CpfValidator implements ConstraintValidator<Cpf, String> {
    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext constraintValidatorContext) {
        if(cpf == null || cpf.length() !=11 || hasEqualDigits(cpf)){
            return false;
        }
        return isValidCpf(cpf);
    }

    private static boolean hasEqualDigits(String cpf) {
        char firstDigit = cpf.charAt(0);
        return cpf.chars().allMatch(digit -> digit == firstDigit);
    }

    public static boolean isValidCpf(String cpf) {
        int firstActualVerifier = Character.getNumericValue(cpf.charAt(9));
        int secondActualVerifier = Character.getNumericValue(cpf.charAt(10));
        String firstNineNumbersOfCpf = cpf.substring(0, 9);
        int firstCalculation = getCpfNumbersCalculation(8, firstNineNumbersOfCpf);
        int realFirstVerifier = getRealVerifierNumber(firstCalculation);
        if (realFirstVerifier != firstActualVerifier) {
            return false;
        }
        String firstTenNumbersOfCpf = cpf.substring(0, 10);
        int secondCalculation = getCpfNumbersCalculation(9, firstTenNumbersOfCpf);
        int realSecondVerifier = getRealVerifierNumber(secondCalculation);
        return (realSecondVerifier == secondActualVerifier);
    }

    private static int getRealVerifierNumber(int calculationTotal) {
        int modOfFirstDivision = calculationTotal % 11;
        return (modOfFirstDivision < 2) ? 0 : 11 - modOfFirstDivision;
    }

    private static int getCpfNumbersCalculation(int rangeEnd, String numbers) {
        int sum = 0;
        for (int position = rangeEnd; position >= 0; position--) {
            int multiplier = (rangeEnd - position) + 2;
            sum += multiplier * Character.getNumericValue(numbers.charAt(position));
        }
        return sum;
    }

}


