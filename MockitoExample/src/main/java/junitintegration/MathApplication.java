package junitintegration;

public class MathApplication {

    private CalculatorService calcService;

    public void setCalcService(CalculatorService calcService) {
        this.calcService = calcService;
    }

    public double add(double input1, double input2){
        return calcService.add(input1, input2);
    }

    public double substract(double input1, double input2){
        return calcService.substract(input1, input2);
    }

    public double multiply(double input1, double input2){
        return calcService.multiply(input1, input2);
    }

    public double divide(double input1, double input2){
        return calcService.divide(input1, input2);
    }

}
