package com.example.demo.springDemo.bean.circular.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EPersonService {
    @Autowired
    private CommunityService communityService;

}
