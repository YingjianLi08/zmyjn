package com.zmyjn.core.util;

import com.zmyjn.core.entity.SysUserLogin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

public class JWTUtils {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JWTUtils.class);


    private static String clientId = "1";
    private static String name = "lyj";
    private static String expiresSecond = "36000";
    private static String base64Secret = "e10adc3949ba59abbe56e057f20f883e";


    /**
     * 解析jwt
     */
    public static Claims parseJWT(String jsonWebToken) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Secret))
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            return null;
        }
    }


    /**
     * 构建jwt
     */
    public static String createJWT(SysUserLogin sysUserLogin) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //生成签名密钥
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Secret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .claim("sysUserLogin", sysUserLogin)
                .setIssuer(name)
                .setAudience(clientId)
                .signWith(signatureAlgorithm, signingKey);
        //添加Token过期时间
        long TTLMillis = Long.valueOf(expiresSecond);
        if (TTLMillis >= 0) {
            long expMillis = nowMillis + TTLMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp).setNotBefore(now);
        }

        //生成JWT
        return builder.compact();
    }


}
