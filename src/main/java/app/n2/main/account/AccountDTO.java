package app.n2.main.account;

public class AccountDTO {
    private String accountId;
    private String profileId;
    private String about;

    public AccountDTO(String accountId, String profileId, String about) {
        this.accountId = accountId;
        this.profileId = profileId;
        this.about = about;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getProfileId() {
        return profileId;
    }

    public String getAbout() {
        return about;
    }
}
