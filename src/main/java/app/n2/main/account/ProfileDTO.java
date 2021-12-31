package app.n2.main.account;

public class ProfileDTO {
    private final String profileId;
    private final String profileName;
    private final String profileAvatar;
    private final String about;

    public ProfileDTO(String profileId, String profileName, String profileAvatar, String about) {
        this.profileId = profileId;
        this.profileName = profileName;
        this.profileAvatar = profileAvatar;
        this.about = about;
    }

    public String getProfileId() {
        return profileId;
    }

    public String getProfileName() {
        return profileName;
    }

    public String getProfileAvatar() {
        return profileAvatar;
    }

    public String getAbout() {
        return about;
    }
}
