package com.example.admin.service.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Slf4j
@Controller
public class VerifyCodeController {

    private static final Random random = new Random(47);
    private static final int width = 100;
    private static final int height = 40;
    private static final int fontHeight = 37;
    private static final double fontWidth = 1.3;

    private static final char[] codes = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static final int[][] fontColors = {
            {0, 0, 0},
            {244, 67, 54},
            {30, 206, 64},
            {37, 30, 206},
            {33, 150, 243},
            {210, 11, 162},
            {206, 197, 30},
    };

    @GetMapping("/vcode")
    @ResponseBody
    public void getVerifyCode(HttpServletResponse response) {
        //定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics gd = buffImg.getGraphics();

        // 将图像填充为白色
        gd.setColor(Color.WHITE);
        gd.fillRect(0, 0, width, height);
        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Consolas", Font.PLAIN, fontHeight);
        gd.setFont(font);
        // 验证码
        String vocde = getRandomCode();
        setValidateCodeColor(vocde, width, height, fontWidth, gd);

        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        try {
            ServletOutputStream sos = response.getOutputStream();
            ImageIO.write(buffImg, "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            log.error("error when generate vcode", e);
        }

    }

    private void setValidateCodeColor(String str, int width, int height, double fontWidth, Graphics g) {
        setRandomFontColor(g);
        int x = (int) (width / 2 - fontWidth * g.getFontMetrics().stringWidth(str) / 2);
        int y = height / 2 + g.getFontMetrics().getHeight() / 3;
        String tempStr;
        int orgStringWight = g.getFontMetrics().stringWidth(str);
        int orgStringLength = str.length();
        int tempx = x;
        while (str.length() > 0) {
            tempStr = str.substring(0, 1);
            str = str.substring(1);
            g.drawString(tempStr, tempx, y);
            tempx = (int) (tempx + (double) orgStringWight / (double) orgStringLength * fontWidth);
        }
        setDrawLine(g);
    }

    private void setDrawLine(Graphics g) {
        for (int i = 0; i < 6; i++) {
            setRandomColor(g);
            g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
        }
    }

    private static void setRandomColor(Graphics g) {
        g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
    }

    private static void setRandomFontColor(Graphics g) {
        int i = random.nextInt(fontColors.length);
        g.setColor(new Color(fontColors[i][0], fontColors[i][1], fontColors[i][2]));
    }

    private static String getRandomCode() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i++ < 4) {
            sb.append(codes[random.nextInt(codes.length)]);
        }
        return sb.toString();
    }


}
