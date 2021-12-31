package app.n2.main.service;

import app.n2.main.account.ProfileDTO;
import app.n2.main.authentication.RegisterDTO;
import app.n2.main.database.AccountDB;
import app.n2.main.database.ProfileDB;

import javax.ws.rs.core.HttpHeaders;

import static app.n2.main.utils.Validations.getCreateProfileID;

public class N2NHomeService {

    private final AccountDB accountDB = new AccountDB();
    private final ProfileDB profileDB = new ProfileDB();

    public void addAccountToDB(HttpHeaders headers) {
        String accountId = getAccountId(headers);
        RegisterDTO registerDTO = accountDB.lookForAccount(accountId);
        String createProfileID = getCreateProfileID(registerDTO.getUsername());
        accountDB.createDB(accountId, registerDTO.getEmail(),
                registerDTO.getUsername(),createProfileID, "");
    }

    public ProfileDTO getProfileDB(String profileId){
        return profileDB.lookForProfile(profileId);
    }


    private String getAccountId(HttpHeaders headers) {
        String cookie = headers.getRequestHeader("Cookie").get(0);
        String[] split = cookie.split(":");
        String accountId = null;
        for(int i = 0; i< split.length; i ++){
            accountId = split[1];
        }
        return accountId;
    }
}
