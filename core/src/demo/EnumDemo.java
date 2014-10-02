package demo;

import java.math.BigDecimal;

public enum EnumDemo {
	BankOfAmerica(new BigDecimal(300)), Citibank(new BigDecimal(300)), Goldman(new BigDecimal(300));

	private BigDecimal clearingCharge;

	private EnumDemo(BigDecimal fees){
	this.clearingCharge = fees;
	}

	public BigDecimal getClearningCharge(){
	return clearingCharge;
	}

	@Override
	public String toString() {
		return "[ " + this.name() + " ]" + "  Clearing Charge: " + this.clearingCharge.toString();
	}

//	@Override
//	public String toString() {
//	     switch (this) {
//	       case BankOfAmerica:
//	            System.out.println("Bank Of America: " + clearingCharge);
//	            break;
//	       case Citibank:
//	            System.out.println("Citibank: " + clearingCharge);
//	            break;
//	       case Goldman:
//	            System.out.println("Goldman: " + clearingCharge);
//	      }
//	return super.toString();
//	}

	public static void main(String[] args) {
		System.out.println(EnumDemo.BankOfAmerica.name() + " = " + EnumDemo.BankOfAmerica.getClearningCharge() + "\n");
		
		EnumDemo citibank = EnumDemo.valueOf("Citibank");
		System.out.println(citibank);
		System.out.println(citibank.name());
		System.out.println(citibank.ordinal()+"\n");
		
		for (EnumDemo bank : EnumDemo.values()) {
			System.out.println(bank);
			System.out.println(bank.name() + " = " + bank.getClearningCharge());
			System.out.println(bank.ordinal()+"\n");
		}
	  }
}
