package com.github.hmld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.hmld.view.LoginView;

import javafx.application.Application;
/**
 * 启动类
 * @author hmld
 *
 */
@SpringBootApplication
public class MainLancher {
  public static void main(String[] args) {
    SpringApplication.run(LoginView.class, args);
    Application.launch(LoginView.class, args);
  }
}
