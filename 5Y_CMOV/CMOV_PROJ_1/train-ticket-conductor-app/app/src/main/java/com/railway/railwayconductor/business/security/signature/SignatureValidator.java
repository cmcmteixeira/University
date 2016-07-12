package com.railway.railwayconductor.business.security.signature;

import android.util.Base64;

import com.railway.railwayconductor.business.security.strategy.HashStrategy;
import com.railway.railwayconductor.business.security.provider.PublicKeyProvider;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by cteixeira on 08-11-2015.
 */
public class SignatureValidator {
    private final HashStrategy strategy;
    private final SignatureProvider signatureProvider;
    private final PublicKeyProvider pkeyProvider;

    public SignatureValidator(
            HashStrategy strategy,
            SignatureProvider signatureProvider,
            PublicKeyProvider pkeyProvider
    ) {

        this.strategy = strategy;
        this.signatureProvider = signatureProvider;
        this.pkeyProvider = pkeyProvider;
    }

    public boolean validate(){
        try {
            String value = strategy.getStringToHash();
            String signature = signatureProvider.provideSignature();
            //String keyS = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAL2N2KM0oOsV+LFNnFCRr/+oMoN2zp1I\nCq5mFqPNQccTHhCnFWG2lN0oxNvJwoPg0y+m1a6VDKBxaTW7JnCTNKUCAwEAAQ==";
            String keyS = pkeyProvider.provideKey();
            X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.decode(keyS,Base64.DEFAULT));
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey key = kf.generatePublic(spec);
            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initVerify(key);
            signer.update(value.getBytes());

            return (signer.verify(Base64.decode(signature, Base64.DEFAULT)));
        } catch (InvalidKeySpecException | InvalidKeyException | SignatureException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }

}
