package lk.bluesky.atm.dto;

public class ChangePinRequest {
    private String accountNumber;
    private String newPin;

    public ChangePinRequest() {}

    public ChangePinRequest(String accountNumber, String newPin) {
        this.accountNumber = accountNumber;
        this.newPin = newPin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getNewPin() {
        return newPin;
    }

    public void setNewPin(String newPin) {
        this.newPin = newPin;
    }
}
