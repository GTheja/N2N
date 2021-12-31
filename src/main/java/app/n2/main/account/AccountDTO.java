package app.n2.main.account;

public class AccountDTO {
    private final String accountId;
    private final String profileId;
    private final String postId;
    private final String email;
    private final String profileName;

    public AccountDTO(String accountId, String profileId, String profileName,
                      String postId, String email) {
        this.accountId = accountId;
        this.profileId = profileId;
        this.postId = postId;
        this.email = email;
        this.profileName = profileName;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getProfileId() {
        return profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getEmail() {
        return email;
    }

    public String getPostId() {
        return postId;
    }
}
