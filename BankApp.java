import java.util.Scanner;
import java.util.ArrayList;
// Interface tells us what to do but does not tell us how to do 
// interface is a fully abstract class 
// in interface we only have declaration and definition is done in the class that implements it
// no partial implementation is allowed so all the account services must be implemente
interface AccountServices {
	public String getIFSC_CODE();
	public String getBranchName();
	public String getBankName();
	public void CheckBalance();
	public String getAccountHolderName();
	public int getAccountNumber();
	public void fundTransfer(BankAccount fromAccount ,BankAccount toAccount, double amount);
	public void Deposit(double amount);
	public void Deposit(double amount,String method);
	public void Withdraw(double amount);
	public void Withdraw(double amount , String mode);
}



// creating a base class called Bank Account from which we create an object and that object must have all the services accessible 
class BankAccount implements AccountServices{
    private int accountNumber;
    private String accountHolderName;
    private double balance;
	private static final String IFSC_CODE = "CSBS000A682";
	private static final String Bank_Name = "CSBS BANK";
	private static final String Branch_Name = "Avalahalli ";

    // Constructor with only account holder name
    public BankAccount(String accountHolderName) {
        this.accountNumber = BankUtil.generateAccountNumber();
        this.accountHolderName = accountHolderName; // this keyword used to avoid instance variable hiding
        this.balance = 0.0;
    }

    // Constructor with account holder name and initial balance
    public BankAccount(String accountHolderName, double balance) {
        this.accountNumber = BankUtil.generateAccountNumber();
        this.accountHolderName = accountHolderName; // this keyword used to avoid instance variable hiding
        this.balance = balance;
    }

	public String getBranchName() {
		return Branch_Name;
	}

	public String getBankName(){
		return Bank_Name;
	}

	public String getIFSC_CODE(){
		return IFSC_CODE;
	}

    // Method to get the Account Number
    public int getAccountNumber() {
        return accountNumber;
    }
	
    // Method to get the AccountHolder Name only	
    public String getAccountHolderName() {
        return accountHolderName;
    }

    // Method to get the Balance of the account 	
    public double getBalance() {
        return balance;
    }

	public double setBalance(double value) {
        return balance = value;
    }

	// Mehtod to get Check balance and displace balance
	public void CheckBalance(){
		System.out.println("Your Account balance is :"+getBalance());
	}    

	public void fundTransfer(BankAccount fromAccount ,BankAccount toAccount, double sum){
		if(sum > 0 && sum <= this.balance){
			fromAccount.balance -= sum;
			toAccount.balance += sum ;
			System.out.println("Transferred "+ sum+" from "+ this.accountNumber +" to "+ toAccount.getAccountNumber());
		}
	}


	// Creating Method to Deposit Money
	public void Deposit(double amount){
		balance += amount;
		System.out.println("Deposited "+ amount +" to your account "+ accountNumber +" successfully ");
	}

	// overloading the Deposit method if the user wants to deposit using other methods
	public void Deposit(double amount , String method){ 
		balance += amount;
		System.out.println(" Deposited "+ amount +" in to your account "+ accountNumber +" thorugh "+ method);
	}


	// Creating Method to Withdraw Money from your account
	public void Withdraw(double amount){
		if(amount <= balance) {
			
			balance -= amount;
			System.out.println("Withdraw"+amount+" from your account "+ accountNumber);
			System.out.println("Balance remaining in your account is "+ getBalance());
		}
		else{ 
			System.out.println("Insufficient Balance to withdraw that much amount");
		}
	}
	
	// overloading  Method to Withdraw Money from your account
	public void Withdraw(double amount,String mode){
		if(amount <= balance) {
			balance -= amount;
			System.out.println("Withdraw"+ amount +"from your account"+ accountNumber +"throught"+ mode);
			System.out.println("Balance remaining in your account is "+getBalance());
		}
		else{ 
			System.out.println("Insufficient Balance to withdraw that much amount");
		}
	}
	
}


// creating a Subclass called Savings Account which inherits the properties of base class BankAccount

class SavingsAccount extends BankAccount{
	private static final double MIN_BALANCE = 500.0;
	private static final double INTREST_RATE = 0.03; // this is annual intrest rate
	
	// constructor of Savings Account
	public SavingsAccount(String accountHolderName) {
		super(accountHolderName);//super keyword usedcall the constructor of the superclass,usefull when we want initialize base class variables		// here the balance automatically gets initialized to zero as we did not pass the balance argument 
	}

	// another constructor of savings account with two arguments or parameter
	public SavingsAccount(String accountHolderName , double balance) {
		super(accountHolderName , balance);
		// here the balance is given so balance will get initialized to the given parameter
	}


	// Overriding the withdraw & deposit method defined in the base class
	@Override
	public void Deposit(double amount) {
		if(amount >= 0){
			super.Deposit(amount); // here is the second usage of super keyword to access the superclass method .... to access superclass method we use dot-operator(.) ..where as for constructor we dont use the dot-operator.
		}
		else {
			System.out.println("Cannot deposit negative number :)) ");
		}
	}

	
	// Overriding the deposit method defined in the base class with another parameter method
	@Override
	public void Deposit(double amount, String method) {
		if(amount >= 0){
			super.Deposit(amount,method); 
		}
		else {
			System.out.println("Cannot deposit negative number :)) ");
		}
	}

	// overriding the withdraw method
	@Override
	public void Withdraw(double amount){
		if( getBalance() - amount >= MIN_BALANCE) {
			super.Withdraw(amount);

		}
		else { 
			System.out.println("Cannot Withdraw amount of rs"+ amount +"because of Minimum balance policy of atleast rs"+ MIN_BALANCE);
		}
	}

	
	// overriding the withdraw method
	@Override
	public void Withdraw(double amount,String mode){
		if( getBalance() - amount >= MIN_BALANCE) {
			super.Withdraw(amount,mode);
		}
		else { 
			System.out.println("Cannot Withdraw amount of rs "+  amount + " because of Minimum balance policy of atleast rs"+ MIN_BALANCE);
		}
	}
	
	public double CalculateIntrest(int days) {
		double intrestperday = INTREST_RATE /365;
		return getBalance() * intrestperday * days ;
	}

} 



// Creating a subclass called Current Account with

class CurrentAccount extends BankAccount {
	private static final double OVERDRAFT_LIMIT = 1000;
	

	public CurrentAccount(String accountHolderName){
		super(accountHolderName);
	}

	public CurrentAccount(String accountHolderName , double balance) {
		super(accountHolderName,balance);
	}

	// overriding Methods to withdraw and deposit

	@Override
	public void Deposit(double amount){
		if(amount >= 0){
			super.Deposit(amount);
		}
		else { 
			System.out.println("Deposit amount should be a non negative number "); 
		}
	}

	@Override
	public void Deposit(double amount, String method) {
		if(amount >= 0){
			super.Deposit(amount,method); 
		}
		else {
			System.out.println("Cannot deposit negative number :)) ");
		}
	}

	//Override Withdraw method
	@Override
	public void Withdraw(double amount){
		if( getBalance() - amount >= -OVERDRAFT_LIMIT) {
			//super.Withdraw(amount); // not using the suepr method
			double result = getBalance() - amount;
			setBalance(result);
			System.out.println("Withdraw "+ amount +" from your account "+ getAccountNumber());
			System.out.println("Balance remaining in your account is "+getBalance());
			
		}
		else { 
			System.out.println("Cannot Withdraw amount of rs "+ amount+" as you have exhausted your OVERDRAFT LIMIT of rs "+ OVERDRAFT_LIMIT);
		}
	}

	
	// overriding the withdraw method
	@Override
	public void Withdraw(double amount,String mode){
		if( getBalance() - amount >= -OVERDRAFT_LIMIT) {
			//super.Withdraw(amount,mode); // but we wont use the super method as that will not allow the customer to withdraw the overdraft limit
			double result = getBalance() - amount;
			setBalance(result);
			System.out.println(" Withdraw "+ amount +" from your account "+ getAccountNumber() +" throught "+ mode);
			System.out.println(" Balance remaining in your account is "+getBalance());
		}
		else { 
			System.out.println("Cannot Withdraw amount of rs "+ amount +" as you have exhausted your OVERDRAFT LIMIT of rs "+OVERDRAFT_LIMIT);
		}
	}

	
}

class BankUtil{
	private static int counter = 0;
	
	// static method to access the static variables
	static int generateAccountNumber() {
		return  (++counter);
	}

}

// abstract class - restricts from creation of objects 
abstract class PremiumAccount extends BankAccount {
	public PremiumAccount(String accountHolderName) {
		super(accountHolderName);
	}

	public PremiumAccount(String accountHolderName, double balance) {
		super(accountHolderName, balance);
	}
	
	// abstract method for getbenefits ( with no definition)
	// abstract method has only interface and no implementation 
	// implementation is different for every class which implements it 
	public abstract String getBenefits();
}

class PremiumSavingsAccount extends PremiumAccount {
    private static final double MIN_BALANCE = 250.0; // lower minimum balance
    private static final double INTEREST_RATE = 0.05; // Higher annual interest rate for premium accounts

    public PremiumSavingsAccount(String accountHolderName) {
        super(accountHolderName);
    }

    public PremiumSavingsAccount(String accountHolderName, double balance) {
        super(accountHolderName, balance);
    }

    @Override
    public void Withdraw(double amount) {
        if (getBalance() - amount >= MIN_BALANCE) {
            super.Withdraw(amount);
        } else {
            System.out.println("Cannot withdraw. Minimum balance of " + MIN_BALANCE + " must be maintained.");
        }
    }

    @Override
    public void Withdraw(double amount, String mode) {
        if (getBalance() - amount >= MIN_BALANCE) {
            super.Withdraw(amount, mode);
        } else {
            System.out.println("Cannot withdraw amount of Rs " + amount + " because of Minimum balance policy of at least Rs " + MIN_BALANCE);
        }
    }

    @Override
    public void Deposit(double amount) {
        if (amount > 0) {
            super.Deposit(amount);
        } else {
            System.out.println("Cannot deposit a non-positive amount.");
        }
    }

    @Override
    public void Deposit(double amount, String type) {
        if (amount > 0) {
            super.Deposit(amount, type);
        } else {
            System.out.println("Cannot deposit a non-positive amount.");
        }
    }

    // Method to calculate interest earned for n number of days
    public double calculateInterest(int days) {
        double interestPerDay = INTEREST_RATE / 365;
        return getBalance() * interestPerDay * days;
    }

    // Implementing the abstract method to get benefits
    @Override
    public String getBenefits() {
        return "Premium Savings Account Benefits: Higher interest rate, free international transactions , lower minimum Balance, priority customer support";
    }
}

class PremiumCurrentAccount extends PremiumAccount {
    private static final double OVERDRAFT_LIMIT = 2000.0;

    public PremiumCurrentAccount(String accountHolderName) {
        super(accountHolderName);
    }

    public PremiumCurrentAccount(String accountHolderName, double balance) {
        super(accountHolderName, balance);
    }

    @Override
    public void Withdraw(double amount){
		if( getBalance() - amount >= -OVERDRAFT_LIMIT) {
			//super.Withdraw(amount,mode); again same as above we wont use the super method as that will not allow the customer to withdraw the overdraft limit
			double result = getBalance() - amount;
			setBalance(result);
			System.out.println(" Withdraw "+ amount +" from your account "+ getAccountNumber());
			System.out.println(" Balance remaining in your account is "+getBalance());
		}
		else { 
			System.out.println("Cannot Withdraw amount of rs "+ amount +" as you have exhausted your OVERDRAFT LIMIT of rs "+OVERDRAFT_LIMIT);
		}
	}

	@Override
	public void Withdraw(double amount,String mode){
		if( getBalance() - amount >= -OVERDRAFT_LIMIT) {
			//super.Withdraw(amount,mode); 	againb same as above
			double result = getBalance() - amount;
			setBalance(result);
			System.out.println(" Withdraw "+ amount +" from your account "+ getAccountNumber() +" throught "+ mode);
			System.out.println(" Balance remaining in your account is "+getBalance());
		}
		else { 
			System.out.println("Cannot Withdraw amount of rs "+ amount +" as you have exhausted your OVERDRAFT LIMIT of rs "+OVERDRAFT_LIMIT);
		}
	}

    @Override
    public void Deposit(double amount) {
        if (amount >= 0) {
            super.Deposit(amount);
        } else {
            System.out.println("Deposit amount should be a non-negative number.");
        }
    }

    // Implementing the abstract method to get benefits    
    @Override
    public String getBenefits() {
        return "Premium Current Account Benefits: higher Overdraft facility, priority customer support.";
    }
}

public class BankApp{
	static ArrayList<BankAccount> data = new ArrayList<>(); // array to store the details of the customers
		
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);		
		BankAccount account = null;

		while(true){

			System.out.println("\nWelcome to CSBS Banking Application!");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Create Premium Savings Account");
            System.out.println("4. Create Premium Current Account");
            System.out.println("5. Show Account Number");
            System.out.println("6. Show Account Holder Name");
            System.out.println("7. Check Balance");
            System.out.println("8. Deposit Money");
            System.out.println("9. Withdraw Money");
            System.out.println("10. Fund Transfer");
            System.out.println("11. View Benefits (Premium Accounts)");
            System.out.println("12. Calculate Interest (Applicable Accounts)");
            System.out.println("13. View All Accounts");
            System.out.println("14. Exit");
            System.out.print("Choose an option: ");

			int choice = sc.nextInt();

			switch(choice){
				case 1 :
					System.out.println("Enter the Account Holder Name : ");
					sc.nextLine();
					String SavName = sc.nextLine();
					System.out.println("Enter the initial balance (leave empty its optional) : ");
					double initialbalance = sc.nextDouble();
					if(initialbalance == 0){
						account = new SavingsAccount(SavName);
					}
					else {
						account = new SavingsAccount(SavName, initialbalance );
					}
					data.add(account);
					System.out.println("Savings Account created successfully for "+ SavName);
					break;

				case 2 :
					System.out.println("Enter the Account Holder Name for current account : ");
					sc.nextLine();
					String CurName = sc.nextLine();
					System.out.println("Enter the initial balance (leave empty its optional) : ");
					double CurIbalance = sc.nextDouble();
					if(CurIbalance == 0){
						account = new CurrentAccount(CurName);
					}
					else {
						account = new CurrentAccount(CurName, CurIbalance);
					}
					data.add(account);
					System.out.println("Current Account created successfully for "+ CurName);
					break;
				case 3 :
					System.out.println("Enter the Account Holder Name for Premium Savings account : ");
					sc.nextLine();
					String PsavName = sc.nextLine();
					System.out.println("Enter the initial balance (leave empty its optional) : ");
					double PresIbalance = sc.nextDouble();
					if(PresIbalance == 0){
						account = new PremiumSavingsAccount(PsavName);
					}
					else {
						account = new PremiumSavingsAccount(PsavName,PresIbalance);
					}
					data.add(account);
					System.out.println("Premium Savings Account created successfully for"+PsavName);
					break;
				case 4 :
					System.out.println("Enter the Account Holder Name for Premium Current account : ");
					sc.nextLine();
					String PcurName = sc.nextLine();
					System.out.println("Enter the initial balance (leave empty its optional) : ");
					double PreCIbalance = sc.nextDouble();
					if(PreCIbalance == 0){
						account = new PremiumCurrentAccount(PcurName);
					}
					else {
						account = new PremiumCurrentAccount(PcurName,PreCIbalance);
					}
					data.add(account);
					System.out.println("Premium Current Account created successfully for"+PcurName);
					break;

				case 5 :
					System.out.println("Enter the Account Holder Name: ");
					sc.nextLine();
					String accHolderName = sc.nextLine();
    				for (BankAccount acc : data) {
        				if (acc.getAccountHolderName() == accHolderName) {
            				System.out.println("Account Number: " + acc.getAccountNumber());
            				break;
						}
					}
					break;
				case 6 :
					System.out.println("Enter the Account Number: ");
					int accNumber = sc.nextInt();
					for( BankAccount acc : data){
						if(acc.getAccountNumber() == accNumber){
							System.out.println("Account Holder Name :"+acc.getAccountHolderName());
							break;
						}
					}
					break;
				case 7 :
					System.out.println("Enter your Account Number :");
					accNumber = sc.nextInt();
					for(BankAccount acc : data){
						if(acc.getAccountNumber() == accNumber){
							System.out.println("Balance is :"+acc.getBalance());
							break;
						}
					}
					break;
				case 8 :
					System.out.println("Enter your Account Number :");
					accNumber = sc.nextInt();
					System.out.println("Enter the amount to deposit :");
					double amount = sc.nextDouble();
					System.out.println("Enter the mode of deposit :");
					sc.nextLine();
					String mode = sc.nextLine();
					for(BankAccount acc : data){
						if(acc.getAccountNumber() == accNumber){
							if(mode.isEmpty()){
								acc.Deposit(amount);
								break;
							}
							else {
								acc.Deposit(amount,mode);
							}
							break;
						}
					}
					break;
				
				case 9 :
					System.out.println("Enter your Account Number :");
					accNumber = sc.nextInt();
					System.out.println("Enter the amount to be withdrawen :");
					double withdrawAmount = sc.nextDouble();
					System.out.println("Enter the mode of withdraw :");
					sc.nextLine();
					String thru = sc.nextLine();
					for (BankAccount acc : data) {
						if (acc.getAccountNumber() == accNumber) {
							if(thru.isEmpty()){
								acc.Withdraw(withdrawAmount);
							}
							else {
								acc.Withdraw(withdrawAmount,thru);
							}
							break;
						}
					}
					break;
				case 10 :
					System.out.println("Enter from account number :");
					int fromAccountNumber = sc.nextInt();
					System.out.println("Enter to account number :");
					int toAccountNumber = sc.nextInt();
					System.out.println("Enter amount to transfer :");
					double transferAmount = sc.nextDouble();
					BankAccount fromAccount = null;
					BankAccount toAccount = null;
					for (BankAccount acc : data) {
						if (acc.getAccountNumber() == fromAccountNumber) {
							fromAccount = acc;
						}
						if (acc.getAccountNumber() == toAccountNumber) {
							toAccount = acc;
						}
					}
					if (fromAccount != null && toAccount != null) {
						fromAccount.fundTransfer(fromAccount, toAccount, transferAmount);
					} else {
						System.out.println("Invalid account numbers.");
					}
					break;
				case 11 :
					System.out.println("Enter the Account Number :");
					accNumber = sc.nextInt();
					for(BankAccount acc : data){
						if(acc.getAccountNumber() == accNumber){
							if(acc instanceof PremiumAccount){
								PremiumAccount premiumAccount = (PremiumAccount) acc; //type casting to Premium Account
								System.out.println(premiumAccount.getBenefits());
							}
							else {
								System.out.println("Account is not a Premium Account");
							}
							break;
						}
					}
					break;
				case 12 :
					System.out.println("Enter the Account Number :");
					accNumber = sc.nextInt();
					System.out.println("Enter the number of days to calculate interest :");
					int days = sc.nextInt();
					for(BankAccount acc : data){
						if(acc.getAccountNumber() == accNumber){
							if(acc instanceof SavingsAccount){
								SavingsAccount savingsAccount = (SavingsAccount) acc;
								System.out.println("Interest earned: " + savingsAccount.CalculateIntrest(days));
							}
							else if(acc instanceof PremiumSavingsAccount){
								PremiumSavingsAccount premiumSavingsAccount = (PremiumSavingsAccount) acc;
								System.out.println("Interest earned: " + premiumSavingsAccount.calculateInterest(days));
							}
							else {
								System.out.println("Account does not earn interest.");
							}
							break;
						}
					}
					break;
				case 13 :
					System.out.println("All Accounts of the bank are : ");
					for(BankAccount acc : data){
						System.out.println("Bank Name : "+acc.getBankName());
						System.out.println("Branch name "+acc.getBranchName());
						System.out.println("Account Number: " + acc.getAccountNumber());
						System.out.println("Account Holder Name: " + acc.getAccountHolderName());
						System.out.println("Balance: " + acc.getBalance());
						System.out.println("IFSC CODE : "+acc.getIFSC_CODE());
						System.out.println();
					}
					break;
				case 14 :
					System.out.println("Exiting the application. Thank you!");
					sc.close();
					System.exit(0); // zero is used for normal termination
					return;
				default :
					System.out.println("Invalid option. Please try again.");
					break;
			}

		}

	}
}