package services;

import com.google.inject.Inject;
import filters.SecureFilter;
import models.User;
import ninja.Context;
import ninja.Result;
import org.apache.commons.codec.binary.Hex;
import repository.UserRepository;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

/**
 * Created by Channel on 2015-01-16.
 */
public class AuthenticationService {

    @Inject
    UserRepository userRepository;

    public boolean authenticate(String username,String password){
       Optional<User> userOptional= userRepository.findUserByUName(username);
        /*if(userOptional.get().getUsername().equals(username)){
            return compareSaltedPW(userOptional.get(),password);
        }*/
        if(userOptional.isPresent()){
            return compareSaltedPW(userOptional.get(),password);
        }
        return false;

/*
        Optional<User> userOptional;
        try{
            userOptional = userRepository.findUserByUName(username);
            if(userOptional.get().getUsername().equals(username)){
                return compareSaltedPW(userOptional.get(),password);
            }
        }
        catch (NoSuchElementException ex){
            return false;
        }
        return false;
*/


    }

    public boolean register(String username,String password){
        byte[] salt = generateSalt();
        String hashedPW = hashSalt(password,salt);

            //User user = userRepository.addUser(username,hashedPW,salt);
            User user= new User(username,hashedPW,salt);
            userRepository.persist(user);
            return true;

}

    public boolean usernameInUse(String username){
        Optional<User> user= userRepository.findUserByUName(username);
        return user.isPresent();
    }

    public boolean compareSaltedPW(User user,String password){
        String saltedPW = hashSalt(password, user.getSalt());
        return user.getPassword().equals(saltedPW);
    }

    public String hashSalt(String password, byte[] salt){
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = f.generateSecret(spec).getEncoded();

            return Hex.encodeHexString(hash);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            ex.printStackTrace();
            return null;
        }

    }
    private byte[] generateSalt(){
        Random random = new SecureRandom();
        byte[] salt = new byte[32];
        random.nextBytes(salt);
        return salt;
    }
}
