package com.example.pitchmanagement.utils;

import com.example.pitchmanagement.entity.User;
import com.example.pitchmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IdGeneration {

    @Autowired
    private UserService userService;

    public String raiseUserId(List<User> arrayList) {
        String result = "";
        int maxIndex = 1;
        if (arrayList == null) {
            result = "U" + String.format("%02d", maxIndex);
        } else {
            maxIndex = arrayList.size() + 1;
            result = "U" + String.format("%02d", maxIndex);
            User temp = userService.findUserById(result);
            while (temp != null) {
                maxIndex += 1;
                result = "U" + String.format("%02d", maxIndex);
                temp = userService.findUserById(result);
            }
        }
        return result;
    }
}
