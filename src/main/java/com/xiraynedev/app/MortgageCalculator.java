package com.xiraynedev.app;

import java.util.ArrayList;
import java.util.Scanner;

public class MortgageCalculator {
        private int principal;
        private float annualInterestRate;
        private byte loanPeriod;
        private float monthlyInterestRate;
        private short numberOfPayments;
        private double mortgage;
        private ArrayList<Double> balance = new ArrayList<>();

        public void startCalculator() {
                Scanner scanner = new Scanner(System.in);

                while (true) {
                        System.out.println();
                        System.out.print(
                                        "--Mortgage Calculator-- Type any character and press enter. Enter q to quit: ");
                        String response = scanner.nextLine().toLowerCase();
                        if (response.charAt(0) == 'q' && response.length() == 1) {
                                break;
                        }
                        getPrincipal();
                        getAnnualInterestRate();
                        getLoanPeriod();
                        calculateMortgage();
                        calculateBalance();
                        MortgageReport.printMortgage(mortgage);
                        MortgageReport.printPaymentSchedule(balance);
                        balance.clear();
                }
                scanner.close();
        }

        private void getPrincipal() {
                principal = (int) (Console.getUserInput("Principal ($1K - $1M): ", 1000.0, 1_000_000.0));
        }

        private void getAnnualInterestRate() {
                annualInterestRate = (float) (Console.getUserInput("Annual Interest Rate (1.0 - 30.0): ", 1.0, 30.0));
        }

        private void getLoanPeriod() {
                loanPeriod = (byte) (Console.getUserInput("Loan Period (Years) 1 - 30: ", 1.0, 30.0));
        }

        private void calculateMortgage() {
                byte PERCENT = 100;
                byte MONTHS_IN_YEAR = 12;
                monthlyInterestRate = (annualInterestRate / PERCENT) / MONTHS_IN_YEAR;
                numberOfPayments = (short) (loanPeriod * MONTHS_IN_YEAR);

                mortgage = principal * (monthlyInterestRate * (Math.pow(1 + monthlyInterestRate, numberOfPayments))
                                / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1));
        }

        private void calculateBalance() {
                for (short month = 1; month <= numberOfPayments; month++) {
                        balance.add(principal
                                        * (Math.pow(1 + monthlyInterestRate, numberOfPayments)
                                                        - Math.pow(1 + monthlyInterestRate, month))
                                        / (Math.pow(1 + monthlyInterestRate, numberOfPayments) - 1));
                }
        }
}
