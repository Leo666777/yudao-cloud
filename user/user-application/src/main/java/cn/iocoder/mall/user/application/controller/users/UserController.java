package cn.iocoder.mall.user.application.controller.users;

import cn.iocoder.common.framework.vo.CommonResult;
import cn.iocoder.mall.user.application.convert.UserConvert;
import cn.iocoder.mall.user.sdk.context.UserSecurityContextHolder;
import cn.iocoder.mall.user.application.vo.users.UsersUserVO;
import cn.iocoder.mall.user.service.api.UserService;
import cn.iocoder.mall.user.service.api.bo.UserBO;
import cn.iocoder.mall.user.service.api.dto.UserUpdateDTO;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/user")
@Api("用户模块")
public class UserController {

    @Reference(validation = "true")
    private UserService userService;

    @GetMapping("/info")
    @ApiOperation(value = "用户信息")
    public CommonResult<UsersUserVO> info() {
        CommonResult<UserBO> userResult = userService.getUser(UserSecurityContextHolder.getContext().getUserId());
        return UserConvert.INSTANCE.convert2(userResult);
    }

    @PostMapping("/update_avatar")
    @ApiOperation(value = "更新头像")
    public CommonResult<Boolean> updateAvatar(@RequestParam("avatar") String avatar) {
        // 创建
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO().setId(UserSecurityContextHolder.getContext().getUserId())
                .setAvatar(avatar);
        // 更新头像
        return userService.updateUser(userUpdateDTO);
    }

    @PostMapping("/update_nickname")
    @ApiOperation(value = "更新昵称")
    public CommonResult<Boolean> updateNickname(@RequestParam("nickname") String nickname) {
        // 创建
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO().setId(UserSecurityContextHolder.getContext().getUserId())
                .setNickname(nickname);
        // 更新头像
        return userService.updateUser(userUpdateDTO);
    }

}