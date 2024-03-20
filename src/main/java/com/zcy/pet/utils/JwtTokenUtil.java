package com.zcy.pet.utils;

import com.alibaba.fastjson2.JSONObject;
import com.zcy.pet.model.vo.UserToken;
import com.zcy.pet.model.vo.UserTokenInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    public String secret;

    @Value("${jwt.header}")
    public String header;

    @Value("${jwt.expire.accessToken}")
    public Integer accessTokenExpire;

    @Value("${jwt.expire.refreshToken}")
    public Integer refreshTokenExpire;

    private final RedisUtil redisUtil;

    /**
     * 创建 刷新令牌 与 访问令牌 的关联关系
     */
    public void tokenAssociation(UserToken userToken, Date refreshTokenExpireDate) {
        Long time = (refreshTokenExpireDate.getTime() - System.currentTimeMillis()) / 1000 + 100;
        redisUtil.set(userToken.getRefreshToken(), userToken.getAccessToken(), time);
    }

    /**
     * 根据 刷新令牌 获取 访问令牌
     *
     * @param refreshToken
     */
    public String getAccessTokenByRefresh(String refreshToken) {
        Object value = redisUtil.get(refreshToken);
        return value == null ? null : String.valueOf(value);
    }

    /**
     * 添加至黑名单
     *
     * @param token
     * @param expireTime
     */
    public void addBlacklist(String token, Date expireTime) {
        Long expireTimeLong = (expireTime.getTime() - System.currentTimeMillis()) / 1000 + 100;
        redisUtil.set(getBlacklistPrefix(token), "1", expireTimeLong);
    }

    /**
     * 校验是否存在黑名单
     *
     * @param token
     * @return true 存在 false不存在
     */
    public Boolean checkBlacklist(String token) {
        return redisUtil.exists(getBlacklistPrefix(token));
    }

    /**
     * 获取黑名单前缀
     *
     * @param token
     * @return
     */
    public String getBlacklistPrefix(String token) {
        return Constants.TOKEN_BLACKLIST_PREFIX + token;
    }

    /**
     * 获取 token 信息
     *
     * @param userTokenInfo
     * @return
     */
    public UserToken createToekns(UserTokenInfo userTokenInfo) {
        Date nowDate = new Date();
        Date accessTokenExpireDate = new Date(nowDate.getTime() + accessTokenExpire * 1000);
        Date refreshTokenExpireDate = new Date(nowDate.getTime() + refreshTokenExpire * 1000);

        UserToken userToken = new UserToken();
        BeanUtils.copyProperties(userTokenInfo, userToken);
        userToken.setAccessToken(createToken(userTokenInfo, nowDate, accessTokenExpireDate));
        userToken.setRefreshToken(createToken(userTokenInfo, nowDate, refreshTokenExpireDate));

        // 创建 刷新令牌 与 访问令牌 关联关系
        tokenAssociation(userToken, refreshTokenExpireDate);
        return userToken;
    }

    /**
     * 生成token
     *
     * @param userTokenInfo
     * @return
     */
    public String createToken(UserTokenInfo userTokenInfo, Date nowDate, Date expireDate) {
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(JSONObject.toJSONString(userTokenInfo))
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 获取 token 中注册信息
     *
     * @param token
     * @return
     */
    public Claims getTokenClaim(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证 token 是否过期失效
     *
     * @param token
     * @return true 过期 false 未过期
     */
    public Boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date());
    }

    /**
     * 获取 token 失效时间
     *
     * @param token
     * @return
     */
    public Date getExpirationDate(String token) {
        return getTokenClaim(token).getExpiration();
    }


    /**
     * 获取 token 发布时间
     *
     * @param token
     * @return
     */
    public Date getIssuedAtDate(String token) {
        return getTokenClaim(token).getIssuedAt();
    }


    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    public UserTokenInfo getUserInfoToken(String token) {
        String subject = getTokenClaim(token).getSubject();
        UserTokenInfo userTokenInfo = JSONObject.parseObject(subject, UserTokenInfo.class);
        return userTokenInfo;
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public String getUserName(String token) {
        UserTokenInfo userInfoToken = getUserInfoToken(token);
        return userInfoToken.getUserName();
    }

    /**
     * 获取用户Id
     *
     * @param token
     * @return
     */
    public Long getUserId(String token) {

        UserTokenInfo userInfoToken = getUserInfoToken(token);
        return userInfoToken.getUserId();
    }

}
