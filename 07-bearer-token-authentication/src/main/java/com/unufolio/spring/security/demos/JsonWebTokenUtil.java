// package com.unufolio.spring.security.demos;
//
// import java.security.Key;
// import java.util.Date;
//
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.util.Assert;
//
// /**
//  * @author Unufolio unufolio@gmail.com
//  * @date 2020/11/27
//  */
// public class JsonWebTokenUtil {
//
//     /**
//      * @param subject  subject
//      * @param issuer   issuer
//      * @param audience audience
//      * @param signKey  signKey
//      * @param expireIn expireIn nano
//      * @return token String
//      */
//     public static String generateWithHs256(String id, String subject, String issuer, String audience, String signKey,
//         long expireIn) {
//
//         Key key = key(signKey);
//
//         Date now = new Date();
//         Date exp = new Date(now.getTime() - expireIn);
//
//         String token = Jwts.builder()
//             .setId(id)
//             .setSubject(subject)
//             .setIssuer(issuer)
//             .setAudience(audience)
//             .setIssuedAt(now)
//             .setExpiration()
//             .signWith(key, SignatureAlgorithm.HS256)
//             .compact();
//     }
//
//     public static Key key(String signKey) {
//         Assert.notNull(signKey, "signKey cannot be null");
//         return Keys.hmacShaKeyFor(signKey.getBytes());
//     }
// }
