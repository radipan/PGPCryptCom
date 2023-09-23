/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encryptor;

import java.util.HashMap;
import java.util.Map;
import org.pgpainless.key.generation.type.KeyType;
import org.pgpainless.key.generation.type.ecc.EllipticCurve;
import org.pgpainless.key.generation.type.eddsa.EdDSACurve;
import org.pgpainless.key.generation.type.rsa.RsaLength;

/**
 *
 * @author adipu
 */
public enum KeyAlgAndSize {
    RSA_3072("RSA3072",KeyType.RSA(RsaLength._3072)),
    RSA_4096("RSA3096",KeyType.RSA(RsaLength._4096)),
    RSA_8192("RSA8192",KeyType.RSA(RsaLength._8192)),
    ECDSA_P521("ECDSAP521", KeyType.ECDSA(EllipticCurve._P521)),
    ECDSA_P384("ECDSAP384",KeyType.ECDSA(EllipticCurve._P384)),
    ECDSA_P256("ECDSAP256",KeyType.ECDSA(EllipticCurve._P256)),
    ECDSA_SECP256K1("ECDSASECP256K1", KeyType.ECDSA(EllipticCurve._SECP256K1)),
    ECDSA_BRAINPOOLP256R1("ECDSABRAINPOOLP256R1",KeyType.ECDSA(EllipticCurve._BRAINPOOLP256R1)),
    ECDSA_BRAINPOOLP384R1("ECDSABRAINPOOLP384R1",KeyType.ECDSA(EllipticCurve._BRAINPOOLP384R1)),
    ECDSA_BRAINPOOLP512R1("ECDSABRAINPOOLP512R1",KeyType.ECDSA(EllipticCurve._BRAINPOOLP512R1)),
    ECDH_P521("ECDHP521",KeyType.ECDH(EllipticCurve._P521)),
    ECDH_P384("ECDHP384",KeyType.ECDH(EllipticCurve._P384)),
    ECDH_P256("ECDHP256",KeyType.ECDH(EllipticCurve._P256)),
    ECDH_SECP256K1("ECDHSECP256K1",KeyType.ECDH(EllipticCurve._SECP256K1)),
    ECDH_BRAINPOOLP256R1("ECDHBRAINPOOLP256R1",KeyType.ECDH(EllipticCurve._BRAINPOOLP256R1)),
    ECDH_BRAINPOOLP384R1("ECDHBRAINPOOLP384R1",KeyType.ECDH(EllipticCurve._BRAINPOOLP384R1)),
    ECDH_BRAINPOOLP512R1("ECDSABRAINPOOLP512R1",KeyType.ECDH(EllipticCurve._BRAINPOOLP512R1)),
    EDDSA_Ed25519("EDDSAEd25519", KeyType.EDDSA(EdDSACurve._Ed25519));
    
    private static final Map<String, KeyAlgAndSize> BY_STRING = new HashMap<>();
    //private static final Map<String, KeyAlgAndSize> BY_STRING = new HashMap<String, KeyAlgAndSize>();
    
    public final String stringAlg;
    public final KeyType keyType;
    static 
    {
        for (KeyAlgAndSize k: values())
        {
            BY_STRING.put(k.stringAlg, k);
        }
    }
    
    public static KeyAlgAndSize valueOfString(String str)
    {
        return BY_STRING.get(str);
    }
    
    private KeyAlgAndSize(String stringAlg, KeyType keyType) {
        this.stringAlg = stringAlg;
        this.keyType = keyType;
    }
}
