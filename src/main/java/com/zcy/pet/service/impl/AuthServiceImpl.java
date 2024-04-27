package com.zcy.pet.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import cn.hutool.core.lang.UUID;
import com.zcy.pet.common.result.Result;
import com.zcy.pet.common.result.ResultCode;
import com.zcy.pet.common.utils.ResponseUtils;
import com.zcy.pet.mapper.PetUserRoleMapper;
import com.zcy.pet.model.dto.LoginResult;
import com.zcy.pet.model.entity.PetUser;
import com.zcy.pet.model.entity.PetUserRole;
import com.zcy.pet.model.form.UserRegisterForm;
import com.zcy.pet.model.vo.UserToken;
import com.zcy.pet.model.vo.UserTokenInfo;
import com.zcy.pet.service.AuthService;
import com.zcy.pet.service.PetUserService;
import com.zcy.pet.utils.JwtTokenUtil;
import com.zcy.pet.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final JwtTokenUtil jwtTokenUtil;

    private final RedisUtil redisUtil;

    private final PetUserService petUserService;

    @Autowired
    private PetUserRoleMapper petUserRoleMapper;

    @Value("${jwt.expire.accessToken}")
    public Integer accessTokenExpire;

    @Override
    public Result<LoginResult> login(String email, String password) {
        // 验证邮箱是否在数据库中
        PetUser user = petUserService.getUserByEmail(email);
        // 如果用户不存在
        if (user == null) {
            return Result.failed("用户不存在");
        }
        // 验证密码是否正确
        if (!password.equals(user.getPassword())) {
            return Result.failed("密码错误");
        }

        UserTokenInfo userTokenInfo = new UserTokenInfo();
        userTokenInfo.setEmail(user.getEmail());
        userTokenInfo.setUid(user.getUid());
        userTokenInfo.setUsername(user.getUsername());
        // 生成Token
        UserToken userToken = jwtTokenUtil.createToekns(userTokenInfo);
        LoginResult loginResult = LoginResult
                .builder()
                .tokenType("Bearer")
                .accessToken(userToken.getAccessToken())
                .refreshToken(userToken.getRefreshToken())
                .expires(Long.valueOf(accessTokenExpire))
                .build();
        log.info("登录成功信息{}", loginResult);
        return Result.success(loginResult,"登录成功");
    }

    @Override
    public String register(UserRegisterForm userRegisterForm) {
        String username = userRegisterForm.getUsername();
        String email = userRegisterForm.getEmail();
        String password = userRegisterForm.getPassword();
        String code = userRegisterForm.getCode();
        // 判断是否为空
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(email) || !StringUtils.hasLength(password) || !StringUtils.hasLength(code)) {
            return "参数不能为空";
        }
        // 判断验证码是否正确
        String genCode = String.valueOf(redisUtil.get(email));
        if (!code.equals(genCode)) {
            return "验证码错误";
        }
        // 判断用户是否已存在
        if (petUserService.getUserByEmail(email) != null) {
            return "该邮箱已注册";
        }
        // 注册用户
        PetUser petUser = new PetUser();
        petUser.setUsername(username);
        petUser.setEmail(email);
        petUser.setPassword(password);
        boolean saved = petUserService.save(petUser);
        // 给用户默认绑定一个普通用户角色
        PetUserRole petUserRole = new PetUserRole();
        petUserRole.setId(null);
        petUserRole.setUid(petUser.getUid());
        petUserRole.setRid(2L);
        petUserRoleMapper.insert(petUserRole);
        if (saved) {
            return "注册成功";
        } else {
            return "注册失败";
        }
    }


    public String generateRandomString(int length) {
        final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
}
